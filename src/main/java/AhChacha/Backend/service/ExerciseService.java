package AhChacha.Backend.service;

import AhChacha.Backend.domain.Exercise;
import AhChacha.Backend.domain.Member;
import AhChacha.Backend.dto.exercise.request.ExerciseRequest;
import AhChacha.Backend.dto.exercise.response.ExerciseIdResponse;
import AhChacha.Backend.dto.exercise.response.ExerciseResponse;
import AhChacha.Backend.dto.exercise.response.ExercisesResponse;
import AhChacha.Backend.exception.NotFoundException;
import AhChacha.Backend.repository.ExerciseRepository;
import AhChacha.Backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.EXERCISE_NOT_FOUND;
import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.USER_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ExerciseIdResponse save(Long memberId, ExerciseRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Exercise exercise = Exercise.builder()
                .quantity(request.getQuantity())
                .type(request.getType())
                .member(member)
                .build();
        Long id = exerciseRepository.save(exercise).getId();
        return new ExerciseIdResponse(id);
    }

    public ExercisesResponse findAll(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        List<Exercise> exercises = exerciseRepository.findAllByMember(member);
        return new ExercisesResponse(exercises.stream()
                .map(ExerciseResponse::of)
                .toList());
    }

    @Transactional
    public ExerciseIdResponse update(Long id, ExerciseRequest request) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(EXERCISE_NOT_FOUND));
        exercise.update(request);
        return new ExerciseIdResponse(exercise.getId());
    }

    public void delete(Long id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(EXERCISE_NOT_FOUND));
        exerciseRepository.delete(exercise);
    }
}
