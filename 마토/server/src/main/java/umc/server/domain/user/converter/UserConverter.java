package umc.server.domain.user.converter;

import umc.server.domain.user.dto.req.UserReqDTO;
import umc.server.domain.user.dto.res.UserResDTO;
import umc.server.domain.user.entity.User;
import umc.server.global.auth.enums.Role;

public class UserConverter {

    // Entity -> DTO
    public static UserResDTO.JoinDTO toJoinDTO(
            User user
    ){
        return UserResDTO.JoinDTO.builder()
                .memberId(user.getId())
                .createAt(user.getCreatedAt())
                .build();
    }

    // Entity -> 로그인 응답 DTO
    public static UserResDTO.LoginDTO toLoginDTO(User user, String accessToken) {
        return UserResDTO.LoginDTO.builder()
                .memberId(user.getId())
                .accessToken(accessToken)
                .build();
    }

    // DTO -> Entity
    public static User toUser(
            UserReqDTO.UserJoinDTO dto,
            String password,
            Role role

    ) {
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.specAddress())
                .point(0)
                .build();
    }
}
