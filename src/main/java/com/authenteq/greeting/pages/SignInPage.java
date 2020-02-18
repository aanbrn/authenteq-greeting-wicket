package com.authenteq.greeting.pages;

import com.authenteq.greeting.components.SignInForm;
import com.authenteq.greeting.models.Credentials;
import com.authenteq.greeting.resources.CssReferences;
import org.apache.wicket.ajax.AjaxClientInfoBehavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.CompoundPropertyModel;

public final class SignInPage extends WebPage {

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(CssReferences.BOOTSTRAP));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new SignInForm("signIn", new CompoundPropertyModel<>(new Credentials())));
        add(new AjaxClientInfoBehavior());
    }

}
