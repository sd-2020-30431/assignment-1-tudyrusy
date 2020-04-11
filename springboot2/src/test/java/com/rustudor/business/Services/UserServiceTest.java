package com.rustudor.business.Services;

import com.rustudor.entity.Item;
import com.rustudor.entity.Login;
import com.rustudor.entity.Role;
import com.rustudor.entity.User;
import com.rustudor.persistence.repository.ItemRepository;
import com.rustudor.persistence.repository.LoginRepository;
import com.rustudor.persistence.repository.UsersRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.AssertTrue;
import java.sql.Timestamp;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class UserServiceTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(UserService.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    ItemRepository itemRepository;

    @Test
    private void addUser(){
        Login login = new Login();
        User user = new User();

        user.setGoal(50);
        user.setEmail("email@email.com");
        user.setLogin(login);
        user.setName("Mihai");
        login.setPassword("parola");
        login.setRole(Role.USER);
        login.setUsername("username");
        login.setUserFK(user);

        User user1 = usersRepository.save(user);
        Login login1 = loginRepository.save(login);
        assertTrue(login != null);
        assertTrue(user != null);
    }

    @Test
    private Item getItem() {

        Item item = new Item();
        item.setConsumptionDate(new Timestamp(System.currentTimeMillis()));
        item.setPurchaseDate(new Timestamp(System.currentTimeMillis()));
        item.setExpirationDate(new Timestamp(System.currentTimeMillis()));
        item.setQuantity(5);
        item.setCalories(10);
        item.setName("ceapa");

        return item;
    }
}
