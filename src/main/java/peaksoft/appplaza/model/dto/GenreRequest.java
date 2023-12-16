package peaksoft.appplaza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreRequest implements Serializable {
    private String genreName;
    private String description;
    private LocalDate localDate;
}