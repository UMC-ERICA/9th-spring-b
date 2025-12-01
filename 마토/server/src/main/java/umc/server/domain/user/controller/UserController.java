package umc.server.domain.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.user.converter.UserConverter;
import umc.server.domain.user.dto.req.UserReqDTO;
import umc.server.domain.user.dto.res.UserResDTO;
import umc.server.domain.user.entity.User;
import umc.server.domain.user.exception.code.UserSuccessCode;
import umc.server.domain.user.service.command.UserCommandService;
import umc.server.domain.user.service.query.UserQueryService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.auth.userDetails.CustomUserDetails;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;


    @PostMapping("/sign-up")
    public ApiResponse<UserResDTO.JoinDTO> signUp(
            @RequestBody @Valid UserReqDTO.UserJoinDTO dto
    ){
        return ApiResponse.onSuccess(UserSuccessCode.FOUND, userCommandService.signup(dto));
    }

    @PostMapping("/login")
    public ApiResponse<UserResDTO.LoginDTO> login(
            @RequestBody @Valid UserReqDTO.LoginDTO dto,
            HttpServletRequest request
    ){

        return ApiResponse.onSuccess(UserSuccessCode.FOUND, userQueryService.login(dto));
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return ApiResponse.onSuccess(UserSuccessCode.FOUND, null);
    }
}
