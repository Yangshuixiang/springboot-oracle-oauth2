package example.demo.conf;

import example.demo.entity.App;
import example.demo.entity.OAuthClientDetails;
import example.demo.entity.Users;
import example.demo.mapper.IAppMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.approval.InMemoryApprovalStore;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/28 15:56
 * @Email: shuixiang.yang@tcl.com
 */

@Configuration
@EnableAuthorizationServer
public class OAuthAuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    IAppMapper appRepository;
    @Resource
    RedisConnectionFactory redisConnectionFactory;
    @Resource
    AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security)
            throws Exception {
        security.tokenKeyAccess("permitAll()") // isAuthenticated()
                .checkTokenAccess("permitAll()") // 允许访问 /oauth/check_token 接口
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        // 自定义CODE
        endpoints.authorizationCodeServices(new InMemoryAuthorizationCodeServices() {
            @Override
            public String createAuthorizationCode(OAuth2Authentication authentication) {
                String code = UUID.randomUUID().toString().replaceAll("-", "");
                store(code, authentication);
                return code;
            }
        });
        endpoints.exceptionTranslator(new DefaultWebResponseExceptionTranslator() {
            @SuppressWarnings({"unchecked", "rawtypes"})
            @Override
            public ResponseEntity translate(Exception e) throws Exception {
                ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
                ResponseEntity<Map<String, Object>> customEntity = exceptionProcess(responseEntity);
                return customEntity;
            }
        });
        // 要想使用密码模式这个步骤不能少，否则默认情况下的只支持除密码模式外的其它4中模式
        endpoints.authenticationManager(authenticationManager);
        /**
         * 如果重新定义了TokenServices 那么token有效期等信息需要重新定义
         * 这时候在ClientDetailsServiceConfigurer中设置的有效期将会无效
         */
        endpoints.tokenServices(tokenService()); // 生成token的服务
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.values()); // 获取token 时 允许所有的方法类型
        endpoints.accessTokenConverter(defaultTokenConvert()); // token生成方式
        endpoints.tokenStore(tokenStore());
        endpoints.pathMapping("/oauth/error", "/oauth/customerror");
        // endpoints.addInterceptor(new XXXX()) ; // 在这里可以配置拦截器
        endpoints.requestValidator(new OAuth2RequestValidator() {
            @Override
            public void validateScope(AuthorizationRequest authorizationRequest, ClientDetails client)
                    throws InvalidScopeException {
                //logger.info("放行...") ;
            }

            @Override
            public void validateScope(TokenRequest tokenRequest, ClientDetails client)
                    throws InvalidScopeException {
                //logger.info("放行...") ;
            }

        });
        endpoints.approvalStore(new InMemoryApprovalStore());
    }

    @Bean
    public ClientDetailsService clientDetailsService() {
        return (clientId) -> {
            if (clientId == null) {
                throw new ClientRegistrationException("未知的客户端: " + clientId);
            }
//            App app = new App();
//            app.setClientId(clientId);
//            QueryWrapper queryWrapper = new QueryWrapper();
//            queryWrapper.setEntity(app);
//            app = appRepository.selectOne(queryWrapper);
            App app = appRepository.findByClientId(clientId);
            if (app == null) {
                throw new ClientRegistrationException("未知的客户端: " + clientId);
            }
            // 因为每一个客户端都可以对应多个认证授权类型，跳转URI等信息，这里为了简单就为每一个客户端固定了这些信息
            OAuthClientDetails clientDetails = new OAuthClientDetails();
            clientDetails.setClientId(clientId);
            clientDetails.setClientSecret(app.getClientSecret());
            Set<String> registeredRedirectUri = new HashSet<>();
            registeredRedirectUri.add(app.getRedirectUri());
            clientDetails.setRegisteredRedirectUri(registeredRedirectUri);
            clientDetails.setScoped(false);
            clientDetails.setSecretRequired(true);
            clientDetails.setScope(new HashSet<String>());
            Set<String> authorizedGrantTypes = new HashSet<>();
            authorizedGrantTypes.add("authorization_code");
            authorizedGrantTypes.add("implicit");
            authorizedGrantTypes.add("password");
            authorizedGrantTypes.add("refresh_token");
            authorizedGrantTypes.add("client_credentials");
            clientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            clientDetails.setAuthorities(authorities);
            return clientDetails;
        };
    }

    // 如下Bean可用来增加获取Token时返回信息（需要在TokenServices中增加）
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new TokenEnhancer() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                System.out.println(authentication);
                if (accessToken instanceof DefaultOAuth2AccessToken) {
                    DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
                    Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();
                    additionalInformation.put("username", ((Users) authentication.getPrincipal()).getUsername());
                    additionalInformation.put("create_time",
                            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    token.setAdditionalInformation(additionalInformation);
                }
                return accessToken;
            }
        };
    }

    @Bean
    @Primary
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices tokenService = new DefaultTokenServices();
        tokenService.setSupportRefreshToken(true); // 如果不设置返回的token 将不包含refresh_token
        tokenService.setReuseRefreshToken(true);
        tokenService.setTokenEnhancer(tokenEnhancer()); // 在这里设置JWT才会生效
        tokenService.setTokenStore(tokenStore());
        tokenService.setAccessTokenValiditySeconds(60 * 60 * 24 * 3); // token有效期
        tokenService.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7); // 30 * 24 * 60 * 60；刷新token （必须在token没有过期前使用）
        return tokenService;
    }


    @Bean
    public TokenStore tokenStore() {
        TokenStore tokenStore = null;
        tokenStore = new RedisTokenStore(redisConnectionFactory);
        return tokenStore;
    }

    @Bean
    public DefaultAccessTokenConverter defaultTokenConvert() {
        DefaultAccessTokenConverter defaultTokenConvert = new DefaultAccessTokenConverter();
        return defaultTokenConvert;
    }

    private static ResponseEntity<Map<String, Object>> exceptionProcess(
            ResponseEntity<OAuth2Exception> responseEntity) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", -1);
        OAuth2Exception excep = responseEntity.getBody();
        String errorMessage = excep.getMessage();
        if (errorMessage != null) {
            errorMessage = "认证失败，非法用户";
            body.put("message", errorMessage);
        } else {
            String error = excep.getOAuth2ErrorCode();
            if (error != null) {
                body.put("message", error);
            } else {
                body.put("message", "认证服务异常，未知错误");
            }
        }
        body.put("data", null);
        ResponseEntity<Map<String, Object>> customEntity = new ResponseEntity<>(body,
                responseEntity.getHeaders(), responseEntity.getStatusCode());
        return customEntity;
    }

}
