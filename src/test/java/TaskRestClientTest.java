import com.vaka.daily.client.TaskRestClient;
import com.vaka.daily.config.RestClientConfig;
import com.vaka.daily.exception.TaskNotFoundException;
import com.vaka.daily.model.Schedule;
import com.vaka.daily.model.Task;
import com.vaka.daily.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {RestClientConfig.class, TaskRestClient.class})
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskRestClientTest {
    @Autowired
    TaskRestClient client;

    private static Task createdTask;

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


    @DisplayName("Should return all tasks")
    @Order(1)
    @Test
    void testGetAll() {
        List<Task> taskList = client.getAll();

        log.info("Task list: {}", taskList.toString());

        assertEquals(5, taskList.size());
    }

    @DisplayName("Should return task by ID")
    @Test
    void testGetById() {
        Task task = client.getById(1);

        assertNotNull(task);

        log.info("Task with ID 1: {}", task);

        assertEquals("Прочитать книгу", task.getName());
    }

    @DisplayName("Should throw TaskNotFound (id)")
    @Test
    void testGetByWrongId() {
        assertThrows(TaskNotFoundException.class, () -> client.getById(125));
    }

    @DisplayName("Should throw IllegalStateException (name)")
    @Test
    void testGetByWrongName() {
        assertThrows(IllegalStateException.class, () -> client.getByUniqueName("abc"));
    }

    @DisplayName("Should create new task")
    @Test
    @Order(2)
    void testPost2() {
        Task task = new Task(null, "new_task", "description", LocalDateTime.now(), false, new Schedule("main",
                new User()));

        Task postedTask = client.create(task);
        createdTask = postedTask;
        log.info("Created task: {}", createdTask);

        assertNotNull(postedTask);
    }

    @DisplayName("Should update task by ID")
    @Test
    @Order(3)
    void testPut() {
        assertNotNull(createdTask);
        String newName = "updated_task";
        createdTask.setName(newName);

        Task updatedSchedule = client.updateById(createdTask.getId(), createdTask);
        log.info("Updated task: {}", updatedSchedule);

        assertEquals(newName, updatedSchedule.getName());
        assertEquals(createdTask.getId(), updatedSchedule.getId());
    }

    @DisplayName("Should delete task by id")
    @Test
    @Order(4)
    void testDelete() {
        assertNotNull(createdTask.getId());
        client.deleteById(createdTask.getId());

        log.info("Task with ID {} was deleted", createdTask.getId());

        assertThrows(TaskNotFoundException.class, () -> client.getById(createdTask.getId()));
    }
}
