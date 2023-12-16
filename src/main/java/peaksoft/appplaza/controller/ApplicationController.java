package peaksoft.appplaza.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.appplaza.model.dto.ApplicationRequest;
import peaksoft.appplaza.model.dto.ApplicationResponse;
import peaksoft.appplaza.service.ApplicationService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "api/applications")
public class ApplicationController {
    private final ApplicationService applicationService;
    @PostMapping("/save")
    public ApplicationResponse save(@RequestBody ApplicationRequest request){
        return applicationService.save(request);
    }
    @PostMapping("/download{id}")
    public List<ApplicationResponse> download(@PathVariable("id") Long appId, Principal principal){
        return applicationService.download(appId, principal.getName());
    }
    @GetMapping("/my-applications")
    public List<ApplicationResponse> getUserApplication(Principal principal){
        return applicationService.myApplication(principal.getName());
    }
    @GetMapping("/{id}")
    public ApplicationResponse findById(@PathVariable Long id){
        return applicationService.findById(id);
    }
    @GetMapping("/{name}")
    public ApplicationResponse findByName(@PathVariable String name){
        return applicationService.findByName(name);
    }
    @GetMapping
    public List<ApplicationResponse> findAll() {
        return applicationService.findAll();
    }
    @GetMapping("update/{id}")
    public ApplicationResponse update(@PathVariable("id") Long id, ApplicationRequest request){
        return applicationService.update(id, request);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")Long id){
        applicationService.removeById(id);
        return "Application with id: "+id+" successfully deleted";
    }
}
