package com.cheise_proj.demowebhook;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Template(

	@JsonProperty("name")
	String name,

	@JsonProperty("language")
	Language language
) {
}
