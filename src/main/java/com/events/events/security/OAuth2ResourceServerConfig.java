package com.events.events.security;

import com.events.events.config.EventProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.InMemoryApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public  class OAuth2ResourceServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final TokenStore tokenStore;

    private final EventProperties properties;

    private final AccessTokenConverter accessTokenConverter;


    public OAuth2ResourceServerConfig(@Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager,
                                            TokenStore tokenStore, EventProperties properties, AccessTokenConverter accessTokenConverter) {

        this.authenticationManager = authenticationManager;
        this.tokenStore = tokenStore;
        this.properties = properties;
        this.accessTokenConverter = accessTokenConverter;
    }

    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }

    @Bean
    public ApprovalStore approvalStore() {
        return new InMemoryApprovalStore();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        endpoints
                .authorizationCodeServices(authorizationCodeServices())
                .approvalStore(approvalStore())
                .tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter)
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(properties.getOauth2().getClient_id())
                .secret(properties.getOauth2().getClient_secret())
                .accessTokenValiditySeconds(properties.getOauth2().getToken_validity())
                .refreshTokenValiditySeconds(properties.getOauth2().getRefresh_token_validity())
                .authorizedGrantTypes(properties.getOauth2().getGrant_type().split(","))
                .scopes(properties.getOauth2().getScope().split(","));
    }
}