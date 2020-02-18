package com.authenteq.greeting.components;

import com.authenteq.greeting.behaviors.FocusOnLoadBehavior;
import com.authenteq.greeting.models.Credentials;
import com.authenteq.greeting.pages.HomePage;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

public class SignInForm extends StatelessForm<Credentials> {

    public SignInForm(final String id, final IModel<Credentials> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new BootstrapFeedbackPanel("feedback"));

        final FormComponent<String> username = new TextField<String>("username").setRequired(true);
        username.setLabel(new StringResourceModel("username", username, null));
        username.setOutputMarkupId(true);
        username.add(new FocusOnLoadBehavior());
        add(new SimpleFormComponentLabel("usernameLabel", username));
        add(username);

        final FormComponent<String> password = new PasswordTextField("password");
        password.setLabel(new StringResourceModel("password", password, null));
        add(new SimpleFormComponentLabel("passwordLabel", password));
        add(password);

        add(new Button("signIn"));
    }

    @Override
    protected void onSubmit() {
        final AuthenticatedWebSession session = AuthenticatedWebSession.get();
        final Credentials credentials = getModelObject();

        if (session.signIn(credentials.getUsername(), credentials.getPassword())) {
            setResponsePage(HomePage.class);
        } else {
            error(getString("signIn.failed"));
        }
    }

}
