package peaksoft.appplaza.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.appplaza.mapper.LoginMapper;
import peaksoft.appplaza.mapper.UserMapper;
import peaksoft.appplaza.model.dto.*;
import peaksoft.appplaza.model.entities.Role;
import peaksoft.appplaza.model.entities.User;
import peaksoft.appplaza.repository.RoleRepository;
import peaksoft.appplaza.repository.UserRepository;
import peaksoft.appplaza.security.jwt.JwtUtil;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {
    final UserRepository repository;
    final UserMapper userMapper;
    final PasswordEncoder passwordEncoder;
    final RoleRepository roleRepository;
    final JwtUtil jwtUtil;
    final AuthenticationManager authenticationManager;
    final LoginMapper loginMapper;

    public RegistrationResponse registration(RegistrationRequest request) {
        User user = userMapper.mapToEntity(request);
        if (user.getName().length() < 2 || user.getLastName().length() < 2) {
            throw new RuntimeException("Имя и фамилия юзера должно содержать более двух символов!");

        }
        if (!user.getEmail().contains("@")) {
            throw new RuntimeException("В email должен присутствовать знак @ !");
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        List<Role> roles = new ArrayList<>();
        if (repository.findAll().isEmpty()) {
            Role role = roleRepository.findByName("ADMIN");
            if (role == null) {
                role = new Role("ADMIN");
            }
            roles.add(role);
        } else {
            Role role = roleRepository.findByName("USER");
            if (role == null) {
                role = new Role("USER");
            }
            roles.add(role);
        }
        repository.save(user);
        return userMapper.mapToResponse(user);
    }

    public LoginResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = repository.findByEmail(request.getEmail()).orElseThrow(()-> new RuntimeException("Not found"));
        String jwt = jwtUtil.generationToke(user);
        return loginMapper.mapToResponse(jwt, user);
    }

    public List<UserResponse> searchAndPagination(String text, int page, int size){
        String name = text == null ? "" : text;
        Pageable pageable = PageRequest.of(page-1, size);
        List<User> users = repository.searchAndPagination(name.toUpperCase(), pageable);
        List<UserResponse> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(userMapper.mapToUserResponse(user));
        }
        return responses;
    }
    public UserResponse findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found by id: " + id));
        return userMapper.mapToUserResponse(user);
    }
    public UserResponse findByName(String name){
        return userMapper.mapToUserResponse(repository.findByName(name).orElseThrow(()-> new RuntimeException("User not found with name: "+ name)));
    }

    public List<UserResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(userMapper::mapToUserResponse).toList();
    }

    public UserResponse updateById(Long userId, RegistrationRequest request) {
        User oldUser = repository.findById(userId).orElseThrow(() -> new RuntimeException("User not found by id: " + userId));
        oldUser.setName(request.getName());
        oldUser.setLastName(request.getLastName());
        oldUser.setAge(request.getAge());
        oldUser.setEmail(request.getEmail());
        oldUser.setSubscribe(request.isSubscribe());
        repository.save(oldUser);
        return userMapper.mapToUserResponse(oldUser);
    }

    public void removeById(Long userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found by id: " + userId));
        repository.delete(user);
    }
}
