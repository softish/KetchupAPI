package app.controllers;

import app.domain.User;
import app.model.AuthenticatedUserDTO;
import app.model.UserDTO;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller manages user related actions.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<AuthenticatedUserDTO> authenticate(@RequestBody UserDTO userDTO) {
        if (userDTO == null || isEmpty(userDTO)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User u = userRepository.findByUsername(userDTO.getUsername());
        if (u == null) {
            return new ResponseEntity<>(new AuthenticatedUserDTO(-1, ""), HttpStatus.NOT_FOUND);
        }

        if (authenticated(userDTO, u)) {
            return new ResponseEntity<>(new AuthenticatedUserDTO(u.getId(), u.getUsername()), HttpStatus.OK);
        }

        return new ResponseEntity<>(new AuthenticatedUserDTO(-1, ""), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        if (userDTO == null || isEmpty(userDTO)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User u = userRepository.findByUsername(userDTO.getUsername());

        if (u == null) {
            User user = new User(userDTO.getUsername(), hash(userDTO.getPassword()));
            userRepository.save(user);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userDTO, HttpStatus.CONFLICT);
        }
    }

    private boolean authenticated(UserDTO requestingUser, User storedUser) {
        return requestingUser.getUsername().equals(storedUser.getUsername()) && BCrypt.checkpw(requestingUser.getPassword(), storedUser.getPassword());
    }

    private boolean isEmpty(UserDTO userDTO) {
        return userDTO.getPassword().trim().equals("") || userDTO.getPassword().trim().equals("");
    }

    private String hash(String string) {
        return new BCryptPasswordEncoder().encode(string);
    }
}
