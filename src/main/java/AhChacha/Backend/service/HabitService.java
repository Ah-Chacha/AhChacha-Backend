package AhChacha.Backend.service;

import AhChacha.Backend.domain.Habit;
import AhChacha.Backend.domain.Member;
import AhChacha.Backend.dto.habit.request.HabitRequest;
import AhChacha.Backend.dto.habit.response.HabitIdResponse;
import AhChacha.Backend.dto.habit.response.HabitResponse;
import AhChacha.Backend.dto.habit.response.HabitsResponse;
import AhChacha.Backend.exception.NotFoundException;
import AhChacha.Backend.repository.HabitRepository;
import AhChacha.Backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.HABIT_NOT_FOUND;
import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.USER_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class HabitService {
    private final MemberRepository memberRepository;
    private final HabitRepository habitRepository;


    @Transactional
    public HabitIdResponse save(Long memberId, HabitRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Habit habit = Habit.builder()
                .foodNum(request.getFoodNum())
                .alcoholQuantity(request.getAlcoholQuantity())
                .readingTime(request.getReadingTime())
                .build();
        return new HabitIdResponse(habit.getId());
    }

    public HabitsResponse findAll(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        List<Habit> habits = habitRepository.findAllByMember(member);
        return new HabitsResponse(habits.stream()
                .map(HabitResponse::of).toList());
    }

    public HabitIdResponse update(Long id, HabitRequest request) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HABIT_NOT_FOUND));
        habit.update(request);
        return new HabitIdResponse(habit.getId());
    }

    public void delete(Long id) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HABIT_NOT_FOUND));
        habitRepository.delete(habit);
    }
}
