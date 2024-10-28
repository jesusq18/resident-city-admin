package com.resident_city_admin.resident_city_admin.Cotroller;

import com.resident_city_admin.resident_city_admin.domain.user.User;
import com.resident_city_admin.resident_city_admin.dto.UserDTO;
import com.resident_city_admin.resident_city_admin.exception.ResourceNotFoundException;
import com.resident_city_admin.resident_city_admin.repository.UserRepository;
import com.resident_city_admin.resident_city_admin.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserRestCotroller {
    @Autowired
    private UserRepository  userRepository;
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setCity(userDTO.getCity());

        userService.createUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        if (this.userService.findUserById(id).isPresent()){
            return ResponseEntity.ok().body(this.userService.findUserById(id));
        } else return ResponseEntity.notFound().build();
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable Email email){
        if (this.userService.findUserByEmail(email).isPresent()){
            return ResponseEntity.ok().body(this.userService.findUserByEmail(email));
        } else return ResponseEntity.notFound().build();
    }

    @GetMapping("/{city}")
    public ResponseEntity<?> findCities(@PathVariable String city){
        try{
            userService.findUserByCity(city);
            return ResponseEntity.ok().body(userService.findUserByCity(city));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long Id) {
        if (this.userService.findUserById(Id).isPresent()) {

            this.userService.deleteUserById(Id);
            return ResponseEntity.noContent().build();

        } else return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{Id}")
    public ResponseEntity<?> updateUser(@PathVariable Long Id, @RequestBody UserDTO userDTO) {
        try {
            userService.updateUser(Id, userDTO);
                return ResponseEntity.accepted().build();
        }   catch(ResourceNotFoundException e){
                return ResponseEntity.status(404).body(e.getMessage());  // Retorna 404 con mensaje
        }
    }

}
