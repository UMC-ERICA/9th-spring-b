package umc.server.domain.member.service.query;

import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;

public interface MemberQueryService {

    void changeUsername(Long memberId, String newUsername);

    // 로그인
    MemberResDTO.LoginResponse login(MemberReqDTO.LoginRequest dto);
}
