package AhChacha.Backend.controller;

import AhChacha.Backend.dto.blood.request.BloodRequest;
import AhChacha.Backend.dto.blood.response.BloodIdResponse;
import AhChacha.Backend.dto.blood.response.BloodsResponse;
import AhChacha.Backend.service.BloodService;
import AhChacha.Backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/blood")
@RestController
public class BloodController {
    private final BloodService bloodService;

    @PostMapping("/{memberId}")
    public ResponseEntity<BloodIdResponse> save(@PathVariable("memberId") Long id,
                                                @RequestBody BloodRequest request){
        BloodIdResponse response = bloodService.save(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<BloodsResponse> findAll(@PathVariable("memberId") Long id){
        BloodsResponse response = bloodService.findAll(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{bloodId}")
    public ResponseEntity<BloodIdResponse> update(@PathVariable("bloodId") Long id,
                                                  @RequestBody BloodRequest request){
        BloodIdResponse response = bloodService.update(request, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{bloodId}")
    public ResponseEntity<Void> delete(@PathVariable("bloodId") Long id){
        bloodService.delete(id);
        return ResponseEntity.ok().build();
    }
}
