package com.nexaanova.crm.controller;
import com.nexaanova.crm.dto.AdmissionRequest;
import com.nexaanova.crm.model.Admission;
import com.nexaanova.crm.service.AdmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admissions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdmissionController {

    private final AdmissionService admissionService;

    @PostMapping
    public ResponseEntity<Admission> convert(@RequestBody AdmissionRequest req,
                                             @RequestParam Long counselorId) {
        return ResponseEntity.ok(admissionService.convert(req, counselorId));
    }

    @GetMapping
    public ResponseEntity<List<Admission>> getAll() {
        return ResponseEntity.ok(admissionService.getAll());
    }
}