package umc.server.domain.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.exception.code.MemberSuccessCode;
import umc.server.domain.member.service.command.KakaoLoginService;
import umc.server.domain.member.service.command.MemberCommandService;
import umc.server.domain.member.service.query.MemberQueryService;
import umc.server.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final KakaoLoginService kakaoLoginService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signup (@RequestBody @Valid MemberReqDTO.JoinDTO dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signup(dto));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginDTO> login (@RequestBody @Valid MemberReqDTO.LoginDTO dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberQueryService.login(dto));
    }

    // 카카오 로그인
    @GetMapping("/kakao/callback")
    public ApiResponse<MemberResDTO.LoginDTO> kakaoLogin(@RequestParam("code") String code) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, kakaoLoginService.kakaoLogin(code));
    }
}
