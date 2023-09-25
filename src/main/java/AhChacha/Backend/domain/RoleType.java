package AhChacha.Backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum RoleType {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    GUEST("GUEST");

    private final String key;
}
