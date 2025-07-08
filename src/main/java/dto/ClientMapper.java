package dto;

import com.tech.loans.entity.Client;

public class ClientMapper {
    public static Client toEntity(ClientDTO dto) {
        Client c = new Client();
        c.setName(dto.getName());
        c.setPhone(dto.getPhone());
        c.setEmail(dto.getEmail());
        c.setNationalId(dto.getNationalId());
        return c;
    }

    public static ClientDTO toDTO(Client c) {
        ClientDTO dto = new ClientDTO();
        dto.setName(c.getName());
        dto.setPhone(c.getPhone());
        dto.setEmail(c.getEmail());
        dto.setNationalId(c.getNationalId());
        return dto;
    }
}