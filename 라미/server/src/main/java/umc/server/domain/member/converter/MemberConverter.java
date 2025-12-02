package umc.server.domain.member.converter;

import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.global.auth.enums.Role;

public class MemberConverter {

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(Member member) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }


    // DTO -> Entity
    public static Member toMember(
            MemberReqDTO.JoinDTO dto,
            String password,
            Role role
    ) {
        return Member.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(password)
                .role(role)
                .birth(dto.getBirth())
                .address(dto.getAddress())
                .gender(dto.getGender())
                .build();
    }

    public static MemberResDTO.LoginDTO toLoginDTO(Member member, String accessToken, String refreshToken) {
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public static MemberResDTO.RefreshTokenDTO toRefreshTokenDTO(String accessToken, String refreshToken) {
        return MemberResDTO.RefreshTokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
