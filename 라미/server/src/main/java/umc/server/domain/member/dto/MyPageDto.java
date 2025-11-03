package umc.server.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPageDto {

    private String name;
    private String email;
    private Integer point;
}
