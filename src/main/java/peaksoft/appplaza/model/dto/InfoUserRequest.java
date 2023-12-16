package peaksoft.appplaza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoUserRequest {
    private int passportNumber;
    private String address;
    private int cardNumber;
}
