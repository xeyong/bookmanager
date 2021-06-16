package com.fastcampus.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class UserTest {

    @Test
    void test(){
        User user = new User();
        user.setEmail("@mail.com");
        user.setName("seyong");
        System.out.println(">>>>>"+ user);
        System.out.println(user.hashCode());

        User.builder().name("se").email("ddd").build();

    }
}