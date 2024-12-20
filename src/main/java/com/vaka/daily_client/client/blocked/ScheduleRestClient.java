package com.vaka.daily_client.client.blocked;

import com.vaka.daily_client.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Component
public class ScheduleRestClient extends AbstractRestClient<Schedule> implements ScheduleClient {
    RestClient client;

    @Autowired
    public ScheduleRestClient(RestClient client) {
        this.client = client;
    }

    @Override
    public Schedule create(Schedule entity) {
        Objects.requireNonNull(entity.getUser());
        return super.create(entity);
    }

    @Override
    public RestClient getRestClient() {
        return client;
    }

    @Override
    public String getDomainUrl() {
        return "/api/schedule";
    }

    @Override
    public String getNameOfUniqueName() {
        return "name";
    }

    @Override
    public Class<Schedule> getDomainType() {
        return Schedule.class;
    }

    @Override
    public Schedule getByUniqueName(String uniqueName) {
        throw new IllegalStateException("Schedule doesn't have unique name");
    }
}
