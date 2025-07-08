package com.tech.loans.service;

import com.tech.loans.entity.Client;
import com.tech.loans.entity.Loan;
import com.tech.loans.repository.ClientRepository;
import com.tech.loans.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LoanRepository loanRepository;

    // Create new client
    public Client createClient(Client client) {
        if (clientRepository.existsByNationalId(client.getNationalId())) {
            throw new RuntimeException("Client with this national ID already exists");
        }
        return clientRepository.save(client);
    }

    // Update existing client
    public Client updateClient(Long id, Client updatedClient) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        client.setName(updatedClient.getName());
        client.setEmail(updatedClient.getEmail());
        client.setPhone(updatedClient.getPhone());
        return clientRepository.save(client);
    }

    // Delete client only if no active loans
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        boolean hasActiveLoans = loanRepository.findAll().stream()
                .anyMatch(loan -> loan.getClient().getId().equals(id) && loan.getStatus() == Loan.Status.ACTIVE);

        if (hasActiveLoans) {
            throw new RuntimeException("Cannot delete client with active loans");
        }
        clientRepository.delete(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}