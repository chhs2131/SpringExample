package com.example.connect_databasetutorial;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class ConnectDatabaseTutorialApplicationTests {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    void contextLoads() {
    }

    @Test
    public void connection_test() {
        try(Connection con = sqlSessionFactory.openSession().getConnection()) {
            //log.debug("커넥션 성공");
            System.out.println("커넥션 성공");
        } catch (Exception e) {
            //log.debug("실패 ㅠㅠ");
            System.out.println("실패 ㅠㅠ");
            e.printStackTrace();
        }
    }
}
