package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.Exceptions.UserNotFoundException;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;

@Service
public class UserService{
    private UserRepository userRepository;
    private ImageService imageService;

    public UserService(UserRepository userRepository, ImageService imageService) {
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    public void setPassword(NewPassword newPassword, UserDetails userDetails) {
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new UserNotFoundException("User is not found"));
        userEntity.setPassword(new BCryptPasswordEncoder().encode(newPassword.getNewPassword()));
        userRepository.save(userEntity);
    }

    public User getUser(UserDetails userDetails) {
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new UserNotFoundException("User is not found"));
        return UserMapper.INSTANCE.userToUserDTO(userEntity);
    }

    public UpdateUser updateUser(UpdateUser updateUser, UserDetails userDetails) {
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new UserNotFoundException("User is not found"));
        userEntity.setFirstName(updateUser.getFirstName());
        userEntity.setLastName(updateUser.getLastName());
        userEntity.setPhone(updateUser.getPhone());
        userRepository.save(userEntity);
        return updateUser;
    }

    public void updateUserImage(MultipartFile multipartFile, UserDetails userDetails) throws IOException {
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).
                orElseThrow(() -> new UserNotFoundException("User is not found"));
        ImageEntity imageEntity = imageService.downloadImage(multipartFile);
        imageService.deleteUserImage(userEntity);
        userEntity.setImageEntity(imageEntity);
        userRepository.save(userEntity);
    }
}
