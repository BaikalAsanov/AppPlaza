package peaksoft.appplaza.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.appplaza.mapper.InfoUserMapper;
import peaksoft.appplaza.model.dto.InfoUserRequest;
import peaksoft.appplaza.model.dto.InfoUserResponse;
import peaksoft.appplaza.model.entities.InfoUser;
import peaksoft.appplaza.repository.InfoUserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InfoUserService {
    private final InfoUserRepository repository;
    private final InfoUserMapper mapper;

    public InfoUserResponse save(InfoUserRequest request) {
        InfoUser infoUser = mapper.mapToEntity(request);
        repository.save(infoUser);
        return mapper.mapToResponse(infoUser);
    }

    public InfoUserResponse findById(Long id) {
        InfoUser infoUser = repository.findById(id).orElseThrow(() -> new RuntimeException("InfoUser not found by id: " + id));
        return mapper.mapToResponse(infoUser);
    }

    public List<InfoUserResponse> findByAll() {
        return repository.findAll()
                .stream()
                .map(mapper::mapToResponse).toList();
    }
    public InfoUserResponse updateById(Long id, InfoUserRequest request){
        InfoUser oldInfoUser = repository.findById(id).orElseThrow(()-> new RuntimeException("InfoUser nor found by id: "+ id));
        oldInfoUser.setPassportNumber(request.getPassportNumber());
        oldInfoUser.setAddress(request.getAddress());
        oldInfoUser.setCardNumber(request.getCardNumber());
        repository.save(oldInfoUser);
        return mapper.mapToResponse(oldInfoUser);
    }
    public void removeById(Long id){
       InfoUser infoUser = repository.findById(id).orElseThrow(()-> new RuntimeException("InfoUser not found by id: " + id));
       repository.delete(infoUser);
    }
}
