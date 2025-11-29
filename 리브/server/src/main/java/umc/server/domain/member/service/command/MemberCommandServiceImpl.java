package umc.server.domain.member.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Food;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.MemberFood;
import umc.server.domain.member.exception.FoodException;
import umc.server.domain.member.exception.code.FoodErrorCode;
import umc.server.domain.member.repository.FoodRepository;
import umc.server.domain.member.repository.MemberFoodRepository;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.global.auth.enums.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;

    // Password Encoder
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    @Transactional
    public MemberResDTO.JoinResponse signup(MemberReqDTO.JoinRequest dto) {
        // 솔트된 비밀번호 생성
        String salt = passwordEncoder.encode(dto.password());

        // 사용자 생성
        Member member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);

        // DB 적용
        memberRepository.save(member);

        // 선호 음식 존재 여부
        if (dto.preferCategory().size() > 1) {
//            List<MemberFood> memberFoodList = new ArrayList<>();
//
//            // 선호 음식 ID별 조회
//            for (Long id : dto.preferCategory()) {
//
//                // 음식 존재 여부 검증
//                Food food = foodRepository.findById(id)
//                        .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));
//
//                // MemberFood 엔티티 생성 (Converter 사용해야 함)
//                MemberFood memberFood = MemberFood.builder()
//                        .member(member)
//                        .food(food)
//                        .build();
//
//                // 사용자 - 음식 (선호 음식) 추가
//                memberFoodList.add(memberFood);
//            }

            List<MemberFood> memberFoodList = dto.preferCategory().stream()
                    .map(id -> MemberFood.builder()
                            .member(member)
                            .food(foodRepository.findById(id)
                                    .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)))
                            .build()
                    )
                    .collect(Collectors.toList());

            // 모든 선호 음식 추가 : DB 적용
            memberFoodRepository.saveAll(memberFoodList);
        }

        // 응답 DTO 생성
        return MemberConverter.toJoinResponseDTO(member);
    }
}
