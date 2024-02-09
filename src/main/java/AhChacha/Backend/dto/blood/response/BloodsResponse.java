package AhChacha.Backend.dto.blood.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BloodsResponse {
    private List<BloodResponse> bloodResponses;
}
