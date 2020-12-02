package example.demo.handler;

import example.demo.entity.Result;
import example.demo.utils.Constant;
import example.demo.utils.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class OauthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException){
        authException.printStackTrace();
        Throwable cause = authException.getCause();

        response.setStatus(HttpStatus.OK.value());
        if(cause instanceof InvalidTokenException) {
            ResultUtil.writeJavaScript(response, Result.error(Constant.ResultStatusCode.INVALID_TOKEN));

        }else{
            ResultUtil.writeJavaScript(response, Result.error(Constant.ResultStatusCode.TOKEN_MISS));
        }
    }
}
