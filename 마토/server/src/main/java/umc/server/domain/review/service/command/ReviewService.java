package umc.server.domain.review.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.mapping.entitiy.UserMission;
import umc.server.domain.mapping.repository.UserMissionRepository;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserMissionRepository userMissionRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public Review createReview(Long userId, Long storeId, String content, Float rating){


        UserMission userMission = userMissionRepository.findByUserIdAndMissionId(userId, storeId)
                .orElseThrow(() -> new IllegalArgumentException("유저 미션 정보를 찾을 수 없습니다."));

        if (!userMission.getIsCompleted()) {
            throw new IllegalStateException("아직 완료되지 않은 미션입니다.");
        }

        Review review = Review.builder()
                .user(userMission.getUser())
                .store(userMission.getMission().getStore())
                .content(content)
                .rating(rating)
                .createdAt(LocalDateTime.now())
                .build();

        return reviewRepository.save(review);
    }
}
