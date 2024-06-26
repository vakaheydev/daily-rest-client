import com.vaka.daily_client.client.blocked.UserTypeRestClient;
import com.vaka.daily_client.config.RestClientConfig;
import com.vaka.daily_client.exception.UserTypeNotFoundException;
import com.vaka.daily_client.exception.ValidationException;
import com.vaka.daily_client.model.UserType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {RestClientConfig.class, UserTypeRestClient.class})
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTypeRestClientTest {
    @Autowired
    UserTypeRestClient client;

    private static UserType createdUserType;

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


    @DisplayName("Should return all user types")
    @Order(1)
    @Test
    void testGetAll() {
        List<UserType> userTypes = client.getAll();

        log.info("User types list: {}", userTypes.toString());

        assertEquals(4, userTypes.size());
    }

    @DisplayName("Should return user type by ID")
    @Test
    void testGetById() {
        UserType userType = client.getById(1);

        assertNotNull(userType);

        log.info("User with ID 1: {}", userType);

        assertEquals("user", userType.getName());
    }

    @DisplayName("Should return user type by name")
    @Test
    void testGetByLogin() {
        String name = "user";
        UserType userType = client.getByUniqueName("user");

        assertNotNull(userType);

        log.info("User with name {}: {}", name, userType);

        assertEquals(1, userType.getId());
    }

    @DisplayName("Should throw UserTypeNotFound (id)")
    @Test
    void testGetByWrongId() {
        assertThrows(UserTypeNotFoundException.class, () -> client.getById(125));
    }

    @DisplayName("Should throw UserTypeNotFound (name)")
    @Test
    void testGetByWrongName() {
        assertThrows(UserTypeNotFoundException.class, () -> client.getByUniqueName("abc"));
    }

    @DisplayName("Should throw Validation (duplicate login)")
    @Test
    void testPost() {
        UserType userType = new UserType(null, "user");

        assertThrows(ValidationException.class, () -> client.create(userType));
    }

    @DisplayName("Should create new user type")
    @Test
    @Order(2)
    void testPost2() {
        UserType userType = new UserType(null, "new_user_type");

        UserType postedUserType = client.create(userType);
        createdUserType = postedUserType;
        log.info("Created user type: {}", createdUserType);

        assertNotNull(postedUserType);
    }

    @DisplayName("Should update user type by ID")
    @Test
    @Order(3)
    void testPut() {
        assertNotNull(createdUserType);
        String newName = "updated_name";
        createdUserType.setName(newName);

        UserType updatedUserType = client.updateById(createdUserType.getId(), createdUserType);
        log.info("Updated user type: {}", updatedUserType);

        assertEquals(newName, updatedUserType.getName());
        assertEquals(createdUserType.getId(), updatedUserType.getId());
    }

    @DisplayName("Should delete user type by id")
    @Test
    @Order(4)
    void testDelete() {
        assertNotNull(createdUserType.getId());
        client.deleteById(createdUserType.getId());

        log.info("User type with ID {} was deleted", createdUserType.getId());

        assertThrows(UserTypeNotFoundException.class, () -> client.getById(createdUserType.getId()));
    }
}
