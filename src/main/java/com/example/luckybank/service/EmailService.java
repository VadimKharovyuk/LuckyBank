package com.example.luckybank.service;

import com.example.luckybank.model.Client;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    public void sendRegistrationConfirmationEmail(String recipientEmail, Client newClient) {
        if (javaMailSender == null || recipientEmail == null || newClient == null) {
            throw new IllegalArgumentException("javaMailSender, recipientEmail, and newClient must not be null");
        }

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(recipientEmail);
            helper.setSubject("Регистрация успешно завершена");
            String messageText = "Уважаемый " + newClient.getName() + ",\n\n";
            messageText += "Спасибо за регистрацию в нашем банке!\n";
            messageText += "Ваша регистрация успешно завершена.\n\n";
            messageText += "Детали вашего аккаунта:\n";
            messageText += "Имя: " + newClient.getName() + "\n";
            messageText += "Фамилия: " + newClient.getLastName() + "\n";
            messageText += "Адрес: " + newClient.getAddress() + "\n";
            messageText += "Email: " + newClient.getEmail() + "\n\n";
            messageText += "С уважением,\nВаш Lucky Bank";
            helper.setText(messageText);
        } catch (MessagingException e) {
            // Обработка ошибки отправки письма
            e.printStackTrace();
        }
        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            // Обработка ошибки отправки письма
            e.printStackTrace();
        }
    }
}
