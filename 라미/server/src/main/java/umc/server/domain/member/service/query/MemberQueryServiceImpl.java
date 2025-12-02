package umc.server.domain.member.service.query;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.ChallengingMissionDto;
import umc.server.domain.member.dto.MyMissionDto;
import umc.server.domain.member.dto.MyPageDto;
import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.exception.code.MemberErrorCode;
import umc.server.domain.member.repository.MemberMissionRepository;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.global.auth.jwt.JwtUtil;
import umc.server.global.auth.service.CustomUserDetails;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;


    public MyPageDto getMyPageInfo(Long memberId) {
        MyPageDto myPageInfo = memberRepository.findMyPageInfoById(memberId);

        if (myPageInfo == null) {
            throw new IllegalArgumentException("사용자 정보를 찾을 수 없습니다.");
        }
        return myPageInfo;
    }

    public Page<MyMissionDto> getMyMissions(Long memberId, Pageable pageable) {
        List<MissionStatus> statuses = List.of(MissionStatus.IN_PROGRESS, MissionStatus.COMPLETED);

        return memberRepository.findMyMissions(memberId, statuses, pageable);
    }

    public Page<ChallengingMissionDto> getMyChallengingMissionsInRegion(Long memberId, Long regionId, Pageable pageable) {
        MissionStatus status = MissionStatus.IN_PROGRESS;

        return memberMissionRepository.findChallengingMissions(memberId, regionId, status, pageable);
    }

    @Override
    public MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto) {
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 비번 검증
        if(!encoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID);
        }

        // jwt 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        String accessToken = jwtUtil.createAccessToken(userDetails);

        return MemberConverter.toLoginDTO(member, accessToken);
    }
}
