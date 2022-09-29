package cz.cvut.fit.tjv.district_events.api.conventer;

import cz.cvut.fit.tjv.district_events.api.controller.UserDto;
import cz.cvut.fit.tjv.district_events.domain.User;

import java.util.ArrayList;
import java.util.Collection;

public class UserConverter {
    public static User fromDto(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName());
    }

    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName());
    }

    public static Collection<User> fromDtoMany(Collection<UserDto> villageDtos) {
        Collection<User> users = new ArrayList<>();
        villageDtos.forEach((u) -> users.add(fromDto(u)));
        return users;
    }

    public static Collection<UserDto> toDtoMany(Collection<User> villages) {
        Collection<UserDto> userDtos = new ArrayList<>();
        villages.forEach((u) -> userDtos.add(toDto(u)));
        return userDtos;
    }
}
