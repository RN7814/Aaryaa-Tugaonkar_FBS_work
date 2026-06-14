package com.nexaanova.crm.controller;
import com.nexaanova.crm.dto.FollowupRequest;
import com.nexaanova.crm.model.Followup;
import com.nexaanova.crm.service.FollowupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/followups")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FollowupController {

    private final FollowupService followupService;

    @PostMapping
    public ResponseEntity<Followup> schedule(@RequestBody FollowupRequest req,
                                             @RequestParam Long counselorId) {
        return ResponseEntity.ok(followupService.schedule(req, counselorId));
    }

    @GetMapping("/today")
    public ResponseEntity<List<Followup>> today() {
        return ResponseEntity.ok(followupService.getTodayFollowups());
    }

    @GetMapping("/counselor/{id}")
    public ResponseEntity<List<Followup>> byCounselor(@PathVariable Long id) {
        return ResponseEntity.ok(followupService.getByCounselor(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Followup> updateStatus(@PathVariable Long id,
                                                  @RequestParam String status,
                                                  @RequestParam(required=false) String remarks) {
        return ResponseEntity.ok(followupService.updateStatus(id, status, remarks));
    }
}