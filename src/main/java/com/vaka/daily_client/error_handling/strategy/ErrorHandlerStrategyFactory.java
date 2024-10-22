package com.vaka.daily_client.error_handling.strategy;

import com.vaka.daily_client.error_handling.strategy.notFound.ScheduleNotFoundHandlerStrategy;
import com.vaka.daily_client.error_handling.strategy.notFound.TaksNotFoundHandlerStrategy;
import com.vaka.daily_client.error_handling.strategy.notFound.UserNotFoundHandlerStrategy;
import com.vaka.daily_client.error_handling.strategy.notFound.UserTypeNotFoundHandlerStrategy;
import com.vaka.daily_client.model.DomainType;

public class ErrorHandlerStrategyFactory {
    public ErrorHandlerStrategy getNotFoundStrategy(DomainType domainType) {
        return switch (domainType) {
            case USER -> new UserNotFoundHandlerStrategy();
            case USER_TYPE -> new UserTypeNotFoundHandlerStrategy();
            case SCHEDULE -> new ScheduleNotFoundHandlerStrategy();
            case TASK -> new TaksNotFoundHandlerStrategy();
        };
    }
}
