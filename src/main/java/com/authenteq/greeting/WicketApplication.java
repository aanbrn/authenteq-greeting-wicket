package com.authenteq.greeting;

import com.authenteq.greeting.pages.HomePage;
import com.authenteq.greeting.pages.SignInPage;
import com.authenteq.greeting.security.UserWebSession;
import de.agilecoders.wicket.webjars.WicketWebjars;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WicketApplication extends AuthenticatedWebApplication {

    @NonNull
    private ApplicationContext applicationContext;

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return SignInPage.class;
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return UserWebSession.class;
    }

    @Override
    protected void init() {
        super.init();

        getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext, true));
        getMarkupSettings().setStripWicketTags(true);

        WicketWebjars.install(this);

        mountPage("home", HomePage.class);
        mountPage("sign-in", SignInPage.class);
    }

}
