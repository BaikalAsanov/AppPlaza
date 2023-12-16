package peaksoft.appplaza.mapper;

import org.springframework.stereotype.Component;
import peaksoft.appplaza.model.dto.InfoUserRequest;
import peaksoft.appplaza.model.dto.InfoUserResponse;
import peaksoft.appplaza.model.entities.InfoUser;

import java.time.LocalDate;

@Component
public class InfoUserMapper {
    public InfoUser mapToEntity(InfoUserRequest request){
        return InfoUser.builder()
                .passportNumber(request.getPassportNumber())
                .address(request.getAddress())
                .cardNumber(request.getCardNumber())
                .localDate(LocalDate.now())
                .build();
    }
    public InfoUserResponse mapToResponse(InfoUser infoUser){
        return InfoUserResponse.builder()
                .id(infoUser.getId())
                .passportNumber(infoUser.getPassportNumber())
                .address(infoUser.getAddress())
                .cardNumber(infoUser.getCardNumber())
                .localDate(infoUser.getLocalDate())
                .build();
    }
}
