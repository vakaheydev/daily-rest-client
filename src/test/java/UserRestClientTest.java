import com.vaka.daily_client.client.blocked.TaskTypeRestClient;
import com.vaka.daily_client.client.blocked.UserRestClient;
import com.vaka.daily_client.config.JacksonConfig;
import com.vaka.daily_client.config.RestClientConfig;
import com.vaka.daily_client.exception.DuplicateEntityException;
import com.vaka.daily_client.exception.notfound.UserNotFoundException;
import com.vaka.daily_client.exception.ValidationException;
import com.vaka.daily_client.model.User;
import com.vaka.daily_client.model.UserTypes;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {RestClientConfig.class, UserRestClient.class, TaskTypeRestClient.class, JacksonConfig.class})
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRestClientTest {
    @Autowired
    UserRestClient client;

    private static User createdUser;

    @BeforeEach
    void setup() {
        client.isServerAlive();
    }

    @DisplayName("Server should be alive")
    @Order(0)
    @Test
    void testServerIsAlive() {
        assertTrue(client.isServerAlive());
    }


    @DisplayName("Should return all users")
    @Order(1)
    @Test
    void testGetAll() {
        List<User> users = client.getAll();

        for (var user : users) {
            log.info(user.getLogin());
        }

        log.info("Users list: {}", users);

        assertDoesNotThrow(() -> users.get(0).toString());

        assertTrue(users.size() > 1);
        assertNotNull(users.get(0).getSchedules().get(0).getTasks().get(0).getTaskType());
    }

    @DisplayName("Should return user by ID")
    @Test
    void testGetById() {
        User user = client.getById(1);

        assertNotNull(user);

        log.info("User with ID 1: {}", user);

        assertEquals("vaka", user.getLogin());
    }

    @DisplayName("Should return user by user type name (developer)")
    @Test
    void testGetByUserTypeName() {
        List<User> users = client.getByUserTypeName("developer");

        assertNotNull(users);

        log.info("Users with user type developer: {}", users);

        assertEquals(1, users.size());
        assertEquals("vaka", users.get(0).getLogin());
        assertEquals("developer", users.get(0).getUserType().getName());
    }

    @DisplayName("Should return user by user type name (user)")
    @Test
    void testGetByUserTypeName2() {
        List<User> users = client.getByUserTypeName("user");

        assertNotNull(users);

        log.info("Users with user type user: {}", users);

        assertFalse(users.isEmpty());
        assertEquals("user", users.get(0).getUserType().getName());
    }

    @DisplayName("Should return user by telegram id")
    @Test
    void testGetUserByTgId() {
        long tgId = 1531192384;
        User user = client.getByTelegramId(tgId);

        assertNotNull(user);

        log.info("User with specified tg id: {}", user);

        assertEquals("vaka", user.getLogin());
        assertEquals(UserTypes.DEVELOPER.getType(), user.getUserType());
        assertEquals(tgId, user.getTelegramId());
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

    @DisplayName("Should throw UserNotFound (telegram id)")
    @Test
    void testGetByWrongTgId() {
        assertThrows(UserNotFoundException.class, () -> client.getByTelegramId(-1L));
    }

    @DisplayName("Should throw DuplicateException (duplicate login)")
    @Test
    void testPost() {
        User user = new User("vaka", "new_password", "firstName", "secondName", "patronymic");

        assertThrows(DuplicateEntityException.class, () -> client.create(user));
    }

    @DisplayName("Should create new user")
    @Test
    @Order(2)
    void testPost2() {
        User user = new User("new_login", "new_password", "firstName", "secondName", "patronymic");

        User postedUser = client.create(user);
        createdUser = postedUser;
        log.info("Created user: {}", createdUser);

        assertNotNull(postedUser);
    }

    @DisplayName("Should update user by ID")
    @Test
    @Order(3)
    void testPut() {
        assertNotNull(createdUser);
        String newLogin = "updated_login";
        createdUser.setLogin(newLogin);

        User updatedUser = client.updateById(createdUser.getId(), createdUser);
        log.info("Updated user: {}", updatedUser);

        assertEquals(newLogin, updatedUser.getLogin());
        assertEquals(createdUser.getId(), updatedUser.getId());
    }

    @DisplayName("Should delete user by id")
    @Test
    @Order(4)
    void testDelete() {
        assertNotNull(createdUser.getId());
        client.deleteById(createdUser.getId());

        log.info("User with ID {} was deleted", createdUser.getId());

        assertThrows(UserNotFoundException.class, () -> client.getById(createdUser.getId()));
    }
}