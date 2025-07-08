package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClientDTO {
    @NotBlank
    private String nationalId;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String phone;
}