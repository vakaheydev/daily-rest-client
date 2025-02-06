package com.vaka.daily_client.client.blocked;

import com.vaka.daily_client.model.BindingToken;

import java.util.List;

public interface BindingTokenClient {
    List<BindingToken> getAll();
    BindingToken getByTokenValue(String tokenValue);
    BindingToken getById(Integer id);
    BindingToken getByUserId(Integer userId);
    BindingToken createToken(Integer userId);
}
