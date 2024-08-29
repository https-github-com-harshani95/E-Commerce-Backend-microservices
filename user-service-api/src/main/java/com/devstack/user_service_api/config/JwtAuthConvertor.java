package com.devstack.user_service_api.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Configuration
public class JwtAuthConvertor  implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        Collection<GrantedAuthority> roles = extractAuthorities(source);
        return new JwtAuthenticationToken(source, roles);
    }

    @Bean
    public Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        if (jwt.getClaim("realm_access") != null) {
            Map<String, Object> realmAccess = jwt.getClaim("realm_access");
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> keycloakRoles = objectMapper.convertValue(realmAccess.get("roles"),
                    new TypeReference<List<String>>() {
                    });
            ArrayList<GrantedAuthority> roles = new ArrayList<>();
            for (String kr : keycloakRoles) {
                roles.add(new SimpleGrantedAuthority(kr));
            }
            return roles;
        }
        return new ArrayList<>();
    }
}
