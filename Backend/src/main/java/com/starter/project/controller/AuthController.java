package com.starter.project.controller;

import com.starter.project.dao.RoleDao;
import com.starter.project.dao.UserDao;
import com.starter.project.dto.RoleDto;
import com.starter.project.dto.UserDetailDto;
import com.starter.project.dto.request.LoginRequest;
import com.starter.project.dto.request.SigndeleteRequest;
import com.starter.project.dto.request.SignupRequest;
import com.starter.project.dto.response.JwtResponse;
import com.starter.project.dto.response.MessageResponse;
import com.starter.project.jwt.JwtUtils;
import com.starter.project.mapper.RoleMapper;
import com.starter.project.mapper.UserMapper;
import com.starter.project.model.Role;
import com.starter.project.model.User;
import com.starter.project.model.enums.RoleEnum;
import com.starter.project.service.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDao userRepository;
    @Autowired
    RoleDao roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    private final UserMapper mapper;

    private final RoleMapper roleMapper;

    public AuthController(AuthenticationManager authenticationManager, UserDao userRepository, RoleDao roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils, UserMapper mapper, RoleMapper roleMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.mapper = mapper;
        this.roleMapper = roleMapper;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDetailDto>> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            user.setPassword("");
        });
        return ResponseEntity.ok(mapper.toDto(users));
    }
    @GetMapping("/roles")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return ResponseEntity.ok(roleMapper.toDto(roles));
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleEnum.ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                Role adminRole = roleRepository.findByName(RoleEnum.valueOf(role.toUpperCase()))
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(adminRole);
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PutMapping("/signput")
    public ResponseEntity<MessageResponse> ModifUser(@Valid @RequestBody User signUpRequest) {
        User userSaved;
        if (!userRepository.existsByUsername(signUpRequest.getUsername())) {
            userSaved=new User();
        }else{
        // Create new user's account
        Optional<User> user = userRepository.findByUsername(signUpRequest.getUsername());
         userSaved = user.get();
        }
        //SignupRequest user = new SignupRequest();

        Set<Role> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleEnum.ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                Role adminRole = roleRepository.findByName(RoleEnum.valueOf(role.getName().toString().toUpperCase()))
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(adminRole);
            });
        }

        userSaved.setUsername(signUpRequest.getUsername());
        userSaved.setRoles(roles);
        userSaved.setEmail(signUpRequest.getEmail());

        if (signUpRequest.getPassword().equals("")) {
            userSaved.setPassword(userSaved.getPassword());
        } else {
            userSaved.setPassword(encoder.encode(signUpRequest.getPassword()));
        }
        //userSaved.setPassword(encoder.encode(signUpRequest.getPassword()));
        userRepository.save(userSaved);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PutMapping("/signdel")
    public ResponseEntity<MessageResponse> deleteUser(@Valid @RequestBody SigndeleteRequest signUpRequest) {
        if (!userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username N'est pas correcten!"));
        }

        if (!userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email N'est pas correcte!"));
        }

        Optional<User> user = userRepository.findByUsername(signUpRequest.getUsername());
        User userSaved = user.get();

        userRepository.delete(userSaved);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}