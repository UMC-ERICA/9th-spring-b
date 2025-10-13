package umc.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.review.dto.ReviewRequestDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public Review createReview(ReviewRequestDTO.CreateDTO request) {
        // 요청 DTO에서 받은 ID로 Member와 Store 엔티티를 DB에서 조회합니다.
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));

        // 새로운 Review 엔티티 객체를 생성합니다.
        Review newReview = Review.builder()
                .member(member)
                .store(store)
                .title(request.getTitle())
                .content(request.getContent())
                .rating(request.getRating())
                .build();

        // Repository의 save() 메서드를 호출하여 DB에 저장합니다.
        // 이 때 JPA가 INSERT SQL을 생성하여 실행합니다.
        return reviewRepository.save(newReview);
    }
}
