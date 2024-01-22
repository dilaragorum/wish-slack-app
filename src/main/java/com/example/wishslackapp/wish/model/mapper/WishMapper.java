package com.example.wishslackapp.wish.model.mapper;

import com.example.wishslackapp.client.kafka.WishEvent;
import com.example.wishslackapp.wish.model.Wish;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WishMapper {
    WishMapper MAPPER = Mappers.getMapper(WishMapper.class);

    WishEvent wishToWishEvent(Wish wish);
}
