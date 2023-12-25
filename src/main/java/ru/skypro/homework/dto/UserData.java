package ru.skypro.homework.dto;

import org.springframework.security.core.userdetails.User;
import ru.skypro.homework.entity.UserEntity;
import java.util.List;

public class UserData extends User {

    private final Integer id;

    public UserData(UserEntity userEntity) {
        super(userEntity.getEmail(), userEntity.getPassword(), List.of(userEntity.getRole()));
        this.id = userEntity.getId();
    }
    public Integer getId() {
        return id;
    }
}
