package com.devstack.user_service_api.service.impl;

import com.devstack.user_service_api.dto.request.RequestSystemUserDto;
import com.devstack.user_service_api.entity.SystemUser;
import com.devstack.user_service_api.exception.DuplicateEntryException;
import com.devstack.user_service_api.exception.EntryNotFoundException;
import com.devstack.user_service_api.repo.SystemUserRepo;
import com.devstack.user_service_api.service.SystemUserService;
import com.devstack.user_service_api.util.KeycloakSecurityUtil;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemUserServiceImpl implements SystemUserService {

    private final KeycloakSecurityUtil keycloakSecurityUtil;
    private final SystemUserRepo systemUserRepo;

    @Value("${keycloak.config.realm}")
    private String realm;
    @Value("${keycloak.config.client-id}")
    private String clientId;
    @Value("${keycloak.config.secret}")
    private String secret;
    @Value("${spring.security.oauth2.resourceserver.jwt.token-uri}")
    private String keyCloakApiUrl;

    @Override
    public void signup(RequestSystemUserDto requestSystemUserDto) {
        String userId;
        Keycloak keycloak = null;

        UserRepresentation existingUser = null;
        keycloak = keycloakSecurityUtil.getKeycloakInstance();

        existingUser = keycloak.realm(realm).users().search(requestSystemUserDto.getEmail()).stream().findFirst()
                        .orElse(null);

        if(existingUser != null){
            Optional<SystemUser> existsSystemUserData = systemUserRepo.findByEmail(existingUser.getEmail());
            if(existsSystemUserData.isEmpty()){
                keycloak.realm(realm).users().delete(existingUser.getId());
            }else{
                throw new DuplicateEntryException("user already exists");
            }
        }else{
            Optional<SystemUser> byEmail = systemUserRepo.findByEmail(requestSystemUserDto.getEmail());
            if(byEmail.isPresent()){
                systemUserRepo.deleteById(byEmail.get().getUserId());
            }
        }

        UserRepresentation userRep = mapUserRep(requestSystemUserDto);
        Response response = keycloak.realm(realm).users().create(userRep);
        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
            RoleRepresentation roleRepresentation = keycloak.realm(realm).roles().get("user").toRepresentation();
            userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
            keycloak.realm(realm).users().get(userId).roles().realmLevel().add(Arrays.asList(roleRepresentation));

            SystemUser systemUser = SystemUser.builder()
                    .userId(userId)
                    .email(requestSystemUserDto.getEmail())
                    .status(true)
                    .fullName(requestSystemUserDto.getFirstName() + " " + requestSystemUserDto.getLastName())
                    .isEnabled(true)
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .build();
            systemUserRepo.save(systemUser);
        }

    }

    private UserRepresentation mapUserRep(RequestSystemUserDto requestSystemUserDto) {
        UserRepresentation userRep = new UserRepresentation();
        userRep.setUsername(requestSystemUserDto.getEmail());
        userRep.setFirstName(requestSystemUserDto.getFirstName());
        userRep.setLastName(requestSystemUserDto.getLastName());
        userRep.setEmail(requestSystemUserDto.getEmail());
        userRep.setEmailVerified(true);
        userRep.setEnabled(true);

        ArrayList<CredentialRepresentation> creds = new ArrayList<>();
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setValue(requestSystemUserDto.getPassword());
        creds.add(credentialRepresentation);
        userRep.setCredentials(creds);
        return userRep;
    }

    @Override
    public Object login(String email, String password) {
            Optional<SystemUser> selectedUserObj = systemUserRepo.findByEmail(email);
            if (selectedUserObj.isEmpty()) {
                throw new EntryNotFoundException("User not found");
            }
            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("client_id", clientId);
            requestBody.add("grant_type", OAuth2Constants.PASSWORD);
            requestBody.add("username", email);
            requestBody.add("client_secret", secret);
            requestBody.add("password", password);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Object> response = restTemplate.postForEntity(keyCloakApiUrl, requestBody, Object.class);
            return response.getBody();
        }
    }
