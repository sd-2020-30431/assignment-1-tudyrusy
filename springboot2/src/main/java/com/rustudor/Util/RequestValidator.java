package com.rustudor.Util;

import com.rustudor.entity.Role;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class RequestValidator {
    public static boolean isUser(Session session){
        if (session.getRole().equals(Role.USER))
            return true;
        else
            return false;
    }

    public static boolean isAdmin(Session session){
        if (session.getRole().equals(Role.ADMIN))
            return true;
        else
            return false;
    }

    public static boolean isExpired(Session session){
        System.out.println(session.getCreationTime());
        System.out.println(session.getExpirationPeriod().toHours());
        System.out.println(session.getCreationTime().plus(session.getExpirationPeriod().toHours(), ChronoUnit.HOURS));
        System.out.println(session.getCreationTime());
        System.out.println(session.getCreationTime().plus(session.getExpirationPeriod().toHours(), ChronoUnit.HOURS).compareTo(Instant.now()));
        if (session.getCreationTime().plus(session.getExpirationPeriod().toHours(), ChronoUnit.HOURS).compareTo(Instant.now()) < 0)
            return true;
        else
            return false;
    }

    public static boolean validate(Session session){
        if (RequestValidator.isUser(session) && !RequestValidator.isExpired(session))
            return true;
        System.out.println(RequestValidator.isUser(session) +"   "+ !RequestValidator.isExpired(session));
        return false;
    }

}
