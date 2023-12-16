package peaksoft.appplaza.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.appplaza.mapper.GenreMapper;
import peaksoft.appplaza.model.dto.GenreRequest;
import peaksoft.appplaza.model.dto.GenreResponse;
import peaksoft.appplaza.model.entities.Genre;
import peaksoft.appplaza.repository.GenreRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository repository;
    private final GenreMapper mapper;

    public GenreResponse save(GenreRequest request){
        Genre genre = mapper.mapToEntity(request);
        repository.save(genre);
        return mapper.mapToResponse(genre);
    }
    public GenreResponse findById(Long genreId){
        return mapper.mapToResponse(repository.findById(genreId)
                .orElseThrow(()-> new RuntimeException("Not found genre by id: "+genreId)));
    }
    public GenreResponse findByName(String genreName){
        return mapper.mapToResponse(repository.findByName(genreName)
                .orElseThrow(()-> new RuntimeException("Not found genre by name: "+genreName)));
    }
    public List<GenreResponse> findAll(){
        return repository.findAll()
                .stream()
                .map(mapper :: mapToResponse).toList();
    }
    public GenreResponse updateById(Long genreId, GenreRequest request){
        Genre oldGenre = repository.findById(genreId).orElseThrow(()-> new RuntimeException("Genre not found by id: "+genreId));
        oldGenre.setGenreName(request.getGenreName());
        oldGenre.setDescription(request.getDescription());
        repository.save(oldGenre);
        return mapper.mapToResponse(oldGenre);
    }
    public void removeById(Long genreId){
        Genre genre = repository.findById(genreId)
                .orElseThrow(()-> new RuntimeException("Genre not found by id: " + genreId));
        repository.delete(genre);
    }
}
