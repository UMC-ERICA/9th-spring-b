package umc.server.domain.user.dto.req;

import lombok.Getter;
import umc.server.domain.user.enums.Gender;
import umc.server.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {

    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            String address,
            String specAddress,
            @ExistFoods
            List<Long> preferCategory
    ){}
}
