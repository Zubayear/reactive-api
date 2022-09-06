package com.zubayear.pursuitofhappyness.Infrastructure;

import com.zubayear.pursuitofhappyness.Core.AgentAggregate.Complaint;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ComplaintRepository extends ReactiveCrudRepository<Complaint, Long> {
    @Query(value = "SELECT * FROM complaint WHERE agent_id= :agentId")
    Flux<Complaint> findComplaintByAgentId(@Param("agentId") Long agentId);

    @Modifying
    @Query("UPDATE complaint SET complaint_status='COMPLETE' WHERE id = :id")
    Mono<Void> completeComplaint(Long id);
}
