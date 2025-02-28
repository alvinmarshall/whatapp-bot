package com.cheise_proj.demowebhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
record Template(
        @JsonProperty("name")
        String name,

        @JsonProperty("language")
        Language language,
        @JsonProperty("components")
        List<Map<String, Object>> components
) {
}
