package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsService detailsService;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public AuthServiceImpl(UserDetailsService detailsService, PasswordEncoder encoder, UserRepository userRepository) {
        this.detailsService = detailsService;
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = detailsService.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (userRepository.existsByEmail(register.getUsername())) {
            return false;
        }
        UserEntity userEntity = UserEntity.builder()
                .firstName(register.getFirstName())
                .lastName(register.getLastName())
                .email(register.getUsername())
                .phone(register.getPhone())
                .role(register.getRole())
                .id(0)
                .password(encoder.encode(register.getPassword()))
                .build();
        userRepository.save(userEntity);
        return true;
    }
}
