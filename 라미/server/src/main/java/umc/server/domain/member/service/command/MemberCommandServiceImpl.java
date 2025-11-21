package umc.server.domain.member.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.mapping.MemberFood;
import umc.server.domain.member.exception.FoodException;
import umc.server.domain.member.exception.code.FoodErrorCode;
import umc.server.domain.member.repository.FoodRepository;
import umc.server.domain.member.repository.MemberFoodRepository;
import umc.server.domain.member.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;

    // 회원가입
    @Override
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {
        // 사용자 생성
        Member member = MemberConverter.toMember(dto);
        memberRepository.save(member);

        // 선호 음식 존재 여부 확인
        if (dto.getFoodCategory() != null && !dto.getFoodCategory().isEmpty()) {
            List<MemberFood> memberFoodList = dto.getFoodCategory().stream() // 스트림으로 dto의 음식 카테고리 순회
                    .map(category -> MemberFood.builder()
                            .member(member)
                            .food(foodRepository.findByCategory(category)
                                    .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)))
                            .build()
                    )
                    .collect(Collectors.toList());
            memberFoodRepository.saveAll(memberFoodList);
        }
        /*if (dto.getFoodCategory() != null && !dto.getFoodCategory().isEmpty()) { // 체크 순서 중요(null에 empty 체크하면 에러)
            List<MemberFood> memberFoodList = new ArrayList<>();

            // 선호 음식 카테고리별 조회
            for (FoodCategory category : dto.getFoodCategory()) {
                Food food = foodRepository.findByCategory(category)
                        .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)); // orElseThrow은 Optional 클래스 메서드

                // MemberFood 엔티티 생성
                MemberFood memberFood = MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build();

                memberFoodList.add(memberFood);
            }
            memberFoodRepository.saveAll(memberFoodList);
        }*/

        // 응답 dto 생성
        return MemberConverter.toJoinDTO(member);
    }
}
