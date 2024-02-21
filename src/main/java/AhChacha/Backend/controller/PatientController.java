package AhChacha.Backend.controller;

import AhChacha.Backend.dto.patient.request.PatientRequest;
import AhChacha.Backend.dto.patient.response.PatientResponse;
import AhChacha.Backend.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/{memberId}")
    public ResponseEntity<PatientResponse> savePatientInfo(@PathVariable("memberId") Long id, @RequestBody @Valid PatientRequest patientRequest) {
        return ResponseEntity.ok(patientService.savePatientInfo(id, patientRequest));
    }
}
