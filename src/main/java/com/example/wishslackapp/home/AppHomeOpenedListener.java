package com.example.wishslackapp.home;

import com.slack.api.app_backend.events.payload.EventsApiPayload;
import com.slack.api.bolt.context.builtin.EventContext;
import com.slack.api.bolt.handler.BoltEventHandler;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.views.ViewsPublishResponse;
import com.slack.api.model.event.AppHomeOpenedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class AppHomeOpenedListener implements BoltEventHandler<AppHomeOpenedEvent> {
    public static final String APP_HOME_ACTION_ID_FOR_SELECT_WISH = "select-wish";
    public static final String APP_HOME_ACTION_ID_FOR_LEARN_STATUS = "learn-status";

    public static final String APP_HOME_VIEW = """
            {
                     	"type": "home",
                     	"blocks": [
                     		{
                     			"type": "actions",
                     			"elements": [
                     				{
                     					"type": "button",
                     					"text": {
                     						"type": "plain_text",
                     						"text": "Select Your Wish",
                     						"emoji": true
                     					},
                     					"action_id": "select-wish"
                     				},
                     				{
                     					"type": "button",
                     					"text": {
                     						"type": "plain_text",
                     						"text": "Learn Status of My Wish",
                     						"emoji": true
                     					},
                     					"action_id": "learn-status"
                     				}
                     			]
                     		}
                     	]
                     }
            """;

    @Override
    public Response apply(EventsApiPayload<AppHomeOpenedEvent> payload, EventContext ctx) throws SlackApiException, IOException {
        ViewsPublishResponse viewsPubRes =
                ctx.client().viewsPublish(r -> r.userId(payload.getEvent().getUser()).viewAsString(APP_HOME_VIEW));

        if (!viewsPubRes.isOk()) {
            ctx.logger.error(viewsPubRes.toString());
        }

        return ctx.ack();
    }

}
