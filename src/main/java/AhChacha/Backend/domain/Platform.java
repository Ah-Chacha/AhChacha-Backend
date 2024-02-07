package AhChacha.Backend.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Platform {
    GOOGLE("GOOGLE", "구글"),
    KAKAO("KAKAO", "카카오"),
    NAVER("NAVER", "네이버");

    private final String key;
    private final String title;
}
