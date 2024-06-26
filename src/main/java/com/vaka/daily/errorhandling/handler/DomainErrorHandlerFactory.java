package com.vaka.daily.errorhandling.handler;

import com.vaka.daily.model.DomainType;

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
