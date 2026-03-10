package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import id.ac.ui.cs.advprog.eshop.service.OrderService;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void testCreateOrderPage() throws Exception {
        mockMvc.perform(get("/order/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("order/createOrder"));
    }

    @Test
    void testHistoryOrderPage() throws Exception {
        mockMvc.perform(get("/order/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("order/historyOrder"));
    }

    @Test
    void testPostHistoryOrder() throws Exception {
        String author = "Safira Sudrajat";
        when(orderService.findAllByAuthor(author)).thenReturn(new ArrayList<>());

        mockMvc.perform(post("/order/history").param("author", author))
                .andExpect(status().isOk())
                .andExpect(view().name("order/listOrder"))
                .andExpect(model().attributeExists("orders"))
                .andExpect(model().attributeExists("author"));
    }

    @Test
    void testGetPayOrderPage() throws Exception {
        mockMvc.perform(get("/order/pay/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("order/payOrder"))
                .andExpect(model().attributeExists("orderId"));
    }

    @Test
    void testPostPayOrder() throws Exception {
        mockMvc.perform(post("/order/pay/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("order/payOrder"))
                .andExpect(model().attributeExists("paymentId"));
    }
}