package umc.server.domain.member.dto.req;

import umc.server.domain.member.enums.Gender;
import umc.server.domain.member.enums.TermsAgreed;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinRequest (
            String username,
            Gender gender,
            LocalDate birthdate,
            String addressZipcode,
            String addressDetails,
            String locationName,
            TermsAgreed termsAgreed,
            List<Long> preferCategory
    ) {}
}
