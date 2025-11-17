package umc.server.domain.member.service.query;

public interface MemberQueryService {

    void changeUsername(Long memberId, String newUsername);
}
