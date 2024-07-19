package org.alsjava.microservice.domain;

import jakarta.persistence.*;
import lombok.*;
import org.alsjava.microservice.enums.UserType;
import org.alsjava.microservice.model.UserDTO;
import org.alsjava.microservice.model.converter.UserTypeConverter;
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
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @Column
    private String name;

    @Column(length = 500)
    private String description;

    @Convert(converter = UserTypeConverter.class)
    @Column
    private UserType userType;

    public UserDTO toDTO() {
        return UserDTO.builder()
                .id(id)
                .name(name)
                .description(description)
                .userType(userType)
                .build();
    }
}
