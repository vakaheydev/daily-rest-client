package com.vaka.dailyClient.errorHandling.strategy;

import com.vaka.dailyClient.errorHandling.strategy.notFound.ScheduleNotFoundHandlerStrategy;
import com.vaka.dailyClient.errorHandling.strategy.notFound.TaksNotFoundHandlerStrategy;
import com.vaka.dailyClient.errorHandling.strategy.notFound.UserNotFoundHandlerStrategy;
import com.vaka.dailyClient.errorHandling.strategy.notFound.UserTypeNotFoundHandlerStrategy;
import com.vaka.dailyClient.model.DomainType;

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
