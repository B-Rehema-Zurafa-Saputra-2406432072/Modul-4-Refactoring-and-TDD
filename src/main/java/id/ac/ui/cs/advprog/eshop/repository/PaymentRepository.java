package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();

    public Payment save(Payment payment) {
        for (int i = 0; i < paymentData.size(); i++) {
            Payment savedPayment = paymentData.get(i);
            if (savedPayment.getId().equals(payment.getId())) {
                // kalau payment exists, update
                paymentData.set(i, payment);
                return payment;
            }
        }
        // kalau gak ada, new
        paymentData.add(payment);
        return payment;
    }

    public Payment findById(String id) {
        for (Payment savedPayment : paymentData) {
            if (savedPayment.getId().equals(id)) {
                return savedPayment;
            }
        }
        return null;
    }

    public List<Payment> getAllPayments() {
        return paymentData;
    }
}
