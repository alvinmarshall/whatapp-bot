package com.cheise_proj.demowebhook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/webhook")
@Slf4j
class WebhookController {
    private final WhatsAppMessageService whatsAppMessageService;
    private final String verifyToken;
    private final List<String> foodItems = List.of("Pizza", "Burger", "Pasta", "Salad");

    WebhookController(WhatsAppMessageService whatsAppMessageService, @Value("${webhook.verify_token}") String verifyToken) {
        this.whatsAppMessageService = whatsAppMessageService;
        this.verifyToken = verifyToken;
    }

    @GetMapping
    ResponseEntity<String> verifyWebhook(
            @RequestParam(value = "hub.mode", required = false) String mode,
            @RequestParam(value = "hub.verify_token", required = false) String token,
            @RequestParam(value = "hub.challenge", required = false) String challenge) {
        if ("subscribe".equals(mode) && verifyToken.equals(token)) {
            return ResponseEntity.ok(challenge);
        }
        return ResponseEntity.status(403).body("Verification failed");
    }

    @PostMapping
    public ResponseEntity<String> handleIncomingWebhook(@RequestBody WebhookDto.Response payload) {
        log.info("Webhook payload received: {}", payload);
        List<WebhookDto.EntryItem> entry = payload.entry();
        String waId = "";
        if (!entry.isEmpty()) {
            List<WebhookDto.ChangesItem> changes = entry.getFirst().changes();
            if (!changes.isEmpty()) {
                WebhookDto.Value value = changes.getFirst().value();
                if (value != null) {
                    if (value.contacts() != null) {
                        waId = value.contacts().getFirst().waId();
                    } else if (value.statuses() != null) {
                        waId = value.statuses().getFirst().recipientId();
                    }
                    List<WebhookDto.MessagesItem> messages = value.messages();
                    if (messages != null && !messages.isEmpty()) {
                        WebhookDto.Text text = messages.getFirst().text();
                        if (text != null && text.body() != null) {
                            log.info("body received: {}", text.body());
                            Optional<String> stringOptional = processListFoodCommand(text.body());
                            if (stringOptional.isPresent()) {
                                log.info("food: {}", stringOptional.get());
                                String msg = "Here you go %s".formatted(stringOptional.get());
                                whatsAppMessageService.sendMessage(waId, msg);
                            } else {
                                whatsAppMessageService.sendMessage(waId, "Sorry, but there is no food for you");
                            }
                        }
                    }
                }
            }
        }

        return ResponseEntity.ok("Webhook received");
    }

    Optional<String> processListFoodCommand(String food) {
        if ("list food".equals(food)) {
            return Optional.of(String.join(" ", foodItems));
        }
        return foodItems.stream().filter(s -> s.equalsIgnoreCase(food)).findFirst();
    }

}
