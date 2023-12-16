package peaksoft.appplaza.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.appplaza.model.dto.InfoUserRequest;
import peaksoft.appplaza.model.dto.InfoUserResponse;
import peaksoft.appplaza.model.entities.InfoUser;
import peaksoft.appplaza.service.InfoUserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "api/info_users")
public class InfoUserController {
    private final InfoUserService infoUserService;
    @PostMapping("/save")
    public InfoUserResponse save(@RequestBody InfoUserRequest request){
        return infoUserService.save(request);
    }
    @GetMapping("/{id}")
    public InfoUserResponse findById(@PathVariable Long id){
        return infoUserService.findById(id);
    }
    @GetMapping
    public List<InfoUserResponse> findAll(){
        return infoUserService.findByAll();
    }
    @GetMapping("update/{id}")
    public InfoUserResponse update(@PathVariable("id") Long id, InfoUserRequest request){
        return infoUserService.updateById(id, request);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        infoUserService.removeById(id);
        return "InfoUser with id: "+id+" successfully deleted";
    }
}
