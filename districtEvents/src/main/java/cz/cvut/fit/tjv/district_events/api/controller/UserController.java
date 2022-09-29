package cz.cvut.fit.tjv.district_events.api.controller;

import cz.cvut.fit.tjv.district_events.api.conventer.UserConverter;
import cz.cvut.fit.tjv.district_events.business.EntityNotFoundException;
import cz.cvut.fit.tjv.district_events.business.EntityStateException;
import cz.cvut.fit.tjv.district_events.business.UserService;
import cz.cvut.fit.tjv.district_events.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    Collection<UserDto> all() {
        return UserConverter.toDtoMany(userService.readAll());
    }

    @PostMapping("/users")
    UserDto newUser(@RequestBody UserDto userDto) {
        if (userDto.getName() == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Name is null");
        }
        User user = UserConverter.fromDto(userDto);
        try {
            userService.create(user);
        } catch (EntityStateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate id");
        }
        user = this.userService.readById(user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Village Not Found"));
        return UserConverter.toDto(user);
    }

    @PutMapping("/users/{id}")
    UserDto updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        userService.readById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
        User user = UserConverter.fromDto(userDto);
        try {
            this.userService.update(user);
        } catch (EntityStateException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID is not unique", exception);
        }
        return userDto;
    }

    @DeleteMapping("users/{id}")
    void deleteVillage(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "User has been deleted");
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User was not found");
        }
    }
}
