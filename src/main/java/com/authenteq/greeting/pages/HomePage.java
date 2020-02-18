package com.authenteq.greeting.pages;

import com.authenteq.greeting.GreetingProperties;
import com.authenteq.greeting.resources.CssReferences;
import com.authenteq.greeting.security.UserRoles;
import com.authenteq.greeting.security.UserWebSession;
import org.apache.wicket.Page;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

@AuthorizeInstantiation(UserRoles.ADMIN)
public final class HomePage extends WebPage {

    @SpringBean
    private GreetingProperties greetingProperties;

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(CssReferences.BOOTSTRAP));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("greeting", new StringResourceModel("greeting", Model.of(greetingProperties.getName()))));
        add(new Link<Page>("signOut") {
            @Override
            public void onClick() {
                UserWebSession.get().signOut();

                setResponsePage(HomePage.class);
            }
        });
    }

}
