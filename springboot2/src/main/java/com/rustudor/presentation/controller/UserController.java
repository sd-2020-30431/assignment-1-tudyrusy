package com.rustudor.presentation.controller;


import com.rustudor.Dto.*;
import com.rustudor.business.Services.UserService;
import com.rustudor.Util.DataValidator;
import com.rustudor.Util.RequestValidator;
import com.rustudor.Util.Session;
import com.rustudor.Util.SessionManager;
import com.rustudor.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/getRole")
    public ResponseEntity<Role> getRole(@RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(session.getRole(), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody FullUserDto fullUserDto) {
        System.out.println(fullUserDto);
        if (!DataValidator.validateUser(fullUserDto)) {
            System.out.println("user input error");
            return new ResponseEntity<>("INVALID DATA", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        switch (userService.register(fullUserDto)) {
            case 0:
                return new ResponseEntity<>("SUCCESS : USER REGISTERED", HttpStatus.OK);
            case -1:
                return new ResponseEntity<>("DUPLICATE", HttpStatus.CONFLICT);
            default:
                return new ResponseEntity<>("UNKNOWN ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getItems")
    public ResponseEntity<ArrayList<ItemDto1>> getAccounts(@RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null)
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            ArrayList<ItemDto1> itemDtos = userService.getItems(session);
            return new ResponseEntity<>(itemDtos, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/addItem")
    public ResponseEntity addItem(@RequestBody ItemDto itemDto, @RequestHeader("token") String token) {
        //validation
        if(DataValidator.validateItem(itemDto)) {
            Session session = SessionManager.getSessionMap().get(token);
            userService.addItem(itemDto, session);
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            System.out.println("item input error");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/setGoal")
    public ResponseEntity setGoal(@RequestBody String goal, @RequestHeader("token") String token) {
        //validation
        if(DataValidator.validateGoal(goal)) {
            Session session = SessionManager.getSessionMap().get(token);
            userService.setGoal(Integer.parseInt(goal), session);
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            System.out.println("goal input error");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/setConsumption")
    public ResponseEntity setConsumption(@RequestBody ConsumptionDto consumptionDto, @RequestHeader("token") String token) {
        if(DataValidator.validateConsumptionDto(consumptionDto)) {
            Session session = SessionManager.getSessionMap().get(token);
            userService.setConsumption(consumptionDto, session);
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            System.out.println("consumptionDto input error");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<String> logout(@RequestHeader("token") String token) {
        if (!SessionManager.getSessionMap().containsKey(token))
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        else {
            userService.logout(token);
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        TokenDto tokenDto = userService.login(loginDto);
        System.out.println(loginDto.getUsername());
        if (tokenDto != null)
            return new ResponseEntity<>(tokenDto, HttpStatus.OK);
        else
            return new ResponseEntity<>(tokenDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/getWeeklyReport")
    public ResponseEntity<StringObj> getWeeklyReport(@RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        } else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(userService.getWeeklyReport(session), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getMonthlyReport")
    public ResponseEntity<StringObj> getMonthlyReport(@RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        } else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(userService.getMonthlyReport(session), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/viewProfile")
    public ResponseEntity<UserDto> viewProfile(@RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        } else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(userService.findByUsername(session.getUsername()), HttpStatus.OK);
        }
    }
}
