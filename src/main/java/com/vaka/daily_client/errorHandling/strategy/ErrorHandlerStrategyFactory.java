package com.vaka.daily_client.errorHandling.strategy;

import com.vaka.daily_client.errorHandling.strategy.notFound.ScheduleNotFoundHandlerStrategy;
import com.vaka.daily_client.errorHandling.strategy.notFound.TaksNotFoundHandlerStrategy;
import com.vaka.daily_client.errorHandling.strategy.notFound.UserNotFoundHandlerStrategy;
import com.vaka.daily_client.errorHandling.strategy.notFound.UserTypeNotFoundHandlerStrategy;
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
