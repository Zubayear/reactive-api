package com.zubayear.pursuitofhappyness.Web.Endpoints.AgentEndpoints.List;

import com.zubayear.pursuitofhappyness.Core.AgentAggregate.Agent;
import com.zubayear.pursuitofhappyness.Infrastructure.AgentRepository;
import com.zubayear.pursuitofhappyness.Web.Endpoints.AgentEndpoints.AgentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ListAgent {
    @Autowired
    private AgentRepository agentRepository;

    /**
     * This is a handler function i.e. it takes Mono<ServerResponse>
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAgentList(ServerRequest request) {
        Flux<AgentDTO> agentsToReturn = agentRepository.findAll()
                .map(agent -> new AgentDTO(agent.getId(), agent.getName()));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(agentsToReturn, AgentDTO.class);
    }
}
