package peaksoft.appplaza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfoUserResponse {
    private Long id;
    private int passportNumber;
    private String address;
    private int cardNumber;
    private LocalDate localDate;

}
