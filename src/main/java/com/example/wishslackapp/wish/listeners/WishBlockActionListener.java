package com.example.wishslackapp.wish.listeners;

import com.slack.api.bolt.context.builtin.ActionContext;
import com.slack.api.bolt.handler.builtin.BlockActionHandler;
import com.slack.api.bolt.request.builtin.BlockActionRequest;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.views.ViewsOpenRequest;
import com.slack.api.methods.request.views.ViewsUpdateRequest;
import com.slack.api.methods.response.views.ViewsOpenResponse;

import java.io.IOException;

import static com.example.wishslackapp.wish.listeners.View.SELECT_WISH_VIEW;

public class WishBlockActionListener implements BlockActionHandler {
    @Override
    public Response apply(BlockActionRequest blockActionRequest, ActionContext ctx) throws IOException, SlackApiException {
        ctx.logger.debug("Choice action clicked");

        // Opening modal
        ViewsOpenRequest viewsOpenRequest = ViewsOpenRequest.builder()
                .triggerId(ctx.getTriggerId())
                .viewAsString(SELECT_WISH_VIEW)
                .build();

        ViewsOpenResponse openResponse = ctx.client().viewsOpen(viewsOpenRequest);
        if (openResponse.getError() != null) {
            ctx.logger.error("Error during view opening: " + openResponse.getError());
        }

        ViewsUpdateRequest updateRequest = ViewsUpdateRequest.builder()
                .viewId(openResponse.getView().getId())
                .build();

        ctx.client().viewsUpdate(updateRequest);

        return ctx.ack();
    }
}
