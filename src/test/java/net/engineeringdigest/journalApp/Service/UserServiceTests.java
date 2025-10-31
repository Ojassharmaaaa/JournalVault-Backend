package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @ParameterizedTest
    @CsvSource({
            "ojas",
            "Muskan",
            "Radhika Avhad",
            "Mishti"

    })
    public void testFindByUserName(String userName)
    {
        User user =userService.findByUserName(userName);
        assertNotNull(user.getUserName());

    }

}
