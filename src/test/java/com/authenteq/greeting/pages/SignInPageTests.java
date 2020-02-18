package com.authenteq.greeting.pages;

import com.authenteq.greeting.GreetingProperties;
import com.authenteq.greeting.WicketApplication;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

public class SignInPageTests extends BaseTests {

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
    public void shouldRenderSignInForm() {
        tester.startPage(SignInPage.class);
        tester.assertRenderedPage(SignInPage.class);
        assertEquals("Username", tester.getComponentFromLastRenderedPage("signIn:usernameLabel")
                                       .getDefaultModelObjectAsString());
        tester.assertComponent("signIn:username", TextField.class);
        assertEquals("Password", tester.getComponentFromLastRenderedPage("signIn:passwordLabel")
                                       .getDefaultModelObjectAsString());
        tester.assertComponent("signIn:password", PasswordTextField.class);
    }

    @Test
    public void shouldRedirectToHomeAfterSubmit() {
        given(greetingProperties.getName()).willReturn("Test");

        tester.startPage(SignInPage.class);
        tester.assertRenderedPage(SignInPage.class);

        final FormTester formTester = tester.newFormTester("signIn");
        formTester.setValue("username", "test");
        formTester.setValue("password", "test");
        formTester.submit("signIn");

        tester.assertRenderedPage(HomePage.class);
    }

}
