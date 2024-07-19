package org.alsjava.microservice.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DeviceType {
    @JsonProperty("PC_DESKTOP") PC_DESKTOP,
    @JsonProperty("PHONE") PHONE,
    @JsonProperty("LAPTOP") LAPTOP,
    @JsonProperty("TABLET") TABLET
}
