package com.authenteq.greeting.components;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class BootstrapFeedbackPanel extends FeedbackPanel {

    public BootstrapFeedbackPanel(final String id) {
        super(id);
    }

    @Override
    protected String getCSSClass(final FeedbackMessage message) {
        switch (message.getLevel()) {
            case FeedbackMessage.ERROR:
            case FeedbackMessage.FATAL:
                return "alert-danger";
            case FeedbackMessage.WARNING:
                return "alert-warning";
            case FeedbackMessage.SUCCESS:
                return "alert-success";
            default:
                return "alert-info";
        }
    }

}
