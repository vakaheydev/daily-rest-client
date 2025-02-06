package com.vaka.daily_client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BindingToken {
    private Integer id;
    private String value;
    private Integer userId;
}
