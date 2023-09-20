package AhChacha.Backend.domain;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Provider {
    KAKAO, GOOGLE;


    public static Provider ofLegacyCode(String legacyCode) {
        if(legacyCode == "KAKAO") {
            return KAKAO;
        } else if (legacyCode == "GOOGLE") {
            return GOOGLE;
        }
        else return null;
    }
}
