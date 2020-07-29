package com.reem96.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;


// Created by Shorasul Sh. on 29.07.2020

@SpringBootTest
class ApiApplicationTest {

    @Test
    void contextLoads() {
        ApiApplication.main(new String[] {});
    }
}