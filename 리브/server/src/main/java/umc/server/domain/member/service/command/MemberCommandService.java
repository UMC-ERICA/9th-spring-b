package umc.server.domain.member.service.command;

import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;

public interface MemberCommandService {

    // 회원가입
    MemberResDTO.JoinResponse signup(MemberReqDTO.JoinRequest dto);
}
