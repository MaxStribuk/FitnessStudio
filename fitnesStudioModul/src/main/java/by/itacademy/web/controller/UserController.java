package by.itacademy.web.controller;

import by.itacademy.core.dto.request.UserLoginDto;
import by.itacademy.core.dto.request.UserRegistrationDto;
import by.itacademy.core.dto.request.UserVerificationDto;
import by.itacademy.core.dto.response.PageUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import by.itacademy.service.api.IUserService;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid UserRegistrationDto user) {
        this.userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/verification")
    public ResponseEntity<?> verify(
            @RequestParam(name = "code") UUID code,
            @RequestParam(name = "mail")
            @Email(regexp = UserRegistrationDto.EMAIL_PATTERN,
                    message = "invalid email") String mail) {
        this.userService.verify(new UserVerificationDto(code, mail));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> logIn(@RequestBody @Valid UserLoginDto user) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.userService.logIn(user));
    }

    @GetMapping(path = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageUserDto> get() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.userService.get());
    }
}