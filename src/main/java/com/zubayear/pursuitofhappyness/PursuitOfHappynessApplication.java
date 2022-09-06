package com.zubayear.pursuitofhappyness;

import com.zubayear.pursuitofhappyness.Web.Endpoints.AgentEndpoints.List.ListAgent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
//@EnableConfigurationProperties(CassandraConfig.class)
public class PursuitOfHappynessApplication {

    public static void main(String[] args) {
        SpringApplication.run(PursuitOfHappynessApplication.class, args);
    }

//    @Bean
//    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(CassandraConfig cassandraConfig) {
//        Path path = cassandraConfig.getSecureConnectBundle().toPath();
//        return cqlSessionBuilder -> cqlSessionBuilder.withCloudSecureConnectBundle(path);
//    }

    @Bean
    RouterFunction<ServerResponse> routerFunction(ListAgent listAgent) {
        return route()
                .GET("/agents", listAgent::getAgentList)
                .build();
    }

}
