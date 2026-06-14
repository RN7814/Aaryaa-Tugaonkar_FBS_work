package com.nexaanova.crm.controller;
import com.nexaanova.crm.dto.ReportSummary;
import com.nexaanova.crm.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/summary")
    public ResponseEntity<ReportSummary> summary() {
        return ResponseEntity.ok(reportService.getSummary());
    }
}