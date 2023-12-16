package peaksoft.appplaza.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.appplaza.model.dto.GenreRequest;
import peaksoft.appplaza.model.dto.GenreResponse;
import peaksoft.appplaza.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "api/genres")
public class GenreController {
    private final GenreService genreService;

    @PostMapping("/save")
    public GenreResponse save(@RequestBody GenreRequest request) {
        return genreService.save(request);
    }

    @GetMapping("/{id}")
    public GenreResponse findById(@PathVariable Long id) {
        return genreService.findById(id);
    }

    @GetMapping("/{name}")
    public GenreResponse findByName(@PathVariable String name) {
        return genreService.findByName(name);
    }

    @GetMapping
    public List<GenreResponse> findAll() {
        return genreService.findAll();
    }

    @GetMapping("update/{id}")
    public GenreResponse update(@PathVariable("id") Long id, GenreRequest request) {
        return genreService.updateById(id, request);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        genreService.removeById(id);
        return "Genre with id: "+id+" successfully deleted";
    }
}
