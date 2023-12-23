package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.dto.UserData;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException());
        return new UserData(0, username, userEntity.getPassword(), userEntity.getRole());
    }

    public void accessVerification(UserDetails userDetails, CommentEntity commentEntity) {
        if (!userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
                && !userDetails.getUsername().equals(commentEntity.getAuthor().getEmail())) {
            throw new RuntimeException();
        }
    }

    public void setPassword(NewPassword newPassword, UserDetails userDetails) {
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException());
        userEntity.setPassword(new BCryptPasswordEncoder().encode(newPassword.getNewPassword()));
        userRepository.save(userEntity);
    }

    public User getUser(UserDetails userDetails) {
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException());
        return userMapper.userToUserDTO(userEntity);
    }

    public UpdateUser updateUser(UpdateUser updateUser, UserDetails userDetails) {
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException());
        userEntity.setFirstName(updateUser.getFirstName());
        userEntity.setLastName(updateUser.getLastName());
        userEntity.setPhone(updateUser.getPhone());
        userRepository.save(userEntity);
        return updateUser;
    }

//    public void updateUserImage() {
//
//    }
}
