package com.vaka.daily_client.errorhandling.handler;

import com.vaka.daily_client.model.DomainType;

public class DomainErrorHandlerFactory {
    public static DomainErrorHandler getErrorHandler(DomainType domainType) {
        return switch (domainType) {
            case USER -> new UserErrorHandler();
            case USER_TYPE -> new UserTypeErrorHandler();
            case SCHEDULE -> new ScheduleErrorHandler();
            case TASK -> new TaskErrorHandler();
        };
    }
}
