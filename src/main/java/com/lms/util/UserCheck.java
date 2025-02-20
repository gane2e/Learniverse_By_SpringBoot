package com.lms.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserCheck {

    public static boolean isUserLoggedIn() {

        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated())
            return true; //로그인
        else
            return false; //비로그인
    }

}
