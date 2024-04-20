package com.example.luckybank.service;

import com.example.luckybank.model.Card;
import com.example.luckybank.model.Client;
import com.example.luckybank.repositoty.CardRepository;
import com.example.luckybank.repositoty.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final CardRepository cardRepository;

    public Client createClient(Client client) {
        // Добавьте здесь логику для создания клиента
        return clientRepository.save(client);
    }

//    public Client getClientById(Long id) {
//        // Добавьте здесь логику для получения клиента по идентификатору
//        return clientRepository.findById(id).orElse(null);
//    }

    public Client getClientByName(String name) {
        return clientRepository.findByName(name);
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

//    public void updateClient(Client client) {
//        // Проверяем, что клиент существует в базе данных
//        if (client.getId() == null || !clientRepository.existsById(client.getId())) {
//            throw new EntityNotFoundException("Клиент не найден");
//        }
//
//        // Проверяем, что у клиента есть карта
//        if (client.getCards() == null || client.getCards().isEmpty()) {
//            throw new IllegalStateException("У клиента нет карты для совершения покупок");
//        }
//
//        // Выводим текущее значение баланса клиента перед обновлением
//        System.out.println("Текущий баланс клиента: " + client.getBalance());
//
//        // Выполняем обновление данных клиента в базе данных
//        clientRepository.save(client);
//
//        // Выводим новое значение баланса клиента после обновления
//        System.out.println("Новый баланс клиента: " + client.getBalance());
//    }


}

