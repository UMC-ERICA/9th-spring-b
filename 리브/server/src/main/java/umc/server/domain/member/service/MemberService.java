package umc.server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void changeUsername(Long memberId, String newUsername) {
        int updated = memberRepository.updateUsername(memberId, newUsername);
        if (updated == 0) {
            throw new IllegalArgumentException("업데이트 대상 회원이 없습니다.");
        }
    }
}
