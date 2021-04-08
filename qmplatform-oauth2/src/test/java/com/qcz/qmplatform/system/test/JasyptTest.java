package com.qcz.qmplatform.system.test;

import com.qcz.qmplatform.oauth2.OAuth2Application;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OAuth2Application.class)
@WebAppConfiguration
public class JasyptTest {

    @Autowired
    StringEncryptor encryptor;

    @Test
    public void test() {
        System.out.println(encryptor.encrypt("postgres"));
    }
}
