package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/detail")
    public String detailForm() {
        return "payment/detailForm";
    }

    @GetMapping("/detail/{paymentId}")
    public String paymentDetail(@PathVariable String paymentId, Model model) {
        Payment payment = paymentService.getPayment(paymentId);
        model.addAttribute("payment", payment);
        return "payment/paymentDetail";
    }

    @GetMapping("/admin/list")
    public String adminList(Model model) {
        model.addAttribute("payments", paymentService.getAllPayments());
        return "payment/adminList";
    }

    @GetMapping("/admin/detail/{paymentId}")
    public String adminDetail(@PathVariable String paymentId, Model model) {
        Payment payment = paymentService.getPayment(paymentId);
        model.addAttribute("payment", payment);
        return "payment/adminDetail";
    }

    @PostMapping("/admin/set-status/{paymentId}")
    public String setStatus(@PathVariable String paymentId, @RequestParam("status") String status) {
        Payment payment = paymentService.getPayment(paymentId);
        if (payment != null) {
            paymentService.setStatus(payment, status);
        }
        return "redirect:/payment/admin/list";
    }
}