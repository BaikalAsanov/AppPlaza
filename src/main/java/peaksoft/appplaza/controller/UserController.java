package peaksoft.appplaza.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.appplaza.model.dto.*;
import peaksoft.appplaza.service.UserService;


import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {
    final UserService userService;
    @PostMapping("/sign-up")
    public ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest request) {
        RegistrationResponse response = userService.registration(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/sign-in")
    public LoginResponse login(@RequestBody LoginRequest request){
        return userService.login(request);
    }
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id){
        return userService.findById(id);
    }
    @GetMapping("/{name}")
    public UserResponse findByName(@PathVariable String name){
        return userService.findByName(name);
    }
    @GetMapping()
    public List<UserResponse> findAll() {
        return userService.findAll();
    }
    @GetMapping("update/{id}")
    public UserResponse update(@PathVariable("id") Long id, RegistrationRequest request){
        return userService.updateById(id, request);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")Long id){
        userService.removeById(id);
        return "User with id: "+id+" successfully deleted";
    }
    @GetMapping("/search")
    public List<UserResponse> searchAndPagination(@RequestParam(name = "text", required = false) String text,
                                                  @RequestParam int page,
                                                  @RequestParam int size){
        return userService.searchAndPagination(text, page, size);

    }
}
