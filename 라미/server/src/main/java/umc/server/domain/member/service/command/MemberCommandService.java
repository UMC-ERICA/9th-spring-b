package umc.server.domain.member.service.command;

import jakarta.servlet.http.HttpServletRequest;
import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;

public interface MemberCommandService {
    // 회원가입
    MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto);
    // 로그인
    MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto);

    MemberResDTO.RefreshTokenDTO refresh(MemberReqDTO.RefreshTokenDTO dto);
}
