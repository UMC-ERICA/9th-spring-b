package umc.server.domain.review.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.review.entity.QReview;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<Review> searchReview(String query, String type) {

        // Q클래스 정의
        QReview review = QReview.review;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // 동적 쿼리: 검색 조건
        if (type.equals("store")) {
            // 가게명으로 검색
            builder.and(review.store.name.contains(query));
        }

        if (type.equals("rating")) {
            // 별점 이상으로 검색
            builder.and(review.rating.goe(Float.parseFloat(query)));
        }

        if (type.equals("both")) {
            // & 기준 변환
            String[] parts = query.split("&");
            String storeName = parts[0].trim();
            String ratingValue = parts[1].trim();

            // 동적 쿼리
            builder.and(review.store.name.contains(storeName));
            builder.and(review.rating.goe(Float.parseFloat(ratingValue)));
        }

        // Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);

        // 리턴
        return reviewList;
    }
}