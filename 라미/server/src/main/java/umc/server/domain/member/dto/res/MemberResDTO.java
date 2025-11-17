package umc.server.domain.member.dto.res;

import lombok.Builder;
import lombok.Setter;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Setter
    @Builder
    public static class JoinDTO {
        Long memberId;
        LocalDateTime createAt;
    }
}
