package com.offer.droolEngine.services;

import com.offer.droolEngine.entities.Order;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    @Autowired
    private KieContainer kieContainer;

    public Order applyRules(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order must not be null");
        }

        KieSession kieSession = kieContainer.newKieSession();
        try {
            kieSession.insert(order);
            kieSession.fireAllRules();
        } finally {
            kieSession.dispose(); // Prevent memory leaks
        }
        return order;
    }
}
