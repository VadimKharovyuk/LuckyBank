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

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final CardRepository cardRepository;

    public Client createClient(Client client) {
        // Добавьте здесь логику для создания клиента
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

