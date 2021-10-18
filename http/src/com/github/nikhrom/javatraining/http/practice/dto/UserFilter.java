package com.github.nikhrom.javatraining.http.practice.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserFilter {
    String email;
}
