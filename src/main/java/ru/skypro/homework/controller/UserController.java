package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.impl.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/set_password")
    public ResponseEntity<String> setPassword(@RequestBody NewPassword newPassword,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        userService.setPassword(newPassword, userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/me")
    public User me(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getUser(userDetails);
    }

    @PatchMapping("/me")
    public UpdateUser updateUser(@RequestBody UpdateUser updateUser, @AuthenticationPrincipal UserDetails userDetails) {
        return userService.updateUser(updateUser, userDetails);
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUserImage(@RequestPart("image") MultipartFile image) {
        return ResponseEntity.ok("OK");
    }
}
