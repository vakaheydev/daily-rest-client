import com.vaka.daily.client.UserRestClientClient;
import com.vaka.daily.config.RestClientConfig;
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

@SpringBootTest(classes = {RestClientConfig.class, UserRestClientClient.class})
@Slf4j
public class UserRestClientClientTest {
    @Autowired
    UserRestClientClient client;

    private static User createdUser;

    @BeforeEach
    void setup() {
        client.isServerAlive();
    }


    @DisplayName("Should return all users")
    @Order(1)
    @Test
    void testGetAll() {
        List<User> users = client.getAll();

        log.info("Users list: {}", users.toString());

        assertEquals(3, users.size());
    }

    @DisplayName("Should return user by ID")
    @Test
    void testGetById() {
        User user = client.getById(1);

        assertNotNull(user);

        log.info("User with ID 1: {}", user);

        assertEquals("vaka", user.getLogin());
    }

    @DisplayName("Should return user by login")
    @Test
    void testGetByLogin() {
        String login = "vaka";
        User user = client.getByUniqueName(login);

        assertNotNull(user);

        log.info("User with login {}: {}", login, user);

        assertEquals(1, user.getId());
    }

    @DisplayName("Should throw UserNotFound (id)")
    @Test
    void testGetByWrongId() {
        assertThrows(UserNotFoundException.class, () -> client.getById(125));
    }

    @DisplayName("Should throw UserNotFound (name)")
    @Test
    void testGetByWrongName() {
        assertThrows(UserNotFoundException.class, () -> client.getByUniqueName("abc"));
    }

    @DisplayName("Should throw Runtime (duplicate login)")
    @Test
    void testPost() {
        User user = new User("vaka", "new_password", "firstName", "secondName", "patronymic");

        assertThrows(RuntimeException.class, () -> client.create(user));
    }

    @DisplayName("Should create new user")
    @Test
    @Order(2)
    void testPost2() {
        User user = new User("new_login", "new_password", "firstName", "secondName", "patronymic");

        User postedUser = client.create(user);
        createdUser = postedUser;

        assertNotNull(postedUser);
    }

    @DisplayName("Should update user by ID")
    @Test
    @Order(3)
    void testPut() {
        assertNotNull(createdUser);
        String updatedLogin = "updated_login";
        createdUser.setLogin(updatedLogin);

        User updatedUser = client.updateById(createdUser.getId(), createdUser);

        assertEquals("updated_login", updatedUser.getLogin());
        assertEquals(createdUser.getId(), updatedUser.getId());
    }

    @DisplayName("Should delete user by id")
    @Test
    @Order(4)
    void testDelete() {
        assertNotNull(createdUser.getId());
        client.deleteById(createdUser.getId());

        assertThrows(UserNotFoundException.class, () -> client.getById(createdUser.getId()));
    }
}