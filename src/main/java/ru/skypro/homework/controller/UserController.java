package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping("/set_password")
    public ResponseEntity<String> setPassword(@RequestBody NewPassword newPassword) {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/me")
    public User me() {
        return new User(0, "jjj@gmail.com", "Serzh", "Serzhev", "+7987987987", Role.USER, "");
    }

    @PatchMapping("/me")
    public UpdateUser updateUser(UpdateUser updateUser) {
        return updateUser;
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUserImage(@RequestPart("image") MultipartFile image) {
        return ResponseEntity.ok("OK");
    }
}
