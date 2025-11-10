package umc.server.domain.test.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.test.exception.TestException;
import umc.server.domain.test.exception.code.TestErrorCode;

@Service
@RequiredArgsConstructor // 생성자 주입
public class TestQueryServiceImpl implements TestQueryService {

    @Override
    public void checkFlag(Long flag) {
        if (flag == 1) {
            throw new TestException(TestErrorCode.TEST_EXCEPTION);
        }
    }
}
