package com.authenteq.greeting.security;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import static java.util.Collections.singletonList;

public final class UserWebSession extends AuthenticatedWebSession {

    private Roles roles;

    public UserWebSession(final Request request) {
        super(request);
    }

    @Override
    public Roles getRoles() {
        return roles;
    }

    @Override
    public void signOut() {
        super.signOut();

        roles = null;
    }

    @Override
    protected boolean authenticate(final String username, final String password) {
        roles = new Roles(singletonList(UserRoles.ADMIN));
        return true;
    }

}
