package dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class LoanDTO {
    @NotNull
    private Long clientId;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal amount;
    // Getters and setters
}