package com.spring.controller;

import com.spring.annotation.DefaultExceptionMessage;
import com.spring.dto.UserDTO;
import com.spring.entity.ResponseWrapper;
import com.spring.entity.User;
import com.spring.entity.common.AuthenticationRequest;
import com.spring.exception.TicketingProjectException;
import com.spring.mapper.MapperUtil;
import com.spring.service.UserService;
import com.spring.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authentication Controller", description = "Authenticate API")
public class LoginController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private MapperUtil mapperUtil;
    private JWTUtil jwtUtil;

    public LoginController(AuthenticationManager authenticationManager, UserService userService, MapperUtil mapperUtil, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.mapperUtil = mapperUtil;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/authenticate")
    @DefaultExceptionMessage(defaultMessage = "Bad Credentials")
    @Operation(summary = "Login to application")
    public ResponseEntity<ResponseWrapper> doLogin(@RequestBody AuthenticationRequest authenticationRequest) throws TicketingProjectException {
        String password = authenticationRequest.getPassword();
        String username = authenticationRequest.getUsername();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authentication);
        UserDTO foundUser = userService.findByUserName(username);
        User convertedUser = mapperUtil.convert(foundUser, new User());

        if (!foundUser.isEnabled()){
            throw new TicketingProjectException("Please verify your user");
        }

        String jwtToken = jwtUtil.generateToken(convertedUser);

        return ResponseEntity.ok(new ResponseWrapper("Login successful", jwtToken));
    }

    @DefaultExceptionMessage(defaultMessage = "Something went wrong, try again!")
    @PostMapping("/create-user")
    @Operation(summary = "Create new account")
    private ResponseEntity<ResponseWrapper> doRegister(@RequestBody UserDTO userDTO){

        UserDTO createdUser = userService.save(userDTO);
        sendEmail(createEmail(createdUser));
    }

}
