package com.zubayear.pursuitofhappyness.Web.Api;

import com.zubayear.pursuitofhappyness.Core.AgentAggregate.Complaint;
import com.zubayear.pursuitofhappyness.Infrastructure.ComplaintRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/agents/{agentId}/complaints")
@Slf4j
public class ComplaintController {
    private final ComplaintRepository complaintRepository;

    public ComplaintController(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @PostMapping("")
    public Mono<ResponseEntity<Complaint>> createComplaint(@PathVariable Long agentId,
                                                           @RequestBody Complaint complaint) {
        log.info("Received request to create complaint:{} with agentId: {}", complaint, agentId);
        complaint.setAgentId(agentId);
        return complaintRepository.save(complaint)
                .map(res -> ResponseEntity.status(HttpStatus.CREATED).body(res));

    }

    @GetMapping("")
    public Flux<Complaint> getComplaints(@PathVariable Long agentId) {
        log.info("Received request to get complaints with agentId: {}", agentId);
        return complaintRepository.findComplaintByAgentId(agentId);
    }

    @PutMapping("{complaintId}")
    public Mono<ResponseEntity<Void>> updateComplaint(@PathVariable Long agentId,
                                                      @PathVariable Long complaintId,
                                                      @RequestBody Complaint complaint) {
        log.info("Received request to update complaint: {} with agentId: {}", agentId, complaint);
        return complaintRepository.findById(complaintId)
                .flatMap(existingComplaint -> {
                    existingComplaint.setComplaintStatus(complaint.getComplaintStatus());
                    existingComplaint.setSla(complaint.getSla());
                    existingComplaint.setDescription(complaint.getDescription());
                    existingComplaint.setAgentId(agentId);
                    return complaintRepository.save(existingComplaint);
                })
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> completeComplaint(@PathVariable Long id) {
        log.info("Received request to complete complaint with id: {}", id);
        // can keep a check to see if agent exists or not
        return complaintRepository.findById(id)
                .flatMap(val -> complaintRepository.completeComplaint(val.getId()))
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Void>> updateComplaint(@PathVariable Long id,
                                                      @RequestBody Complaint complaint,
                                                      @PathVariable Long agentId) {
        log.info("Received request to update complaint{} with id: {}", complaint, id);
        return complaintRepository.findById(id)
                .flatMap(existingComplaint -> {
                    existingComplaint.setAgentId(agentId);
                    existingComplaint.setComplaintStatus(complaint.getComplaintStatus());
                    existingComplaint.setSla(complaint.getSla());
                    existingComplaint.setDescription(complaint.getDescription());
                    return complaintRepository.save(existingComplaint);
                })
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Complaint>> getComplaint(@PathVariable Long id) {
        log.info("Received request to get complaint with id: {}", id);
        return complaintRepository.findById(id)
                .map(ResponseEntity::ok);
    }
}
