package umc.server.domain.user.converter;

import umc.server.domain.user.dto.req.UserReqDTO;
import umc.server.domain.user.dto.res.UserResDTO;
import umc.server.domain.user.entity.User;

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

    // DTO -> Entity
    public static User toUser(
            UserReqDTO.JoinDTO dto
    ) {
        return User.builder()
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.specAddress())
                .point(0)
                .build();
    }
}
