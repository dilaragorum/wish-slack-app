package com.example.wishslackapp.wish.listeners;

import com.example.wishslackapp.client.kafka.KafkaMessage;
import com.example.wishslackapp.client.kafka.KafkaProducer;
import com.example.wishslackapp.client.kafka.WishEvent;
import com.example.wishslackapp.wish.helper.ExtractWishFromView;
import com.example.wishslackapp.wish.model.Wish;
import com.slack.api.bolt.App;
import com.slack.api.bolt.context.builtin.ViewSubmissionContext;
import com.slack.api.bolt.handler.builtin.ViewSubmissionHandler;
import com.slack.api.bolt.request.builtin.ViewSubmissionRequest;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.wishslackapp.wish.listeners.View.ACTION_ID__FOR_SELECT_COUNTRY;
import static com.example.wishslackapp.wish.listeners.View.ACTION_ID__FOR_SELECT_WISH;
import static com.example.wishslackapp.wish.model.mapper.WishMapper.MAPPER;

@Component
public class WishViewSubmissionListener implements ViewSubmissionHandler {
    private final App app;

    private final KafkaProducer kafkaProducer;

    public WishViewSubmissionListener(App app, KafkaProducer kafkaProducer) {
        this.app = app;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public Response apply(ViewSubmissionRequest viewSubmissionRequest, ViewSubmissionContext viewSubmissionContext) throws IOException, SlackApiException {
        var values = viewSubmissionRequest.getPayload().getView().getState().getValues();

        Wish wishFromChild = ExtractWishFromView.getWishFromSelectedOptions(values, ACTION_ID__FOR_SELECT_WISH, ACTION_ID__FOR_SELECT_COUNTRY);

        WishEvent wishEventBody = MAPPER.wishToWishEvent(wishFromChild);

        KafkaMessage<WishEvent> shipmentEvent = KafkaMessage.<WishEvent>builder()
                .body(wishEventBody)
                .build();

        kafkaProducer.publish(shipmentEvent);

        String userId = viewSubmissionRequest.getPayload().getUser().getId();
        String message = String.format("Your wish is successfully received. You can track your gift with this id: %s " +
                "Never stop looking at the sky and wait for us !!", wishFromChild.getId());

        sendMessage(userId, message);
        return viewSubmissionContext.ack();
    }

    private void sendMessage(String userId, String message) throws IOException, SlackApiException {
        var chatPostMessageRequest = ChatPostMessageRequest.builder()
                .channel(userId)
                .text(message)
                .build();

        app.client().chatPostMessage(chatPostMessageRequest);
    }
}
