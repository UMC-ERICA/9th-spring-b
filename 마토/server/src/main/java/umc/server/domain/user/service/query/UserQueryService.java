package umc.server.domain.user.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.user.dto.req.UserReqDTO;
import umc.server.domain.user.dto.res.UserResDTO;


public interface UserQueryService {
    UserResDTO.JoinDTO signup(
            UserReqDTO.JoinDTO dto
    );
}
