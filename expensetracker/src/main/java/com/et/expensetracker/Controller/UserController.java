package com.et.expensetracker.Controller;

import com.et.expensetracker.DTO.LoginRequest;
import com.et.expensetracker.Model.User;
import com.et.expensetracker.Repository.UserRepository;
import com.et.expensetracker.Security.JwtUtil;
import com.et.expensetracker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserRepository repo;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) throws Exception {
        Optional<User> existingUser = repo.findByEmail(user.getEmail());
        Optional<User> existingUserName = repo.findByUsername(user.getUsername());
        if(existingUser.isPresent() || existingUserName.isPresent()){
            Map<String, String> res = new HashMap<>();
            res.put("message","User already exists with this email or username!!");
            return ResponseEntity.ok(res);
        }
        service.registerUser(user);
        Map<String, String>  successRes = new HashMap<>();
        successRes.put("message", "User Registered Successfully!");
        return ResponseEntity.ok(successRes);
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Received username: " + loginRequest.getUsername());
        System.out.println("Received password: " + loginRequest.getPassword());
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        Long id = user.get().getId();
        try {
            authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            String token = jwtUtil.generateToken(loginRequest.getUsername());
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("username", loginRequest.getUsername());
            // Add token expiration time
            response.put("userId", id);

            // Return the response as JSON
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);        }
    }
}
