package com.example.luckybank.repositoty;

import com.example.luckybank.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository  extends JpaRepository<Client,Long> {
    Client findByName(String name);

}
