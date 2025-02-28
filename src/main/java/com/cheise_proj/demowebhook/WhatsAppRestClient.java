package com.cheise_proj.demowebhook;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "whatsAppRestClient", url = "${whatsAppClient.endpoint}")
interface WhatsAppRestClient {
    @PostMapping("{phoneId}/messages")
    ResponseEntity<Map<String, Object>> sendMessage(
            @RequestBody MessagePayload payload,
            @PathVariable("phoneId") String phoneId,
            @RequestHeader("Authorization") String accessToken
    );
}
