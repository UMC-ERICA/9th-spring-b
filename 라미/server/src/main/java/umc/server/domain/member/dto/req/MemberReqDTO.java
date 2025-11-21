package umc.server.domain.member.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.server.domain.member.enums.FoodCategory;
import umc.server.domain.member.enums.Gender;
import umc.server.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    @Getter
    public static class JoinDTO {
        @NotBlank // null, 공백, 빈 문자열 X
        private String name;
        @NotNull
        private Gender gender;
        @NotNull
        private LocalDate birth;
        @NotNull
        private String address;
        @ExistFoods
        private List<FoodCategory> foodCategory; // 엔티티를 직접 받으면 안 됨
    }
}
