package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    void testDetailForm() throws Exception {
        mockMvc.perform(get("/payment/detail"))
                .andExpect(status().isOk())
                .andExpect(view().name("payment/detailForm"));
    }

    @Test
    void testPaymentDetail() throws Exception {
        Payment mockPayment = new Payment("pay-1", null, "VOUCHER", new HashMap<>());
        when(paymentService.getPayment("pay-1")).thenReturn(mockPayment);

        mockMvc.perform(get("/payment/detail/pay-1"))
                .andExpect(status().isOk())
                .andExpect(view().name("payment/paymentDetail"))
                .andExpect(model().attributeExists("payment"));
    }

    @Test
    void testAdminList() throws Exception {
        when(paymentService.getAllPayments()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/payment/admin/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("payment/adminList"))
                .andExpect(model().attributeExists("payments"));
    }

    @Test
    void testAdminDetail() throws Exception {
        Payment mockPayment = new Payment("pay-1", null, "VOUCHER", new HashMap<>());
        when(paymentService.getPayment("pay-1")).thenReturn(mockPayment);

        mockMvc.perform(get("/payment/admin/detail/pay-1"))
                .andExpect(status().isOk())
                .andExpect(view().name("payment/adminDetail"))
                .andExpect(model().attributeExists("payment"));
    }

    @Test
    void testSetStatus() throws Exception {
        Payment mockPayment = new Payment("pay-1", null, "VOUCHER", new HashMap<>());
        when(paymentService.getPayment("pay-1")).thenReturn(mockPayment);

        // Submitting the form should process the status and redirect back to the list
        mockMvc.perform(post("/payment/admin/set-status/pay-1").param("status", "SUCCESS"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/payment/admin/list"));

        verify(paymentService, times(1)).setStatus(mockPayment, "SUCCESS");
    }
}
