package com.nexaanova.crm.controller;
import com.nexaanova.crm.dto.CallRecordRequest;
import com.nexaanova.crm.model.CallRecord;
import com.nexaanova.crm.service.CallRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/calls")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CallRecordController {

    private final CallRecordService callRecordService;

    // POST /api/calls?counselorId=2
    @PostMapping
    public ResponseEntity<CallRecord> addCall(@RequestBody CallRecordRequest req,
                                              @RequestParam Long counselorId) {
        return ResponseEntity.ok(callRecordService.addCall(req, counselorId));
    }

    @GetMapping("/enquiry/{id}")
    public ResponseEntity<List<CallRecord>> byEnquiry(@PathVariable Long id) {
        return ResponseEntity.ok(callRecordService.getByEnquiry(id));
    }

    @GetMapping("/counselor/{id}")
    public ResponseEntity<List<CallRecord>> byCounselor(@PathVariable Long id) {
        return ResponseEntity.ok(callRecordService.getByCounselor(id));
    }
}