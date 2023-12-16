package peaksoft.appplaza.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peaksoft.appplaza.model.dto.ApplicationRequest;
import peaksoft.appplaza.model.dto.ApplicationResponse;
import peaksoft.appplaza.model.entities.Application;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ApplicationMapper {
    private final GenreMapper genreMapper;
    public Application mapToEntity(ApplicationRequest applicationRequest) {
        return Application.builder()
                .name(applicationRequest.getName())
                .description(applicationRequest.getDescription())
                .appStatus(applicationRequest.getAppStatus())
                .version(applicationRequest.getVersion())
                .localDate(LocalDate.now()).build();
    }
    public ApplicationResponse mapToResponse(Application application){
        return ApplicationResponse.builder()
                .id(application.getId())
                .name(application.getName())
                .description(application.getDescription())
                .version(application.getVersion())
                .genreName(application.getGenreName())
                .appStatus(application.getAppStatus())
                .genre(genreMapper.mapToResponse(application.getGenre()))
                .localDate(LocalDate.now()).build();
    }
}
