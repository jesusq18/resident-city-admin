package com.resident_city_admin.resident_city_admin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.resident_city_admin.resident_city_admin.domain.user.User;
import com.resident_city_admin.resident_city_admin.dto.UserDTO;
import com.resident_city_admin.resident_city_admin.exception.ResourceNotFoundException;
import com.resident_city_admin.resident_city_admin.repository.UserRepository;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resident_city_admin.resident_city_admin.exception.InvalidCityException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private static final String API_URL = "https://wft-geo-db.p.rapidapi.com/v1/geo/cities?namePrefix=";
    private static final String RAPIDAPI_HOST = "wft-geo-db.p.rapidapi.com";
    @Value("${rapidapi.key}")
    private String RAPIDAPI_KEY;

    private final ObjectMapper objectMapper = new ObjectMapper();

    RestTemplate restTemplate = new RestTemplate();

    public void cityValidation(String city) {
        try {
            String url = API_URL + city;

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Host", RAPIDAPI_HOST);
            headers.set("X-RapidAPI-Key", RAPIDAPI_KEY);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            String response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class)
                    .getBody();
            JsonNode jsonResponse = objectMapper.readTree(response);
            JsonNode data = jsonResponse.get("data");

            if (data.isEmpty()) {
                throw new InvalidCityException("The city " + city + " doesn't exist.");
            }
        } catch (RuntimeException | JsonProcessingException e) {
            throw new InvalidCityException("Error on validating city: " + e.getMessage());
        }
    }
    public void createUser(User user){
        this.cityValidation(user.getCity());
        this.userRepository.save(user);
    }
    public Optional<User> findUserById(Long Id){
        return this.userRepository.findUserById(Id);
    }

    public Optional<User> findUserByEmail(Email email){
        return this.userRepository.findUserByEmail(email);
    }

    public void deleteUserById(Long Id){
        if(this.userRepository.existsById(Id)){
            this.userRepository.deleteById(Id);
        } else throw new ResourceNotFoundException("User with ID + " + Id + " Not Found.");
    }

    public void updateUser(Long Id, UserDTO userDTO){
        Optional<User> userOptional = findUserById(Id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            if (userDTO.getName() != null && userDTO.getName().isEmpty()){
                user.setName(userDTO.getName());
            }
            if (userDTO.getCity() != null && !userDTO.getCity().isEmpty()) {
                user.setCity(userDTO.getCity());
            }
            if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
                user.setEmail(userDTO.getEmail());
            }
            userRepository.save(user);
        } else throw new ResourceNotFoundException("User with ID + " + Id + " Not Found.");
    }

    public List<User> findUserByCity(String city){
        Optional<List> optionalList = userRepository.findUserByCity(city);
        if (optionalList.isPresent()){
            List<User> list = optionalList.get();
            return list;
        } else throw new ResourceNotFoundException("User Not Found.");
    }



}
