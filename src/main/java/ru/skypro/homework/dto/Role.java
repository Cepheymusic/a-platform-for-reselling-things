package ru.skypro.homework.dto;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER("USER"), ADMIN("ADMIN");
    Role(String value) {
        this.value = value;
    }
    private String value;

    @Override
    public String getAuthority() {
        return value;
    }
}
