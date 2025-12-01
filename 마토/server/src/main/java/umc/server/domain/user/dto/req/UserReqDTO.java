package umc.server.domain.user.dto.req;

import jakarta.validation.constraints.NotBlank;
import umc.server.domain.user.enums.Gender;
import umc.server.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {

    public record UserJoinDTO(
            @NotBlank
            String name,
            String email,
            String password,
            Gender gender,
            LocalDate birth,
            String address,
            String specAddress,
            @ExistFoods
            List<Long> preferCategory


    ){}

    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}
