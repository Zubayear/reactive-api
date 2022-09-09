package com.zubayear.pursuitofhappyness.Web.Endpoints.AgentEndpoints.Create;

import com.zubayear.pursuitofhappyness.Core.AgentAggregate.Agent;
import com.zubayear.pursuitofhappyness.Infrastructure.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CreateAgent {
    @Autowired
    private AgentRepository agentRepository;

    public Mono<ServerResponse> createAgent(ServerRequest request) {
        return request.bodyToMono(CreateAgentRequest.class)
                .map(r -> new Agent(null, r.getName(), true))
                .flatMap(createAgentRequest -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(agentRepository.save(createAgentRequest), CreateAgentResponse.class));
    }
}
