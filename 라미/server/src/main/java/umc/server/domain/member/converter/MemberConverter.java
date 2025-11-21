package umc.server.domain.member.converter;

import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Member;

public class MemberConverter {

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(Member member) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }


    // DTO -> Entity
    public static Member toMember(MemberReqDTO.JoinDTO dto) {
        return Member.builder()
                .name(dto.getName())
                .birth(dto.getBirth())
                .address(dto.getAddress())
                .gender(dto.getGender())
                .build();
    }
}
