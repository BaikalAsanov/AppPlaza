package peaksoft.appplaza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import peaksoft.appplaza.model.enums.ApplicationStatus;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequest {
    private String name;
    private String description;
    private String version;
    private ApplicationStatus appStatus;
    private Long genreId;
}