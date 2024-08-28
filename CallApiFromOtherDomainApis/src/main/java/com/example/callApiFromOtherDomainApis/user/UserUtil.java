package com.example.callApiFromOtherDomainApis.user;

import com.example.callApiFromOtherDomainApis.config.RestTemplateConfig;
import com.example.callApiFromOtherDomainApis.dto.AuthenticationRequest;
import com.example.callApiFromOtherDomainApis.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserUtil {


    public static final String URI_API_USER_PROFILE = "/api/v1/employees";
//    @Value("http://localhost:9000")
    public static final String urlWeb = "http://localhost:8081";

    public static final String URI_API_GET_TOKEN = "/identity/auth/token-other-service";

    public String getToken() {
        RestTemplateConfig restTemplateConfig = new RestTemplateConfig();
        RestTemplate restTemplate = restTemplateConfig.restTemplate();
        String token = null;
        String restUrl = urlWeb + URI_API_GET_TOKEN;
        // Tạo HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        AuthenticationRequest info = new AuthenticationRequest();
        info.setUsername("admin");
        info.setPassword("admin");
        // Tạo HttpEntity với DTO và headers
        HttpEntity<AuthenticationRequest> entity = new HttpEntity<>(info, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(restUrl, HttpMethod.POST
                , entity, String.class);
        token = responseEntity.getBody();
        return token;
    }

    public List<UserResponse> getAllUsers(String token) {
        RestTemplateConfig restTemplateConfig = new RestTemplateConfig();
        RestTemplate restTemplate = restTemplateConfig.restTemplate();
        List<UserResponse> users = new ArrayList<>();
        String restUrl = urlWeb + URI_API_USER_PROFILE;
        try {
            HttpEntity<?> entity = configRequestHeader(token);
            ResponseEntity<List<UserResponse>> response = restTemplate.exchange(restUrl
                    , HttpMethod.GET, entity, new ParameterizedTypeReference<List<UserResponse>>() {});
            users = response.getBody();

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.FORBIDDEN)) {
                log.error("Forbidden");
            } else {
                log.error("Error token");
            }

        } catch (Exception e) {
            log.error("Exception: " + e.getMessage() + " \n Stack trace: " + e);
        }
        return users;

    }

    private HttpEntity<?> configRequestHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-Type", "application/json");
        return new HttpEntity<>(headers);
    }

}
