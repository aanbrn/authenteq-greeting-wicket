package com.authenteq.greeting.pages;

import com.authenteq.greeting.GreetingProperties;
import com.authenteq.greeting.WicketApplication;
import com.authenteq.greeting.security.UserWebSession;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.BDDMockito.given;

public class HomePageTests extends BaseTests {

    @Mock
    private GreetingProperties greetingProperties;

    private WicketTester tester;

    @BeforeEach
    public void setUp() {
        final ApplicationContextMock applicationContextMock = new ApplicationContextMock();

        applicationContextMock.putBean(greetingProperties);

        tester = new WicketTester(new WicketApplication(applicationContextMock));
    }

    @Test
    public void shouldRequestSignIn() {
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(SignInPage.class);
    }

    @Test
    public void shouldRenderGreetingWhenSignedIn() {
        given(greetingProperties.getName()).willReturn("Test");

        final UserWebSession session = (UserWebSession) tester.getSession();
        session.signIn("test", "test");

        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
        tester.assertLabel("greeting", "Hello, Test!");
    }

}
