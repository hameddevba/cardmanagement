package com.starter.project.controller;

import com.starter.project.dao.AgencyDao;
import com.starter.project.dao.RoleDao;
import com.starter.project.dao.UserDao;
import com.starter.project.dto.RoleDto;
import com.starter.project.dto.UserDetailDto;
import com.starter.project.dto.request.LoginRequest;
import com.starter.project.dto.request.UserDto;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


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
    AgencyDao agencyDao;

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
                .map(GrantedAuthority::getAuthority)
                .toList();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(userDto.getUsername(),
                userDto.getEmail(),
                encoder.encode(userDto.getPassword()),
                agencyDao.findByCode(userDto.getCodeAgency()).orElse(null));

        Set<String> strRoles = userDto.getRoles();
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
    public ResponseEntity<MessageResponse> modifUser(@Valid @RequestBody UserDto signUpRequest) {
        User userSaved = userRepository.findByUsername(signUpRequest.getUsername()).orElse(new User());
        Set<String> strRoles = signUpRequest.getRoles();
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

        userSaved.setUsername(signUpRequest.getUsername());
        userSaved.setRoles(roles);
        userSaved.setEmail(signUpRequest.getEmail());
        userSaved.setAgency(agencyDao.findByCode(signUpRequest.getCodeAgency()).orElse(null));

        if (signUpRequest.getPassword().equals("")) {
            userSaved.setPassword(userSaved.getPassword());
        } else {
            userSaved.setPassword(encoder.encode(signUpRequest.getPassword()));
        }
        userRepository.save(userSaved);

        return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable String username) {

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            User userSaved = user.get();
            userRepository.delete(userSaved);
            return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Utilisateur n'existe pas"));

    }
}