package com.example.wishslackapp.wish.model;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wish {
    private String id;
    private Gift gift;
    private String receiverCountry;
}
