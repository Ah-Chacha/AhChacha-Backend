package AhChacha.Backend.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {
    DOCTOR("ROLE_DOCTOR", "의사"),
    PATIENT("ROLE_PATIENT", "의심 환자"),
    GUARDIAN("ROLE_GUARDIAN", "보호자"),
    ADMIN("ROLE_ADMIN", "관리자"),
    GUEST("ROLE_GUEST", "게스트");

    private final String key;
    private final String title;
}
