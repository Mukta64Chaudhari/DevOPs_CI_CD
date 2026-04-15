package com.library;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebListener;

@WebListener
public class SessionConfigListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SessionCookieConfig cookieConfig = sce.getServletContext().getSessionCookieConfig();
        boolean secure = "true".equalsIgnoreCase(System.getenv("SESSION_COOKIE_SECURE"));
        cookieConfig.setSecure(secure);
        cookieConfig.setHttpOnly(true);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // no-op
    }
}
