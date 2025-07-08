package com.tech.loans.controller;

import com.tech.loans.entity.Client;
import com.tech.loans.service.ClientService;
import com.tech.loans.service.LoanService;
import dto.ClientDTO;
import dto.ClientMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody ClientDTO dto) {
        System.out.println("National Id: " + dto.getNationalId());
        Client client = clientService.createClient(ClientMapper.toEntity(dto));
        return ResponseEntity.ok(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO dto) {
        Client updated = clientService.updateClient(id, ClientMapper.toEntity(dto));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok("Client deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }
}
