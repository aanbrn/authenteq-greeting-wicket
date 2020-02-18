package com.authenteq.greeting.behaviors;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;

import static java.lang.String.format;
import static org.apache.wicket.markup.head.OnLoadHeaderItem.forScript;

public final class FocusOnLoadBehavior extends Behavior {

    @Override
    public void renderHead(final Component component, final IHeaderResponse response) {
        response.render(forScript(format("document.getElementById('%s').focus()",
                component.getMarkupId())));
    }

}
