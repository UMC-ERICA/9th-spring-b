package umc.server.domain.member.converter;

import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.enums.MemberStatus;
import umc.server.global.auth.enums.Role;

public class MemberConverter {

    // entity -> DTO
    public static MemberResDTO.JoinResponse toJoinResponseDTO(Member member) {
        return MemberResDTO.JoinResponse.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // DTO -> entity
    public static Member toMember(MemberReqDTO.JoinRequest dto, String password, Role role) {
        return Member.builder()
                .username(dto.username())
                .email(dto.email())
                .password(password)
                .role(role)
                .gender(dto.gender())
                .birthdate(dto.birthdate())
                .addressZipcode(dto.addressZipcode())
                .addressDetails(dto.addressDetails())
                .locationName(dto.locationName())
                .termsAgreed(dto.termsAgreed())
                .memberStatus(MemberStatus.ACTIVE)
                .build();
    }

    // entity + token -> Login DTO
    public static MemberResDTO.LoginResponse toLoginDTO(Member member, String accessToken) {
        return MemberResDTO.LoginResponse.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }
}
