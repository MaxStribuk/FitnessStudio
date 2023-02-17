package web.controllers;

import core.dto.request.UserDto;
import core.dto.response.PageUserDto;
import core.dto.response.PageUsersDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.api.IAdminService;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class AdminController {

    private final IAdminService userService;

    public AdminController(IAdminService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageUserDto get(@PathVariable(name = "uuid") UUID uuid) {
        return userService.get(uuid);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageUsersDto getAll() {
        return userService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody UserDto user) {
        userService.add(user);
    }

    @PutMapping(path = "{uuid}/dt_update/{dt_update}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable(name = "uuid") UUID uuid,
                       @PathVariable(name = "dt_update") LocalDateTime dtUpdate,
                       @RequestBody UserDto user) {
        userService.update(uuid, dtUpdate, user);
    }
}