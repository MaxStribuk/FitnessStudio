package web.controllers;

import core.dto.request.UserLoginDto;
import core.dto.request.UserRegistrarionDto;
import core.dto.response.PageUserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.api.IUserService;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody UserRegistrarionDto user) {
        userService.add(user);
    }

    @GetMapping(path = "/verification")
    public void verification(@RequestParam(name = "code") UUID verification) {
        userService.verification(verification);
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void login(@RequestBody UserLoginDto user) {
        userService.login(user);
    }

    @GetMapping(path = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageUserDto get() {
        return userService.get();
    }
}