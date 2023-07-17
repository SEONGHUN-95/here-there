package com.seonghun.herenthere.dtos;

import com.seonghun.herenthere.models.User;

public record UserDto(
        String id,
        String name
) {
    public static UserDto of(User user) {
        return new UserDto(user.id().toString(), user.name());
    }
}
