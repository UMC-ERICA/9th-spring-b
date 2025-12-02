package umc.server.domain.member.service.kakao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import umc.server.domain.member.dto.res.MemberResDTO;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoApiService {

    private final WebClient webClient = WebClient.builder().build();

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.token-uri}")
    private String tokenUri;

    @Value("${kakao.user-info-uri}")
    private String userInfoUri;

    // 인가 코드로 액세스 토큰 발급받기
    public MemberResDTO.KakaoTokenResponse getAccessToken(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        return webClient.post()
                .uri(tokenUri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(params)
                .retrieve()
                .bodyToMono(MemberResDTO.KakaoTokenResponse.class)
                .block();
    }

    // 액세스 토큰으로 사용자 정보 가져오기
    public MemberResDTO.KakaoUserInfoResponse getUserInfo(String accessToken) {
        return webClient.get()
                .uri(userInfoUri)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(MemberResDTO.KakaoUserInfoResponse.class)
                .block();
    }
}