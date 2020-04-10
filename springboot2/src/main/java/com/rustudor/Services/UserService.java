package com.rustudor.Services;

import com.rustudor.Dto.*;
import com.rustudor.Util.Session;
import com.rustudor.Util.SessionManager;
import com.rustudor.entity.*;
import com.rustudor.repository.ItemRepository;
import com.rustudor.repository.LoginRepository;
import com.rustudor.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    ItemRepository itemRepository;

    @Transactional
    public int register(FullUserDto fullUserDto) {
        User user = new User();
        Login login = new Login();

        Login login2 = loginRepository.findByUsername(fullUserDto.getUsername());
        if (login2 != null)
            return -1;//duplicate username
        user.setName(fullUserDto.getName());
        user.setEmail(fullUserDto.getEmail());
        login.setRole(Role.USER);
        login.setUsername(fullUserDto.getUsername());
        login.setUserFK(user);
        login.setPassword(fullUserDto.getPassword());
        user.setLogin(login);

        loginRepository.save(login);
        usersRepository.save(user);
        return 0;
    }

    public TokenDto login(LoginDto loginDto) {
        Login login = loginRepository.findByUsername(loginDto.getUsername());
        if (login != null) {
            if (login.getPassword().equals(loginDto.getPassword())) {
                //SUCCESS
                Session session = new Session(loginDto.getUsername(), Instant.now(), Session.EXPIRATION_TIME, login.getRole());
                String token = SessionManager.add(session);
                return new TokenDto(token, "TOKEN OK");
            } else {
                return null;
            }
        } else {
            //ERROR
            return null;
        }
    }

    public UserDto findByUsername(String username) {
        User u = usersRepository.findByUsername(username);
        return new UserDto(u.getName(),u.getEmail());
    }

    public void logout(String token) {
        SessionManager.getSessionMap().remove(token);
    }

    public void addItem(ItemDto itemDto,Session session) {
        Item i = new Item();
        User user = usersRepository.findByUsername(session.getUsername());

        i.setName(itemDto.getName());
        i.setCalories(itemDto.getCalories());
        i.setExpirationDate(itemDto.getExpirationDate());
        i.setPurchaseDate(itemDto.getPurchaseDate());
        i.setQuantity(itemDto.getQuantity());
        i.setUserFK(user);

        user.getGroceryList().add(i);
        itemRepository.save(i);
    }

    public ArrayList<ItemDto1> getItems(Session session) {
        ArrayList<ItemDto1> itemDtos = new ArrayList<>();

        User user = usersRepository.findByUsername(session.getUsername());
        for (Item a : user.getGroceryList()) {
            if (a.getConsumptionDate()==null)
                itemDtos.add(new ItemDto1(a.getId(),a.getName(),a.getQuantity(),a.getCalories(),a.getPurchaseDate(),
                        a.getExpirationDate(),"N/As"));
            else
            itemDtos.add(new ItemDto1(a.getId(),a.getName(),a.getQuantity(),a.getCalories(),a.getPurchaseDate(),
                    a.getExpirationDate(),a.getConsumptionDate().toString()));
        }
        return itemDtos;
    }

    public void setConsumption(ConsumptionDto consumptionDto, Session session) {
        User user = usersRepository.findByUsername(session.getUsername());
        Item i = itemRepository.findByNameAndUserFK(consumptionDto.getName(), user);
        System.out.println(i.toString());
        i.setConsumptionDate(consumptionDto.getConsumptionDate());
        itemRepository.save(i);
    }
}
