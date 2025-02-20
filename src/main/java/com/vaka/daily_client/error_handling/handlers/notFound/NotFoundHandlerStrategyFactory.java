package com.vaka.daily_client.error_handling.handlers.notFound;

import com.vaka.daily_client.model.DomainType;
import com.vaka.daily_client.model.ResponseError;

public class NotFoundHandlerStrategyFactory {
    public NotFoundHandlerStrategy getNotFoundHandlerStrategy(DomainType domainType, ResponseError responseError) {
        return switch (domainType) {
            case USER -> new UserNotFoundHandlerStrategy();
            case USER_TYPE -> new UserTypeNotFoundHandlerStrategy();
            case SCHEDULE -> new ScheduleNotFoundHandlerStrategy();
            case TASK -> new TaskNotFoundHandlerStrategy();
            case TASK_TYPE -> new TaskTypeNotFoundHandlerStrategy();
            case BINDING_TOKEN -> new BindingTokenNotFoundHandlerStrategy();
        };
    }
}
