package umc.server.domain.store.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.server.domain.store.entity.QStore;
import umc.server.domain.store.entity.Store;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreQueryDslImpl implements StoreQueryDsl {

    private final EntityManager em;

    @Override
    public Page<Store> searchStores(
            String regionName,
            String keyword,
            String sortBy,
            Pageable pageable
    ) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QStore store = QStore.store;

        // 조건 생성
        BooleanBuilder builder = new BooleanBuilder();

        // 지역 필터
        if (regionName != null && !regionName.isEmpty()) {
            builder.and(store.region.name.eq(regionName));
        }

        // 이름 검색
        if (keyword != null && !keyword.isEmpty()) {
            builder.and(buildNameCondition(store, keyword));
        }

        // 정렬 조건
        OrderSpecifier<?>[] orderSpecifiers = buildOrderSpecifier(store, sortBy);

        // 데이터 조회
        List<Store> content = queryFactory
                .selectFrom(store)
                .leftJoin(store.region).fetchJoin()
                .where(builder)
                .orderBy(orderSpecifiers)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 전체 개수 조회
        Long total = queryFactory
                .select(store.count())
                .from(store)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0L);
    }

    @Override
    public List<Store> searchStoresByCursor(
            String regionName,
            String keyword,
            String sortBy,
            Long cursor,
            int size
    ) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QStore store = QStore.store;

        BooleanBuilder builder = new BooleanBuilder();

        // 지역 필터
        if (regionName != null && !regionName.isEmpty()) {
            builder.and(store.region.name.eq(regionName));
        }

        // 이름 검색
        if (keyword != null && !keyword.isEmpty()) {
            builder.and(buildNameCondition(store, keyword));
        }

        // 커서 조건
        if (cursor != null) {
            builder.and(store.id.lt(cursor));
        }

        // 정렬
        OrderSpecifier<?>[] orderSpecifiers = buildOrderSpecifier(store, sortBy);

        return queryFactory
                .selectFrom(store)
                .leftJoin(store.region).fetchJoin()
                .where(builder)
                .orderBy(orderSpecifiers)
                .limit(size + 1)  // +1로 다음 페이지 존재 확인
                .fetch();
    }

    // 이름 검색 조건 생성
    private BooleanExpression buildNameCondition(QStore store, String keyword) {
        if (keyword.contains(" ")) {
            String[] words = keyword.split("\\s+");
            BooleanExpression result = null;

            for (String word : words) {
                if (!word.isEmpty()) {
                    BooleanExpression condition = store.name.contains(word);
                    result = result == null ? condition : result.or(condition);
                }
            }
            return result;
        } else {
            return store.name.contains(keyword);
        }
    }

    // 정렬 조건 생성
    private OrderSpecifier<?>[] buildOrderSpecifier(QStore store, String sortBy) {
        List<OrderSpecifier<?>> orders = new ArrayList<>();

        if ("name".equals(sortBy)) {
            // 이름순 정렬
            orders.add(store.name.asc());
            // 이름 동일 시 최신순
            orders.add(store.createdAt.desc());
        } else {
            // 기본: 최신순
            orders.add(store.createdAt.desc());
        }

        return orders.toArray(new OrderSpecifier[0]);
    }
}