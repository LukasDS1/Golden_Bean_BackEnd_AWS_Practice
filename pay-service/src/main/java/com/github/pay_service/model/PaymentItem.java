package com.github.pay_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
@Schema(
    name = "PaymentItem",
    description = "Represents a purchased coffee item within a payment transaction."
)
public class PaymentItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
        description = "Unique identifier of the item.",
        example = "10"
    )
    private Long id;   

    @Column(nullable = false)
    @Schema(
        description = "Identifier of the purchased coffee.",
        example = "2"
    )
    private Long coffeeId;

    @Column(nullable = false)
    @Schema(
        description = "Name of the coffee purchased.",
        example = "Café Americano"
    )
    private String coffeeName;

    @Column(nullable = false)
    @Schema(
        description = "Quantity of the coffee purchased.",
        example = "3"
    )
    private Integer quantity;

    @Column(nullable = false)
    @Schema(
        description = "Unit price of the coffee in Chilean Pesos (CLP).",
        example = "2200"
    )
    private Integer priceCLP;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    @JsonIgnore
    @Schema(
        description = "The payment transaction to which this item belongs. Hidden in serialized responses."
    )
    private Payment payment;
}
