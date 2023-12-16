package peaksoft.appplaza.mapper;

import org.springframework.stereotype.Component;
import peaksoft.appplaza.model.dto.LoginResponse;
import peaksoft.appplaza.model.entities.Role;
import peaksoft.appplaza.model.entities.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginMapper {
    public LoginResponse mapToResponse(String token, User user) {
        List<String> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getRoleName());
        }
        return LoginResponse.builder()
                .token(token)
                .roleName(roles)
                .build();
    }
}
