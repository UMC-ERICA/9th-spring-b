package umc.server.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.review.entity.QReview;
import umc.server.domain.review.entity.Review;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager entityManager;

    // 검색 API
    @Override
    public List<Review> searchReview(Predicate predicate) {

        // JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        // Q클래스 선언
        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .where(predicate)
                .fetch();
    }
}
