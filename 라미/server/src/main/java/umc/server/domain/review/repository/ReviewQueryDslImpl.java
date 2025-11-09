package umc.server.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.server.domain.review.entity.QReview;
import umc.server.domain.review.entity.Review;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    //private final ReviewRepository reviewRepository;
    private final EntityManager em;

    // 검색 API
    @Override
    public List<Review> searchReview(
            Predicate predicate
    ){
        // JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        // Q클래스 선언
        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .leftJoin(review.store).fetchJoin()
                .leftJoin(review.member).fetchJoin()
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}
