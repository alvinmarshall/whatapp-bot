package com.cheise_proj.demowebhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
record Language(
        @JsonProperty("code")
        String code
) {
}
