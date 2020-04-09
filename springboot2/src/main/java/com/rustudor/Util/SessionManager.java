package com.rustudor.Util;

import com.rustudor.Dto.TokenDto;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class SessionManager {
    private static Map<String,Session> sessionMap = new HashMap<String,Session>();

    private static SecureRandom random = new SecureRandom();

    public static String generateToken(String username ) {
        long longToken = Math.abs( random.nextLong() );
        String random = Long.toString( longToken, 16 );
        return random;
    }

    public static String add(Session session){
        String token = generateToken(session.getUsername());
        sessionMap.put(token,session);
        return token;
    }

    public static void delete(){
        //TODO
    }

    public static Map<String, Session> getSessionMap() {
        return sessionMap;
    }

    public static void printMap(){
        for (Session s : sessionMap.values()){
            System.out.println(s.toString());
        }
    }
}
