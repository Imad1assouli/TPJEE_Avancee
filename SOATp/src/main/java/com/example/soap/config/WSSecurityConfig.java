package com.example.soap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;

import java.util.Map;

@Configuration
public class WSSecurityConfig {

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
        interceptor.setValidationCallbackHandler(callbackHandler());
        interceptor.setValidationActions("UsernameToken");
        return interceptor;
    }

    @Bean
    public SimplePasswordValidationCallbackHandler callbackHandler() {
        SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
        handler.setUsersMap(Map.of("user", "password"));
        return handler;
    }

    @Bean
    public CryptoFactoryBean cryptoFactoryBean() {
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        cryptoFactoryBean.setKeyStorePassword("password");
        return cryptoFactoryBean;
    }
}
