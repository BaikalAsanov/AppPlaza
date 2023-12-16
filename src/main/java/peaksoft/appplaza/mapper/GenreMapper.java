package peaksoft.appplaza.mapper;

import org.springframework.stereotype.Component;
import peaksoft.appplaza.model.dto.GenreRequest;
import peaksoft.appplaza.model.dto.GenreResponse;
import peaksoft.appplaza.model.entities.Genre;

import java.time.LocalDate;

@Component
public class GenreMapper {
    public Genre mapToEntity(GenreRequest genreRequest){
        return Genre.builder()
                .genreName(genreRequest.getGenreName())
                .description(genreRequest.getDescription())
                .localDate(LocalDate.now()).build();
    }
    public GenreResponse mapToResponse(Genre genre){
        return GenreResponse.builder()
                .id(genre.getId())
                .genreName(genre.getGenreName())
                .description(genre.getDescription())
                .localDate(LocalDate.now())
                .build();
    }
}
