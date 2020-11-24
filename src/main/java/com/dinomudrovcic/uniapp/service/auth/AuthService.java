package com.dinomudrovcic.uniapp.service.auth;

import com.dinomudrovcic.uniapp.domain.auth.ERole;
import com.dinomudrovcic.uniapp.domain.auth.Role;
import com.dinomudrovcic.uniapp.domain.auth.User;
import com.dinomudrovcic.uniapp.payload.request.LoginRequest;
import com.dinomudrovcic.uniapp.payload.request.SignupRequest;
import com.dinomudrovcic.uniapp.payload.response.JwtResponse;
import com.dinomudrovcic.uniapp.payload.response.MessageResponse;
import com.dinomudrovcic.uniapp.repository.RoleRepository;
import com.dinomudrovcic.uniapp.repository.UserRepository;
import com.dinomudrovcic.uniapp.security.jwt.JwtUtils;
import com.dinomudrovcic.uniapp.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private AuthenticationManager authManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;

    @Autowired
    public AuthService(AuthenticationManager authManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public JwtResponse getJwtResponseFromLoginRequest(LoginRequest loginRequest) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtUtils.generateJwtToken(auth);

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        int expirationDate = jwtUtils.getExpirationFromJwtToken(jwt);
        return new JwtResponse(jwt, userDetails.getUsername(), roles, expirationDate);
        //return new JwtResponse(jwt);
    }


    public ResponseEntity<?> registerUser(@Valid SignupRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role_admin is not found."));
                        roles.add(adminRole);
                        break;
                    case "student":
                        //TODO: implementation for role student
                        break;
                    case "professor":
                        //TODO: implementation for role professor
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role_user is not found."));
                        roles.add(userRole);
                        break;
                }
            });
        }

        User user = new User(signUpRequest.getUsername(), passwordEncoder.encode(signUpRequest.getPassword()), roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered!"));
    }

}
