package com.free.freelance_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class EmailSenderService {

    private final String url = "localhost:8081/email";

    WebClient client = WebClient.create();

    public String sendConfirmRegistration(String email, String key, String userId, String userName, String role) {
        WebClient.ResponseSpec retrieve = client.get()
                .uri(uriBuilder -> uriBuilder.path(url + "/confirmRegistration")
                        .queryParam("email", email)
                        .queryParam("key", key)
                        .queryParam("userId", userId)
                        .queryParam("userName", userName)
                        .queryParam("role", role)
                        .build())
                .retrieve();
        return retrieve.bodyToMono(String.class).block();
    }
}
