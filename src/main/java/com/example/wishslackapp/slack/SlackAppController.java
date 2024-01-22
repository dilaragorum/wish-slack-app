package com.example.wishslackapp.slack;

import com.example.wishslackapp.client.kafka.KafkaProducer;
import com.example.wishslackapp.home.AppHomeOpenedListener;
import com.example.wishslackapp.wish.listeners.WishBlockActionListener;
import com.example.wishslackapp.wish.listeners.WishViewSubmissionListener;
import com.slack.api.bolt.App;
import com.slack.api.bolt.jakarta_servlet.SlackAppServlet;
import com.slack.api.model.event.AppHomeOpenedEvent;
import jakarta.servlet.annotation.WebServlet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import static com.example.wishslackapp.home.AppHomeOpenedListener.APP_HOME_ACTION_ID_FOR_SELECT_WISH;
import static com.example.wishslackapp.wish.listeners.View.CALLBACK_ACTION_FOR_SELECT_WISH;


@WebServlet("/slack/events")
@Component
public class SlackAppController extends SlackAppServlet {
    public SlackAppController(App app, KafkaProducer kafkaProducer) {
        super(app);

        app.event(AppHomeOpenedEvent.class, new AppHomeOpenedListener());
        app.blockAction(APP_HOME_ACTION_ID_FOR_SELECT_WISH, new WishBlockActionListener());
        app.viewSubmission(CALLBACK_ACTION_FOR_SELECT_WISH, new WishViewSubmissionListener(app, kafkaProducer));
    }
}


