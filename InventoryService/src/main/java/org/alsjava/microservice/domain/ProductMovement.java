package org.alsjava.microservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.alsjava.microservice.model.MovementType;
import org.alsjava.microservice.model.converter.MovementTypeConverter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "products_movement")
public class ProductMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column
    @Convert(converter = MovementTypeConverter.class)
    private MovementType movementType;

    @Column
    @JdbcTypeCode(Types.VARCHAR)
    private UUID owner;

    @Column
    @Positive
    private long stock;
}
