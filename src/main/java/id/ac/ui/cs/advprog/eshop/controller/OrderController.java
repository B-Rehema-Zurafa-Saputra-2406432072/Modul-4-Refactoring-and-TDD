package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public String createOrderPage() {
        return "order/createOrder";
    }

    @GetMapping("/history")
    public String historyOrderPage() {
        return "order/historyOrder";
    }

    @PostMapping("/history")
    public String postHistoryOrder(@RequestParam("author") String author, Model model) {
        model.addAttribute("orders", orderService.findAllByAuthor(author));
        model.addAttribute("author", author);
        return "order/listOrder";
    }

    @GetMapping("/pay/{orderId}")
    public String payOrderPage(@PathVariable String orderId, Model model) {
        model.addAttribute("orderId", orderId);
        return "order/payOrder";
    }

    @PostMapping("/pay/{orderId}")
    public String processPaymentRequest(@PathVariable String orderId, Model model) {
        String generatedPaymentId = "PAY-" + UUID.randomUUID().toString().substring(0, 8);

        model.addAttribute("orderId", orderId);
        model.addAttribute("paymentId", generatedPaymentId);
        return "order/payOrder";
    }
}
