package AhChacha.Backend.controller;

import AhChacha.Backend.dto.exercise.request.ExerciseRequest;
import AhChacha.Backend.dto.exercise.response.ExerciseIdResponse;
import AhChacha.Backend.dto.exercise.response.ExercisesResponse;
import AhChacha.Backend.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/exercise")
@RequiredArgsConstructor
@RestController
public class ExerciseController {
    private final ExerciseService exerciseService;

    @PostMapping("/{memberId}")
    public ResponseEntity<ExerciseIdResponse> save(@PathVariable("memberId") Long id,
                                                   @RequestBody ExerciseRequest request) {
        ExerciseIdResponse response = exerciseService.save(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<ExercisesResponse> findAll(@PathVariable("memberId") Long id) {
        ExercisesResponse response = exerciseService.findAll(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{exerciseId}")
    public ResponseEntity<ExerciseIdResponse> update(@PathVariable("exerciseId") Long id,
                                                     @RequestBody ExerciseRequest request) {
        ExerciseIdResponse response = exerciseService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<Void> delete(@PathVariable("exerciseId") Long id) {
        exerciseService.delete(id);
        return ResponseEntity.ok().build();
    }

}
