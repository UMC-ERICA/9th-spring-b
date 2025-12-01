package umc.server.domain.member.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import umc.server.domain.member.enums.Gender;
import umc.server.domain.member.enums.TermsAgreed;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinRequest (
            @NotBlank
            String username,
            @Email
            String email,
            @NotBlank
            String password,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birthdate,
            @NotNull
            String addressZipcode,
            @NotNull
            String addressDetails,
            @NotNull
            String locationName,
            @NotNull
            TermsAgreed termsAgreed,
            List<Long> preferCategory
    ) {}

    // 로그인
    public record LoginRequest(
        @NotBlank
        String email,
        @NotBlank
        String password
    ) {}
}
