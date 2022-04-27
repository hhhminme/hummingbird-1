package com.hummingbird.backend.order.dto;

import com.hummingbird.backend.food.domain.Food;
import com.hummingbird.backend.order.domain.Order;
import com.hummingbird.backend.order.domain.OrderItem;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemInfo {

    private String fileName;
    private String foodName;
    private Long foodId;
    private int foodPrice;
    private int count;

    public int calOrderPrice() {
        return foodPrice * count;
    }

    public OrderItemInfo(String fileName, String foodName, Long foodId, int foodPrice, int count) {
        this.fileName = fileName;
        this.foodName = foodName;
        this.foodId = foodId;
        this.foodPrice = foodPrice;
        this.count = count;
    }

    public OrderItem convertToOrderItem(){
        return OrderItem.builder().
                build();
    }
}
