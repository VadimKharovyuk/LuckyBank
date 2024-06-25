package com.example.luckybank.service;

import com.example.luckybank.model.Card;
import com.example.luckybank.model.Client;
import com.example.luckybank.repositoty.CardRepository;
import com.example.luckybank.repositoty.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final CardRepository cardRepository;




    public List<Client> getAllClientsWithEmail() {
        return clientRepository.findAll().stream()
                .filter(client -> client.getEmail() != null && !client.getEmail().isEmpty())
                .collect(Collectors.toList());
    }



    public Client createClient(Client client) {
        return clientRepository.save(client);
    }


public Optional<Client> getClientByName(String name) {
    return clientRepository.findOneByName(name);
}


    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }


    public List<Card> getCardsByClientId(Long id) {
        return cardRepository.findByClientId(id);
    }
    public Client getClientById(Long id) {
        // Ваша логика для получения клиента по идентификатору
        return (Client) clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Клиент с id " + id + " не найден"));
    }





}

