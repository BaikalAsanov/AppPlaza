package peaksoft.appplaza.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.appplaza.mapper.ApplicationMapper;
import peaksoft.appplaza.model.dto.ApplicationRequest;
import peaksoft.appplaza.model.dto.ApplicationResponse;
import peaksoft.appplaza.model.entities.Application;
import peaksoft.appplaza.model.entities.Genre;
import peaksoft.appplaza.model.entities.User;
import peaksoft.appplaza.repository.ApplicationRepository;
import peaksoft.appplaza.repository.GenreRepository;
import peaksoft.appplaza.repository.UserRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository repository;
    private final ApplicationMapper mapper;
    private final GenreRepository genreRepository;
    private final UserRepository userRepository;

    public ApplicationResponse save(ApplicationRequest request){
        Application application = mapper.mapToEntity(request);
        Genre genre  = genreRepository.findById(request.getGenreId()).orElseThrow(()-> new RuntimeException("Not found genre by id: "+ request.getGenreId()));
        application.setGenre(genre);
        application.setGenreName(genre.getGenreName());
        repository.save(application);
        return mapper.mapToResponse(application);
    }
    public List<ApplicationResponse> download(Long applicationId, String username){
        User user = userRepository.findByEmail(username)
                .orElseThrow(()-> new RuntimeException("Not found user with name: " + username));

        Application application = repository.findById(applicationId)
                .orElseThrow(()-> new RuntimeException("Not found application with id: " + applicationId));

        List<Application> myApplications = user.getApplications();
        if (! myApplications.contains(application)) {
            myApplications.add(application);
            userRepository.save(user);
        } else {
            throw new RuntimeException("This application already downloaded!");
        }
        return myApplication(user.getEmail());
    }
    public List<ApplicationResponse> myApplication(String username){
        User user = userRepository.findByEmail(username)
                .orElseThrow(()-> new RuntimeException("Not found user by name: "+ username));
        List<Application> applications = repository.getApplicationsByUserId(user.getId());
        return applications
                .stream()
                .map(mapper :: mapToResponse).toList();
    }
    public ApplicationResponse findById(Long id) {
        Application application = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found by id: " + id));
        return mapper.mapToResponse(application);
    }
    public ApplicationResponse findByName(String name) {
        return mapper.mapToResponse(repository.findByName(name).orElseThrow(()-> new RuntimeException("Application not found by name: "+name)));
    }
    public List<ApplicationResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::mapToResponse).toList();
    }
    public ApplicationResponse update(Long applicationId, ApplicationRequest request) {
        Application oldApplication = repository.findById(applicationId).orElseThrow(() -> new RuntimeException("Application not found by id: " + applicationId));
        oldApplication.setName(request.getName());
        oldApplication.setDescription(request.getDescription());
        oldApplication.setVersion(request.getVersion());
        oldApplication.setAppStatus(request.getAppStatus());

        repository.save(oldApplication);
        return mapper.mapToResponse(oldApplication);
    }
    public void removeById(Long appId) {
        Application application = repository.findById(appId)
                .orElseThrow(() -> new RuntimeException("Application not found by id: " + appId));
        repository.delete(application);
    }
}
