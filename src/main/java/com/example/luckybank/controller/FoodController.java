//package com.example.luckybank.controller;
//
//import com.example.luckybank.model.Client;
//import com.example.luckybank.model.Food;
//import com.example.luckybank.service.ClientService;
//import com.example.luckybank.service.FoodService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@Controller
//@AllArgsConstructor
//
//public class FoodController {
//    private final FoodService foodService;
//    private final ClientService clientService;
//
//    @GetMapping("/shop")
//
//    public String showFoodList(Model model) {
//        List<Food> foodList = foodService.getAllFood();
//        List<Client> clientList = clientService.getAllClients(); // Предполагая, что у вас есть метод для получения всех клиентов
//        model.addAttribute("foodList", foodList);
//        model.addAttribute("clientList", clientList); // Добавляем список клиентов в модель
//        return "foodList"; // Возвращаем имя HTML-шаблона для отображения списка еды
//    }
//
//    @PostMapping("/buy")
//    public String buyFood(@RequestParam Long foodId, @RequestParam Long clientId, RedirectAttributes redirectAttributes) {
//        // Получаем выбранный продукт
//        Food food = foodService.getFoodById(foodId);
//
//        // Получаем выбранного клиента
//        Client client = clientService.getClientById(clientId);
//
//        // Получаем баланс клиента
//        BigDecimal balanceDecimal = client.getBalance();
//
//        // Проверяем, что баланс клиента не null и не пуст
//        if (balanceDecimal != null) {
//            // Сравниваем баланс с ценой продукта
//            if (balanceDecimal.compareTo(food.getPrice()) >= 0) {
//                // Вычитаем стоимость продукта из баланса клиента
//                BigDecimal newBalance = balanceDecimal.subtract(food.getPrice());
//                client.setBalance(newBalance);
//
//                // Обновляем данные клиента в базе данных
//                clientService.updateClient(client);
//
//                // Выполняем другие действия, связанные с покупкой (например, добавляем продукт в историю покупок клиента)
//
//                // Перенаправляем пользователя на страницу магазина с сообщением об успешной покупке
//                redirectAttributes.addFlashAttribute("successMessage", "Покупка успешно совершена!");
//            } else {
//                // Если у клиента недостаточно средств, перенаправляем его на страницу магазина с сообщением об ошибке
//                redirectAttributes.addFlashAttribute("errorMessage", "У вас недостаточно средств для покупки этого продукта!");
//            }
//        } else {
//            // Обработка случаев, когда баланс пуст или null
//            redirectAttributes.addFlashAttribute("errorMessage", "Баланс клиента не указан или пуст");
//        }
//
//        return "redirect:/shop";
//    }
//
//
//
//
//}
//
//
//
//
//
//
//
//
//
//
