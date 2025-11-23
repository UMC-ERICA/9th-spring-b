package umc.server.domain.user.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.user.dto.req.UserReqDTO;
import umc.server.domain.user.dto.res.UserResDTO;
import umc.server.domain.user.entity.User;
import umc.server.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService{

    private final UserRepository userRepository;

    @Override
    public UserResDTO.JoinDTO signup(UserReqDTO.JoinDTO dto) {
        return null;
    }
}
