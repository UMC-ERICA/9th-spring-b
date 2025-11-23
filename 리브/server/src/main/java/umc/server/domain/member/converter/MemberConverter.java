package umc.server.domain.member.converter;

import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.enums.MemberStatus;

public class MemberConverter {

    // entity -> DTO
    public static MemberResDTO.JoinResponse toJoinResponseDTO(Member member) {
        return MemberResDTO.JoinResponse.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // DTO -> entity
    public static Member toMember(MemberReqDTO.JoinRequest dto) {
        return Member.builder()
                .username(dto.username())
                .gender(dto.gender())
                .birthdate(dto.birthdate())
                .addressZipcode(dto.addressZipcode())
                .addressDetails(dto.addressDetails())
                .locationName(dto.locationName())
                .termsAgreed(dto.termsAgreed())
                .memberStatus(MemberStatus.ACTIVE)
                .build();
    }
}
