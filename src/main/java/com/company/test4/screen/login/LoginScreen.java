package com.company.test4.screen.login;

import io.jmix.core.MessageTools;
import io.jmix.core.Messages;
import io.jmix.securityui.authentication.AuthDetails;
import io.jmix.securityui.authentication.LoginScreenSupport;
import io.jmix.ui.JmixApp;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.*;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;
import io.jmix.ui.security.UiLoginProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;

import java.util.Locale;

public class LoginScreen{

    @Autowired
    private LoginScreenSupport loginScreenSupport;

    @Autowired
    private UiLoginProperties loginProperties;

    @Autowired
    private JmixApp app;

    @Subscribe
    private void onInit() {
        loginScreenSupport.authenticate(
                AuthDetails.of(loginProperties.getDefaultUsername(), loginProperties.getDefaultPassword())
                        .withLocale(app.getLocale())
                        .withRememberMe(true), (FrameOwner) this);
    }
}
