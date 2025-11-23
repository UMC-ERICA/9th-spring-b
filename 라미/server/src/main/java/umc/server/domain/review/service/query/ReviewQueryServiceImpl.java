package umc.server.domain.review.service.query;

import com.querydsl.core.BooleanBuilder;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.exception.code.MemberErrorCode;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.QReview;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.exception.code.StoreErrorCode;
import umc.server.domain.store.repository.StoreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
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

        return reviewList;
    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page) {
        // 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));
        // 가게에 맞는 리뷰를 가져온다 (offset 페이징)
        PageRequest pageRequest = PageRequest.of(page - 1, 5);  // 사용자는 페이지 1부터 시작
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        // 결과를 응답 DTO로 반환 (컨버터 사용)
        return ReviewConverter.toReviewPreViewListDTO(result);
    }

    @Override
    public ReviewResDTO.MyReviewListDTO findMyReview(Long memberId, Integer page) {
        // 회원 존재 여부 검증
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        // 페이징 객체 생성 및 리뷰 조회
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<Review> result = reviewRepository.findAllByMember(member, pageRequest);

        // DTO 변환
        return ReviewConverter.toMyReviewListDTO(result);
    }
}