package umc.server.domain.review.service.query;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import umc.server.domain.member.enums.FoodType;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.QReview;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.enums.RatingLevel;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.exception.code.StoreErrorCode;
import umc.server.domain.store.repository.StoreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    public List<Review> searchReview(Long memberId, Long storeId, RatingLevel ratingLevel) {

        // Q클래스 정의
        QReview review = QReview.review;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용

        builder.and(review.member.id.eq(memberId));

        if (storeId != null) {
            builder.and(review.store.id.eq(storeId));
        }

        if (ratingLevel != null) {
            switch (ratingLevel) {
                case FIVE -> builder.and(review.rating.goe(5.0f));
                case FOUR -> builder.and(review.rating.goe(4.0f).and(review.rating.lt(5.0f)));
                case THREE -> builder.and(review.rating.goe(3.0f).and(review.rating.lt(4.0f)));
                case TWO -> builder.and(review.rating.goe(2.0f).and(review.rating.lt(3.0f)));
                case ONE -> builder.and(review.rating.goe(1.0f).and(review.rating.lt(2.0f)));
            }
        }

        // Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);
        return reviewList;
    }

    @Override
    public List<Review> searchReview(String filter, String type) throws Exception {
        // Q클래스 정의
        QReview review = QReview.review;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용

        // 가게명 검색
        if (filter != null && !filter.isBlank()) {
            builder.and(review.store.storeName.containsIgnoreCase(filter));
        }

        // 음식 카테고리 검색
        if (type != null && !type.isBlank()) {
            try {
                builder.and(review.store.category.eq(type));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("존재하지 않는 음식 타입입니다: " + type);
            }
        }

        return reviewRepository.searchReview(builder);
    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page) {
        // 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findByStoreName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        // 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);
    }
}
