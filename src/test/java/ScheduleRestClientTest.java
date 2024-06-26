import com.vaka.daily.client.ScheduleRestClient;
import com.vaka.daily.config.RestClientConfig;
import com.vaka.daily.exception.ScheduleNotFoundException;
import com.vaka.daily.model.Schedule;
import com.vaka.daily.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {RestClientConfig.class, ScheduleRestClient.class})
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ScheduleRestClientTest {
    @Autowired
    ScheduleRestClient client;

    private static Schedule createdSchedule;

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


    @DisplayName("Should return all schedules")
    @Order(1)
    @Test
    void testGetAll() {
        List<Schedule> schedules = client.getAll();

        log.info("Schedule list: {}", schedules.toString());

        assertEquals(3, schedules.size());
    }

    @DisplayName("Should return schedule by ID")
    @Test
    void testGetById() {
        Schedule schedule = client.getById(1);

        assertNotNull(schedule);

        log.info("Schedule with ID 1: {}", schedule);

        assertEquals("main", schedule.getName());
    }

    @DisplayName("Should throw ScheduleNotFound (id)")
    @Test
    void testGetByWrongId() {
        assertThrows(ScheduleNotFoundException.class, () -> client.getById(125));
    }

    @DisplayName("Should throw IllegalStateException (name)")
    @Test
    void testGetByWrongName() {
        assertThrows(IllegalStateException.class, () -> client.getByUniqueName("abc"));
    }

    @DisplayName("Should create new schedule")
    @Test
    @Order(2)
    void testPost2() {
        Schedule schedule = new Schedule("new_schedule", new User());

        Schedule postedSchedule = client.create(schedule);
        createdSchedule = postedSchedule;
        log.info("Created schedule: {}", createdSchedule);

        assertNotNull(postedSchedule);
    }

    @DisplayName("Should update schedule by ID")
    @Test
    @Order(3)
    void testPut() {
        assertNotNull(createdSchedule);
        String newName = "updated_schedule";
        createdSchedule.setName(newName);

        Schedule updatedSchedule = client.updateById(createdSchedule.getId(), createdSchedule);
        log.info("Updated schedule: {}", updatedSchedule);

        assertEquals(newName, updatedSchedule.getName());
        assertEquals(createdSchedule.getId(), updatedSchedule.getId());
    }

    @DisplayName("Should delete schedule by id")
    @Test
    @Order(4)
    void testDelete() {
        assertNotNull(createdSchedule.getId());
        client.deleteById(createdSchedule.getId());

        log.info("schedule with ID {} was deleted", createdSchedule.getId());

        assertThrows(ScheduleNotFoundException.class, () -> client.getById(createdSchedule.getId()));
    }
}
