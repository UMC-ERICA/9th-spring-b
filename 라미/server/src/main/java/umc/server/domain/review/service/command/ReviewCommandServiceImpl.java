package umc.server.domain.review.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    public Review createReview(ReviewReqDTO.CreateDTO request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));

        Review review = ReviewConverter.toReview(request, member, store);

        // DB 저장
        return reviewRepository.save(review);
    }
}
