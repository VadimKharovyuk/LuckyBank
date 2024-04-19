package com.example.luckybank.service;
import com.example.luckybank.model.Card;
import com.example.luckybank.model.Client;
import com.example.luckybank.repositoty.CardRepository;
import com.example.luckybank.repositoty.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final  CardRepository cardRepository;
    public Client createClient(Client client) {
        // Добавьте здесь логику для создания клиента
        return clientRepository.save(client);
    }

    public Client getClientById(Long id) {
        // Добавьте здесь логику для получения клиента по идентификатору
        return clientRepository.findById(id).orElse(null);
    }

    public Client getClientByName(String name) {
        return clientRepository.findByName(name);
    }

    public List<Client> getAllClients() {
       return clientRepository.findAll();
    }


    public List<Card> getCardsByClientId(Long id) {
        return cardRepository.findByClientId(id);
    }
}
