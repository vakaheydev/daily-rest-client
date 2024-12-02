package com.vaka.daily_client.client.blocked;

import com.vaka.daily_client.client.Client;
import com.vaka.daily_client.model.User;

import java.util.List;

public interface UserClient extends Client<User> {
    List<User> getByUserTypeName(String userTypeName);
    User getByTelegramId(Long tgId);
}
