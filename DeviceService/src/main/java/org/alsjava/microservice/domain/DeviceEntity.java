package org.alsjava.microservice.domain;

import jakarta.persistence.*;
import lombok.*;
import org.alsjava.microservice.enums.DeviceType;
import org.alsjava.microservice.model.DeviceDTO;
import org.alsjava.microservice.model.converter.DeviceTypeConverter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Types;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "devices")
public class DeviceEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @Column
    private String name;

    @Column(length = 500)
    private String description;

    @Convert(converter = DeviceTypeConverter.class)
    @Column
    private DeviceType deviceType;

    @Column
    @JdbcTypeCode(Types.VARCHAR)
    private UUID userId;

    public DeviceDTO toDTO() {
        return DeviceDTO.builder()
                .id(id)
                .name(name)
                .description(description)
                .deviceType(deviceType)
                .build();
    }
}
