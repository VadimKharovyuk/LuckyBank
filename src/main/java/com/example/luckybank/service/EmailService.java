package com.example.luckybank.service;
import com.example.luckybank.model.Client;
import com.example.luckybank.сonfiguration.EmailProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {
    private final EmailProducer emailProducer;

    public void sendRegistrationConfirmationEmail(String recipientEmail, Client newClient) {
        if (recipientEmail == null || newClient == null) {
            throw new IllegalArgumentException("recipientEmail and newClient must not be null");
        }

        String subject = "Регистрация успешно завершена";
        String messageText = "Уважаемый " + newClient.getName() + ",\n\n";
        messageText += "Спасибо за регистрацию в нашем банке!\n";
        messageText += "Ваша регистрация успешно завершена.\n\n";
        messageText += "Детали вашего аккаунта:\n";
        messageText += "Имя: " + newClient.getName() + "\n";
        messageText += "Фамилия: " + newClient.getLastName() + "\n";
        messageText += "Адрес: " + newClient.getAddress() + "\n";
        messageText += "Email: " + newClient.getEmail() + "\n\n";
        messageText += "С уважением,\nВаш Lucky Bank";

        EmailMessage emailMessage = new EmailMessage(recipientEmail, subject, messageText);
        emailProducer.sendEmailMessage(emailMessage);
    }
}
