package umc.server.domain.review.service.query;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import umc.server.domain.review.dto.ReviewResDto;
import umc.server.domain.review.entity.QReview;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewQueryDsl;

import java.util.List;


public interface ReviewQueryService {

    List<Review> searchReview(Long storeId, Float rating);


    ReviewResDto.ReviewPreViewListDTO findReview(String storeName, Integer page);

    Page<Review> findMyReviews(Long userId, int pageIndex);

}
