package com.cheise_proj.demowebhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
record MessagePayload(
        @JsonProperty("template")
        Template template,
        @JsonProperty("messaging_product")
        String messagingProduct,
        @JsonProperty("to")
        String to,
        @JsonProperty("type")
        String type
) {
}

