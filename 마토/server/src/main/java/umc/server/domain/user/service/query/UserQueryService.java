package umc.server.domain.user.service.query;

import jakarta.validation.Valid;
import umc.server.domain.user.dto.req.UserReqDTO;
import umc.server.domain.user.dto.res.UserResDTO;


public interface UserQueryService {

    UserResDTO.LoginDTO login(UserReqDTO.@Valid LoginDTO dto);
}
