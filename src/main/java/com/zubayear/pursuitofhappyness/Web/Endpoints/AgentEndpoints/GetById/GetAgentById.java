package com.zubayear.pursuitofhappyness.Web.Endpoints.AgentEndpoints.GetById;

import com.zubayear.pursuitofhappyness.Infrastructure.AgentRepository;
import com.zubayear.pursuitofhappyness.Web.Endpoints.AgentEndpoints.AgentDTO;
import com.zubayear.pursuitofhappyness.Web.Endpoints.AgentEndpoints.Create.CreateAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public class GetAgentById {
    @Autowired
    private AgentRepository agentRepository;

    public Mono<ServerResponse> getAgentById(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));
        return agentRepository.findById(id)
                .map(agent -> new AgentDTO(agent.getId(), agent.getName()))
                .flatMap(agent -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(agent)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
