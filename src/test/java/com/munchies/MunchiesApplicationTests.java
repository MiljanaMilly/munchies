package com.munchies;

import static org.junit.Assert.assertNotNull;

import com.munchies.model.Role;
import com.munchies.model.User;
import com.munchies.repositories.UserJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MunchiesApplicationTests {


    @Test
    public void contextLoads() {

    }

    @Test
    public void saveUser() {
        User testUser = new User();
        testUser.setFirstName("fdfgggg");
        testUser.setLastName("Last sgsggName");
        testUser.setEmail("ffwe@gmail.com");
        testUser.setPassword("password");
        testUser.setRoles(Arrays.asList(new Role("ADMIN")));
        assertNotNull(testUser);

    }


}
