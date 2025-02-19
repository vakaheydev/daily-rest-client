package com.vaka.daily_client.exception.notfound;

public class BindingTokenNotFoundException extends ObjectNotFoundException {
    public BindingTokenNotFoundException(String detailName, Object detailValue) {
        super(detailName, detailValue);
    }

    @Override
    protected String getObjectName() {
        return "BindingToken";
    }
}
