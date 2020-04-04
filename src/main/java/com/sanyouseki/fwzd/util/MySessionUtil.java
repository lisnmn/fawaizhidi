package com.sanyouseki.fwzd.util;

import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MySessionUtil {
    private HttpSession session;

    private String sessionUsername;

    public MySessionUtil(HttpServletRequest request) {
        this.session = request.getSession(true);
        this.sessionUsername = (String) session.getAttribute("sessionUsername");
        if (this.sessionUsername == null) this.sessionUsername = "";
    }

    public boolean isSessionUsernameEmpty() {
        return sessionUsername.isEmpty();
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public String getSessionUsername() {
        return sessionUsername;
    }

    public void setSessionUsername(String sessionUsername) {
        session.setAttribute("sessionUsername", sessionUsername);
        this.sessionUsername = sessionUsername;
    }
}
