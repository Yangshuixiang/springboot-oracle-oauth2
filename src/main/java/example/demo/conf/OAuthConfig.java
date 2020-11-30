package example.demo.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/28 16:34
 * @Email: shuixiang.yang@tcl.com
 * 资源保护
 */

//@Configuration
//@EnableResourceServer
public class OAuthConfig extends ResourceServerConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(OAuthConfig.class) ;

    public static final String RESOURCE_ID = "gx_resource_id";

    @Resource
    private RedisConnectionFactory redisConnectionFactory ;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID) ;
        OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
        oAuth2AuthenticationEntryPoint.setExceptionTranslator(webResponseExceptionTranslator());
        resources.authenticationEntryPoint(oAuth2AuthenticationEntryPoint) ;
        resources.tokenExtractor((request) -> {
            String tokenValue = extractToken(request) ;
            if (tokenValue != null) {
                PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(tokenValue, "");
                return authentication;
            }
            return null;
        }) ;
    }
    private String extractToken(HttpServletRequest request) {
        // first check the header... Authorization: Bearer xxx
        String token = extractHeaderToken(request);
        // sencod check the header... access_token: xxx
        if (token == null) {
            token = request.getHeader("access_token") ;
        }
        // bearer type allows a request parameter as well
        if (token == null) {
            logger.debug("Token not found in headers. Trying request parameters.") ;
            token = request.getParameter(OAuth2AccessToken.ACCESS_TOKEN) ;
            if (token == null) {
                logger.debug("Token not found in request parameters.  Not an OAuth2 request.") ;
            } else {
                request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, OAuth2AccessToken.BEARER_TYPE);
            }
        }
        return token;
    }
    private String extractHeaderToken(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaders("Authorization");
        while (headers.hasMoreElements()) { // typically there is only one (most servers enforce that)
            String value = headers.nextElement();
            if ((value.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase()))) {
                String authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
                // Add this here for the auth details later. Would be better to change the signature of this method.
                request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE,
                        value.substring(0, OAuth2AccessToken.BEARER_TYPE.length()).trim());
                int commaIndex = authHeaderValue.indexOf(',');
                if (commaIndex > 0) {
                    authHeaderValue = authHeaderValue.substring(0, commaIndex);
                }
                return authHeaderValue;
            }
        }
        return null;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() ;
        http.requestMatcher(request -> {
            String path = request.getServletPath() ;
            if (path != null && path.startsWith("/demo")) {
                return true ;
            }
            return false ;
        })
                .authorizeRequests()
                .anyRequest()
                .authenticated() ;
    }

    @Bean
    public TokenStore tokenStore() {
        TokenStore tokenStore = null ;
        tokenStore = new RedisTokenStore(redisConnectionFactory) ;
        return tokenStore ;
    }

    @Bean
    public WebResponseExceptionTranslator<?> webResponseExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @SuppressWarnings({ "unchecked", "rawtypes" })
            @Override
            public ResponseEntity translate(Exception e) throws Exception {
                ResponseEntity<OAuth2Exception> responseEntity = super.translate(e) ;
                ResponseEntity<Map<String, Object>> customEntity = exceptionProcess(responseEntity);
                return customEntity ;
            }
        };
    }

    private static ResponseEntity<Map<String, Object>> exceptionProcess(
            ResponseEntity<OAuth2Exception> responseEntity) {
        Map<String, Object> body = new HashMap<>() ;
        body.put("code", -1) ;
        OAuth2Exception excep = responseEntity.getBody() ;
        String errorMessage = excep.getMessage();
        if (errorMessage != null) {
            errorMessage = "认证失败，非法用户" ;
            body.put("message", errorMessage) ;
        } else {
            String error = excep.getOAuth2ErrorCode();
            if (error != null) {
                body.put("message", error) ;
            } else {
                body.put("message", "认证服务异常，未知错误") ;
            }
        }
        body.put("data", null) ;
        ResponseEntity<Map<String, Object>> customEntity = new ResponseEntity<>(body,
                responseEntity.getHeaders(), responseEntity.getStatusCode()) ;
        return customEntity;
    }

}
