package com.cheise_proj.demowebhook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
class WhatsAppMessageService {

    private final String phoneNumberId;
    private final String accessToken;
    private final WhatsAppRestClient whatsAppRestClient;

    WhatsAppMessageService(
            @Value("${whatsAppClient.phoneId}") String phoneNumberId,
            @Value("${whatsAppClient.verificationToken}") String accessToken,
            WhatsAppRestClient whatsAppRestClient
    ) {
        this.phoneNumberId = phoneNumberId;
        this.accessToken = accessToken;
        this.whatsAppRestClient = whatsAppRestClient;
    }

    void sendMessage(String recipientWaId, String message) {
        MessagePayload payload = MessagePayload.builder()
                .messagingProduct("whatsapp")
                .to(recipientWaId)
                .type("template")
                .template(Template.builder()
                        .name("auto_pay_reminder_3")
                        .language(Language.builder().code("en_US").build())
                        .components(
                                Collections.singletonList(
                                        Map.of("type", "body",
                                                "parameters", Collections.singletonList(
                                                        Map.of("type", "text", "text", message)
                                                )
                                        )
                                )
                        )
                        .build())
                .build();

        ResponseEntity<Map<String, Object>> responseEntity = whatsAppRestClient
                .sendMessage(payload, phoneNumberId, "Bearer " + accessToken);
        log.info("message: {}", payload);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            log.info("resp: {}", Objects.requireNonNull(responseEntity.getBody()));
            responseEntity.getBody();
            return;
        }
        log.info("resp: {}", Objects.requireNonNull(responseEntity.getBody()));
    }
}
