package AhChacha.Backend.controller;

import AhChacha.Backend.dto.habit.request.HabitRequest;
import AhChacha.Backend.dto.habit.response.HabitIdResponse;
import AhChacha.Backend.dto.habit.response.HabitsResponse;
import AhChacha.Backend.service.HabitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/habit")
@RestController
public class HabitController {

    private final HabitService habitService;

    @PostMapping("/{memberId}")
    public ResponseEntity<HabitIdResponse> save(@PathVariable("memberId") Long id,
                                @RequestBody HabitRequest request){
        HabitIdResponse response = habitService.save(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<HabitsResponse> findAll(@PathVariable("memberId") Long id){
        HabitsResponse response = habitService.findAll(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{habitId}")
    public ResponseEntity<HabitIdResponse> update(@PathVariable("habitId") Long id,
                                                  @RequestBody HabitRequest request){
        HabitIdResponse response = habitService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{habitId}")
    public ResponseEntity<Void> delete(@PathVariable("habitId") Long id){
        habitService.delete(id);
        return ResponseEntity.ok().build();
    }
}
