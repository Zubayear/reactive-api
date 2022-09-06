package com.zubayear.pursuitofhappyness.Web.Api;

import com.zubayear.pursuitofhappyness.Core.AgentAggregate.Agent;
import com.zubayear.pursuitofhappyness.Infrastructure.AgentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/agents")
@Slf4j
public class AgentsController /*extends BaseApiController*/ {
    private final AgentRepository agentRepository;

    public AgentsController(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @PostMapping
    public Mono<ResponseEntity<Agent>> createAgent(@RequestBody Agent agent) {
        log.info("Received request to create agent: {}", agent);
        Mono<Agent> savedAgent = agentRepository.save(agent);
        return savedAgent.map(val -> ResponseEntity.status(HttpStatus.CREATED).body(val));

    }

    @GetMapping("")
    public Flux<Agent> getAgents() {
        log.info("Received request to get all agents");
        return agentRepository.findAll();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Agent>> getAgent(@PathVariable Long id) {
        log.info("Received request to get agent with id: {}", id);
        return agentRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("{agentId}")
    public Mono<ResponseEntity<Void>> updateAgent(@PathVariable Long agentId, @RequestBody Agent agent) {
        log.info("Received request to update agent: {} with id: {}", agent, agentId);
        return agentRepository.findById(agentId)
                .flatMap(existingAgent -> {
                    existingAgent.setName(agent.getName());
                    return agentRepository.save(existingAgent);
                })
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

    @DeleteMapping("{agentId}")
    public Mono<ResponseEntity<Void>> deleteAgent(@PathVariable Long agentId) {
        log.info("Received request to delete agent with id: {}", agentId);
        return agentRepository.findById(agentId)
                .flatMap(val -> agentRepository.updateActive(val.getId())).then(Mono.just(ResponseEntity.noContent().build()));
    }
}
