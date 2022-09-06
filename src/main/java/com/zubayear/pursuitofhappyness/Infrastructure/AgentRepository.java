package com.zubayear.pursuitofhappyness.Infrastructure;

import com.zubayear.pursuitofhappyness.Core.AgentAggregate.Agent;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AgentRepository extends ReactiveCrudRepository<Agent, Long> {
    @Modifying
    @Query("UPDATE agent SET active=0 WHERE id=:id")
    Mono<Void> updateActive(Long id);
}

