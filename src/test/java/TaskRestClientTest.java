import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaka.daily_client.client.blocked.ScheduleRestClient;
import com.vaka.daily_client.client.blocked.TaskRestClient;
import com.vaka.daily_client.config.JacksonConfig;
import com.vaka.daily_client.config.RestClientConfig;
import com.vaka.daily_client.exception.TaskNotFoundException;
import com.vaka.daily_client.model.Schedule;
import com.vaka.daily_client.model.Task;
import com.vaka.daily_client.model.TaskType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {RestClientConfig.class, TaskRestClient.class, ScheduleRestClient.class, JacksonConfig.class})
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskRestClientTest {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    TaskRestClient taskClient;

    @Autowired
    ScheduleRestClient scheduleClient;

    private static Task createdTask;

    @BeforeEach
    void setup() {
        taskClient.isServerAlive();
    }

    @DisplayName("Server should be alive")
    @Order(0)
    @Test
    void testServerIsAlive() {
        assertTrue(taskClient.isServerAlive());
    }


    @DisplayName("Should return all tasks")
    @Order(1)
    @Test
    void testGetAll() {
        List<Task> taskList = taskClient.getAll();

        for(var task : taskList) {
            log.info(task.getName());
        }

        log.info("Task list: {}", taskList);
        assertEquals("Прочитать книгу", taskList.get(0).getName());
        assertEquals("Разработать REST API", taskList.get(1).getName());
    }

    @DisplayName("Should return task by ID")
    @Test
    void testGetById() {
        Task task = taskClient.getById(1);

        assertNotNull(task);

        log.info("Task with ID 1: {}", task);

        assertEquals("Прочитать книгу", task.getName());
    }

    @DisplayName("Should throw TaskNotFound (id)")
    @Test
    void testGetByWrongId() {
        assertThrows(TaskNotFoundException.class, () -> taskClient.getById(125));
    }

    @DisplayName("Should throw IllegalStateException (name)")
    @Test
    void testGetByWrongName() {
        assertThrows(IllegalStateException.class, () -> taskClient.getByUniqueName("abc"));
    }

    @DisplayName("Should create new task")
    @Test
    @Order(2)
    void testPost() throws JsonProcessingException {
        Schedule schedule = scheduleClient.getById(2);
        log.info("Schedule before task created: {}", schedule);

        Task task = new Task(null, "new_task", "description", LocalDateTime.now(), false, 2, TaskType.SINGULAR);
        log.info("Task to JSON: {}", mapper.writeValueAsString(task));
        Task postedTask = taskClient.create(task);
        createdTask = postedTask;
        log.info("Created task: {}", postedTask);

        schedule = scheduleClient.getById(2);

        log.info("Schedule after task created: {}", schedule);
    }

    @DisplayName("Should update task by ID")
    @Test
    @Order(3)
    void testPut() {
        assertNotNull(createdTask);
        String newName = "updated_task";
        createdTask.setName(newName);

        Task updatedSchedule = taskClient.updateById(createdTask.getId(), createdTask);
        log.info("Updated task: {}", updatedSchedule);

        assertEquals(newName, updatedSchedule.getName());
        assertEquals(createdTask.getId(), updatedSchedule.getId());
    }

    @DisplayName("Should delete task by id")
    @Test
    @Order(4)
    void testDelete() {
        assertNotNull(createdTask);
        assertNotNull(createdTask.getId());
        taskClient.deleteById(createdTask.getId());

        log.info("Task with ID {} was deleted", createdTask.getId());

        assertThrows(TaskNotFoundException.class, () -> taskClient.getById(createdTask.getId()));
    }
}
