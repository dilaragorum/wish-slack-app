package com.example.wishslackapp.client.kafka;

import com.example.wishslackapp.wish.model.Gift;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishEvent {
    private String id;
    private Gift gift;
    private String receiverCountry;
}
