package umc.server.domain.user.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.mapping.entitiy.UserFood;
import umc.server.domain.user.converter.UserConverter;
import umc.server.domain.user.dto.req.UserReqDTO;
import umc.server.domain.user.dto.res.UserResDTO;
import umc.server.domain.user.entity.Food;
import umc.server.domain.user.entity.User;
import umc.server.domain.user.repository.FoodRepository;
import umc.server.domain.user.repository.UserFoodRepository;
import umc.server.domain.user.repository.UserRepository;
import umc.server.global.auth.enums.Role;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService{

    private final UserRepository userRepository;
    private final UserFoodRepository userFoodRepository;
    private final FoodRepository foodRepository;

    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Override
    @Transactional
    public UserResDTO.JoinDTO signup(UserReqDTO.UserJoinDTO dto) {
        // 사용자 생성
        String salt = passwordEncoder.encode(dto.password());
        User user = UserConverter.toUser(dto, salt, Role.ROLE_USER);
        userRepository.save(user); // DB에 저장


        // 선호 음식 존재 여부 확인
        if (dto.preferCategory() != null && !dto.preferCategory().isEmpty()) {
            List<UserFood> userFoodList = new ArrayList<>();

            // 선호 음식 ID별 조회
            for (Long foodId : dto.preferCategory()) {
                // 음식 존재 여부 검증
                Food food = foodRepository.findById(foodId)
                        .orElseThrow(() -> new RuntimeException("Food not found"));

                // UserFood 엔티티 생성
                UserFood userFood = UserFood.builder()
                        .user(user)
                        .food(food)
                        .favorite(true) // 예시로 선호 여부를 true로 설정
                        .build();

                // 사용자 - 음식 관계 추가
                userFoodList.add(userFood);
            }

            // 모든 선호 음식 추가: DB에 적용
            userFoodRepository.saveAll(userFoodList);
        }

        // 응답 DTO 생성
        return UserConverter.toJoinDTO(user);
    }
}
