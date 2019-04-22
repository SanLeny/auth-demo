package cn.sanleny.authservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Author: sanleny
 * @Date: 2019-04-21
 * @Description: cn.sanleny.authservice.config
 * @Version: 1.0
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients
//                .inMemory() //将客户端的信息存储在内存中
//                .withClient("user-service") //创建了一个Client为"user-service"的客户端
//                .secret("123456")
//                .scopes("service") //客户端的域
//                .authorizedGrantTypes("refresh_token", "password") //配置类验证类型为 refresh_token和password
//                .accessTokenValiditySeconds(12*300); //5min过期
        //自定义客户端认证
        clients.withClientDetails(customClientDetailsService());
    }

    @Bean
    public ClientDetailsService customClientDetailsService(){
        return new CustomClientDetailsService();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).
                tokenEnhancer(jwtTokenEnhancer()).
                authenticationManager(authenticationManager);
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        //注意此处需要相应的jks文件
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("fzp-jwt.jks"), "fzp123".toCharArray());
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("fzp-jwt"));
        converter.setSigningKey("springcloud");
        return converter;
    }
}
