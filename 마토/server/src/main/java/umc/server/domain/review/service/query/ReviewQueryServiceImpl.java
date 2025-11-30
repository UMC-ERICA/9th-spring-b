package umc.server.domain.review.service.query;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.review.controller.ReviewController;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.ReviewResDto;
import umc.server.domain.review.entity.QReview;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewQueryDsl;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.repository.StoreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewQueryDsl reviewQueryDsl;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> searchReview(Long storeId, Float rating) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        // ✅ 여기서는 QReview 필드가 아니라 파라미터를 기준으로 null 체크해야 함
        if (storeId != null) {
            builder.and(review.store.id.eq(storeId));
        }

        if (rating != null) {
            float lowerBound = (float) Math.floor(rating);
            float upperBound = lowerBound + 1.0f;
            builder.and(review.rating.goe(lowerBound));
            builder.and(review.rating.lt(upperBound));
        }

        return reviewQueryDsl.searchReview(builder);
    }

    @Override
    public ReviewResDto.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    ){
        Store store = storeRepository.findByName(storeName).orElseThrow();

        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        return ReviewConverter.toReviewPreviewListDTO(result);
    }

    @Override
    public Page<Review> findMyReviews(Long userId, int pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex, 10, Sort.by("createdAt").descending());
        return reviewRepository.findByUser_Id(userId, pageable);
    }
}
