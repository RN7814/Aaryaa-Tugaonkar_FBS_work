package com.nexaanova.crm.controller;
import com.nexaanova.crm.dto.EnquiryRequest;
import com.nexaanova.crm.model.Enquiry;
import com.nexaanova.crm.model.EnquiryStatus;
import com.nexaanova.crm.service.EnquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/enquiries")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EnquiryController {

    private final EnquiryService enquiryService;

    @PostMapping
    public ResponseEntity<Enquiry> create(@RequestBody EnquiryRequest req) {
        return ResponseEntity.ok(enquiryService.createEnquiry(req));
    }

    @GetMapping
    public ResponseEntity<List<Enquiry>> getAll() {
        return ResponseEntity.ok(enquiryService.getAll());
    }

    @GetMapping("/counselor/{id}")
    public ResponseEntity<List<Enquiry>> byCounselor(@PathVariable Long id) {
        return ResponseEntity.ok(enquiryService.getByCounselor(id));
    }

    @PutMapping("/{id}/assign/{counselorId}")
    public ResponseEntity<Enquiry> assign(@PathVariable Long id, @PathVariable Long counselorId) {
        return ResponseEntity.ok(enquiryService.assignCounselor(id, counselorId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Enquiry> updateStatus(@PathVariable Long id, @RequestParam EnquiryStatus status) {
        return ResponseEntity.ok(enquiryService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enquiryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}