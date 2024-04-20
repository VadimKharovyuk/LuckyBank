package com.example.luckybank.controller;

import com.example.luckybank.model.Client;
import com.example.luckybank.model.Food;
import com.example.luckybank.service.ClientService;
import com.example.luckybank.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor

public class FoodController {
    private final FoodService foodService ;
    private final ClientService clientService;
    @GetMapping("/shop")

    public String showFoodList(Model model) {
        List<Food> foodList = foodService.getAllFood();
        List<Client> clientList = clientService.getAllClients(); // Предполагая, что у вас есть метод для получения всех клиентов
        model.addAttribute("foodList", foodList);
        model.addAttribute("clientList", clientList); // Добавляем список клиентов в модель
        return "foodList"; // Возвращаем имя HTML-шаблона для отображения списка еды
    }

    @PostMapping("/buy")
    public String buyFood(@RequestParam Long foodId, @RequestParam Long clientId, RedirectAttributes redirectAttributes) {
        // Получаем выбранного продукт
        Food food = foodService.getFoodById(foodId);

        // Получаем выбранного клиента
        Client client = clientService.getClientById(clientId);
        String balanceStr = client.getBalance();

        if (balanceStr != null && !balanceStr.isEmpty()) {
            double balance = Double.parseDouble(balanceStr);
            // Сравниваем баланс с ценой продукта
            if (balance >= food.getPrice()) {
                // Вычитаем стоимость продукта из баланса клиента
                double newBalance = balance - food.getPrice();
                client.setBalance(String.valueOf(newBalance)); // Преобразуем новый баланс обратно в строку и устанавливаем его клиенту
                clientService.updateClient(client);

                // Выполняем другие действия, связанные с покупкой (например, добавляем продукт в историю покупок клиента)

                // Перенаправляем пользователя на страницу магазина с сообщением об успешной покупке
                redirectAttributes.addFlashAttribute("successMessage", "Покупка успешно совершена!");
            } else {
                // Если у клиента недостаточно средств, перенаправляем его на страницу магазина с сообщением об ошибке
                redirectAttributes.addFlashAttribute("errorMessage", "У вас недостаточно средств для покупки этого продукта!");
            }
        } else {
            // Обработка случаев, когда баланс пуст или null
        }
        return "redirect:/shop";
    }

}





