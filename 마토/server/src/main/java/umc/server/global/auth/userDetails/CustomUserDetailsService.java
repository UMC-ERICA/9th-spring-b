package umc.server.global.auth.userDetails;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import umc.server.domain.user.entity.User;
import umc.server.domain.user.exception.UserException;
import umc.server.domain.user.exception.code.UserErrorCode;
import umc.server.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {
        // 검증할 Member 조회
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));
        // CustomUserDetails 반환
        return new CustomUserDetails(user);
    }
}

