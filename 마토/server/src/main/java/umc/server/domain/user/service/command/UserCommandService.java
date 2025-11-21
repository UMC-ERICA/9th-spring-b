package umc.server.domain.user.service.command;

import umc.server.domain.user.dto.req.UserReqDTO;
import umc.server.domain.user.dto.res.UserResDTO;

public interface UserCommandService {

    UserResDTO.JoinDTO signup(
            UserReqDTO.JoinDTO dto
    );
}
