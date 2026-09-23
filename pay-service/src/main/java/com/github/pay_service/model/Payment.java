package com.github.pay_service.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    name = "Payment",
    description = "Represents a payment transaction made by a user that includes multiple coffee items."
)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
        description = "Unique identifier of the payment transaction.",
        example = "35"
    )
    private Long id;

    @Column(nullable = false)
    @Schema(
        description = "Email of the user making the payment.",
        example = "user@example.com"
    )
    private String userEmail;

    @Column(nullable = false)
    @Schema(
        description = "Total price of the payment in Chilean Pesos (CLP).",
        example = "8400"
    )
    private Integer totalPrice;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    @Schema(
        description = "List of purchased items included in the payment."
    )
    private List<PaymentItem> items;
}
