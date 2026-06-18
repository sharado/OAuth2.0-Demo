package com.sharad.oauthdemo.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class TokenLoggingFilter extends OncePerRequestFilter {

    private final OAuth2AuthorizedClientService clientService;

    public TokenLoggingFilter(OAuth2AuthorizedClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof OAuth2AuthenticationToken oauth) {
            OAuth2AuthorizedClient client =
                    clientService.loadAuthorizedClient(
                            oauth.getAuthorizedClientRegistrationId(),
                            oauth.getName()
                    );

            System.out.println("ACCESS TOKEN = " + client.getAccessToken().getTokenValue());
            System.out.println("TOKEN TYPE = " + client.getAccessToken().getTokenType().getValue());
            System.out.println("SCOPES = " + client.getAccessToken().getScopes());
            System.out.println("TOKEN ISSUED AT= " + client.getAccessToken().getIssuedAt());
            System.out.println("TOKEN EXPIRY AT= " + client.getAccessToken().getExpiresAt());
            System.out.println("-------------------------");
            if (client.getRefreshToken() != null) {
                System.out.println("REFRESH TOKEN = " + client.getRefreshToken().getTokenValue());
                System.out.println("TOKEN ISSUED AT= " + client.getRefreshToken().getIssuedAt());
                System.out.println("TOKEN EXPIRY AT= " + client.getRefreshToken().getExpiresAt());
            }
            System.out.println("-------------------------");
            System.out.println("Principal Name= " + client.getPrincipalName());
            System.out.println("Provider Details= " + client.getClientRegistration().getRegistrationId());
            System.out.println("AuthorizationGrantType( " + client.getClientRegistration().getAuthorizationGrantType());
            System.out.println("ClientId= " + client.getClientRegistration().getClientId());
            System.out.println("getClientSecret= " + client.getClientRegistration().getClientSecret());
            System.out.println("ClientSettings " + client.getClientRegistration().getClientSettings());
            System.out.println("ClientAuthenticationMethod= " + client.getClientRegistration().getClientAuthenticationMethod());
        }

        filterChain.doFilter(request, response);
    }
}
