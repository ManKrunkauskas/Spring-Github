package com.example.RunningClub.service;

import com.example.RunningClub.dto.UserRequestDTO;
import com.example.RunningClub.dto.UserResponseDTO;
import com.example.RunningClub.model.Role;
import com.example.RunningClub.model.User;
import com.example.RunningClub.repository.RoleRepository;
import com.example.RunningClub.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO registerUser(UserRequestDTO dto) {
        if (userRepository.findByUsername(dto.username()).isPresent()) {
            throw new RuntimeException("Username is already taken");
        }

        Set<Role> roles = dto.roles().stream()
                .map(roleId -> roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found")))
                .collect(Collectors.toSet());

        User user = new User(dto.username(), passwordEncoder.encode(dto.password()), roles);
        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getRoles().stream().map(Role::getName).collect(Collectors.toSet())
        );
    }
}
