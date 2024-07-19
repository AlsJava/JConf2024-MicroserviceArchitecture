package org.alsjava.microservice.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserType {
    @JsonProperty("DEVELOPER") DEVELOPER,
    @JsonProperty("SUPPORT") SUPPORT,
    @JsonProperty("NORMAL") NORMAL
}
