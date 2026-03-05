package com.synapse.erp.service;

import com.synapse.erp.model.ErpOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ErpService {

    @Value("${synapse.api.url}")
    private String synapseApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedDelay = 30000)
    public void sendOrderToSynapse() {
        ErpOrder order = new ErpOrder(
            "CMD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(),
            "Steak haché 150g",
            100,
            "WMS"
        );

        Map<String, String> flux = new HashMap<>();
        flux.put("source", "ERP");
        flux.put("destination", "WMS");
        flux.put("payload", "{\"order_id\": \"" + order.getOrderId() + "\", \"product\": \"" + order.getProduct() + "\", \"quantity\": " + order.getQuantity() + "}");

        try {
            restTemplate.postForObject(synapseApiUrl, flux, Map.class);
            System.out.println("Commande envoyée à Synapse : " + order.getOrderId());
        } catch (Exception e) {
            System.err.println("Erreur envoi Synapse : " + e.getMessage());
        }
    }
}
