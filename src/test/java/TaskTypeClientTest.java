import com.vaka.daily_client.client.blocked.TaskTypeRestClient;
import com.vaka.daily_client.config.JacksonConfig;
import com.vaka.daily_client.config.RestClientConfig;
import com.vaka.daily_client.exception.TaskTypeNotFoundException;
import com.vaka.daily_client.exception.ValidationException;
import com.vaka.daily_client.model.TaskType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {RestClientConfig.class, TaskTypeRestClient.class, JacksonConfig.class})
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskTypeClientTest {
    @Autowired
    TaskTypeRestClient client;

    private static TaskType createdTaskType;

//    @BeforeEach
//    void setup() {
//        client.isServerAlive();
//    }

    @DisplayName("Server should be alive")
    @Order(0)
    @Test
    void testServerIsAlive() {
        assertTrue(client.isServerAlive());
    }


    @DisplayName("Should return all task types")
    @Order(1)
    @Test
    void testGetAll() {
        List<TaskType> types = client.getAll();

        for (var TaskType : types) {
            log.info(TaskType.getName());
        }

        log.info("task types list: {}", types.toString());

        assertEquals(3, types.size());
    }

    @DisplayName("Should return task type by ID")
    @Test
    void testGetById() {
        TaskType type = client.getById(1);

        assertNotNull(type);

        log.info("task with ID 1: {}", type);

        assertEquals("singular", type.getName());
    }

    @DisplayName("Should throw TaskTypeNotFound (id)")
    @Test
    void testGetByWrongId() {
        assertThrows(TaskTypeNotFoundException.class, () -> client.getById(125));
    }

    @DisplayName("Should throw Validation (duplicate login)")
    @Test
    void testPost() {
        TaskType type = new TaskType(null, "repetitive");

        assertThrows(ValidationException.class, () -> client.create(type));
    }

    @DisplayName("Should create new task type")
    @Test
    @Order(2)
    void testPost2() {
        TaskType type = new TaskType(null, "new_task_type");

        TaskType postedTaskType = client.create(type);
        createdTaskType = postedTaskType;
        log.info("Created task type: {}", createdTaskType);

        assertNotNull(postedTaskType);
    }

    @DisplayName("Should update task type by ID")
    @Test
    @Order(3)
    void testPut() {
        assertNotNull(createdTaskType);
        String newName = "updated_name";
        createdTaskType.setName(newName);

        TaskType updatedTaskType = client.updateById(createdTaskType.getId(), createdTaskType);
        log.info("Updated task type: {}", updatedTaskType);

        assertEquals(newName, updatedTaskType.getName());
        assertEquals(createdTaskType.getId(), updatedTaskType.getId());
    }

    @DisplayName("Should delete task type by id")
    @Test
    @Order(4)
    void testDelete() {
        assertNotNull(createdTaskType.getId());
        client.deleteById(createdTaskType.getId());

        log.info("Task type with ID {} was deleted", createdTaskType.getId());

        assertThrows(TaskTypeNotFoundException.class, () -> client.getById(createdTaskType.getId()));
    }
}
