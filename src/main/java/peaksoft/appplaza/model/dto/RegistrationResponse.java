package peaksoft.appplaza.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import peaksoft.appplaza.model.enums.Status;
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationResponse {
    Long id;
    String name;
    String lastName;
    String email;
    int age;
    Status status;
    String response;
}
