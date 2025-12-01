package umc.server.domain.user.service.query;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc.server.domain.user.converter.UserConverter;
import umc.server.domain.user.dto.req.UserReqDTO;
import umc.server.domain.user.dto.res.UserResDTO;
import umc.server.domain.user.entity.User;
import umc.server.domain.user.exception.UserException;
import umc.server.domain.user.exception.code.UserErrorCode;
import umc.server.domain.user.repository.UserRepository;
import umc.server.global.auth.jwt.JwtUtil;
import umc.server.global.auth.userDetails.CustomUserDetails;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService{

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public UserResDTO.LoginDTO login(
            UserReqDTO.@Valid LoginDTO dto
    ) {


        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("해당 이메일의 사용자가 없습니다."));

        // 비밀번호 검증
        if(!encoder.matches(dto.password(), user.getPassword())){
            throw new UserException(UserErrorCode.INVALID);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(user);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);


        return UserConverter.toLoginDTO(user, accessToken);
    }
}
