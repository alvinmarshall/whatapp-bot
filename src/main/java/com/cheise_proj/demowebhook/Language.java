package com.cheise_proj.demowebhook;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Language(

	@JsonProperty("code")
	String code
) {
}
