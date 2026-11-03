package com.github.pay_service.event;
import java.util.List;
import com.github.pay_service.model.Payment;

public record PaymentCreatedEvent(Long paymentId,String userEmail,Integer totalPrice,List<Item> items) {
    public record Item(String coffeeName, Integer quantity, Integer priceCLP){}

     public static PaymentCreatedEvent from(Payment p) {
        List<Item> items = p.getItems().stream()
                .map(i -> new Item(i.getCoffeeName(), i.getQuantity(), i.getPriceCLP()))
                .toList();
        return new PaymentCreatedEvent(p.getId(), p.getUserEmail(), p.getTotalPrice(), items);
    }

}
