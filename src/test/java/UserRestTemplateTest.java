import com.vaka.daily.client.UserRestTemplateClient;
import com.vaka.daily.config.RestTemplateConfig;
import com.vaka.daily.exception.UserNotFoundException;
import com.vaka.daily.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {RestTemplateConfig.class, UserRestTemplateClient.class})
@Slf4j
public class UserRestTemplateTest {
    @Autowired
    UserRestTemplateClient client;

    private static Integer createdUserID;

    @BeforeEach
    void setup() {
        client.isServerAlive();
    }


    @DisplayName("Should return all users")
    @Test
    void testGetAll() {
        List<User> users = client.getAll();

        log.info("Users list: {}", users.toString());

        assertEquals(4, users.size());
    }

    @DisplayName("SECOND | Should return all users")
    @Test
    void testSecondGetAll() {
        List<User> users = client.secondGetAll();

        log.info("Users list: {}", users.toString());

        assertEquals(4, users.size());
    }

    @DisplayName("Should return user by id")
    @Test
    void testGetById() {
        User user = client.getById(18);

        assertNotNull(user);

        log.info("User with ID 10: {}", user);

        assertEquals("new_login", user.getLogin());
    }

    @DisplayName("Should throw UserNotFound")
    @Test
    void testGetByWrongId() {
        assertThrows(UserNotFoundException.class, () -> client.getById(4));
    }

    @DisplayName("Should throw Runtime (duplicate login)")
    @Test
    void testPost() {
        User user = new User("vaka", "new_password", "firstName", "secondName", "patronymic");

        assertThrows(RuntimeException.class, () -> client.create(user));
    }

    @DisplayName("Should create new user")
    @Test
    @Order(1)
    void testPost2() {
        User user = new User("new_login", "new_password", "firstName", "secondName", "patronymic");

        User postedUser = client.create(user);
        createdUserID = postedUser.getId();

        assertNotNull(postedUser);
    }

    @DisplayName("Should delete user by id")
    @Test
    @Order(2)
    void testDelete() {
        assertNotNull(createdUserID);
        client.deleteById(createdUserID);
    }
}
