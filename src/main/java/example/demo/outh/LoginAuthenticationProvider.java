package example.demo.outh;

import example.demo.entity.Users;
import example.demo.mapper.IUsersMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/28 16:03
 * @Email: shuixiang.yang@tcl.com
 * <p>
 * 登录认证类
 */

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private IUsersMapper usersRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 登录用户名
        String username = authentication.getName();
        // 凭证（密码）
        Object credentials = authentication.getCredentials();
        Users user = null;
        try {
//            user = new Users();
//            user.setUsername(username);
//            user.setPassword((String) credentials);
//            QueryWrapper queryWrapper = new QueryWrapper();
//            queryWrapper.setEntity(user);
//            user = usersRepository.selectOne(queryWrapper);
            user = usersRepository.findByUsernameAndPassword(username, (String) credentials) ;
            if (user == null) {
                String errorMsg = "错误的用户名或密码";
                throw new BadCredentialsException(errorMsg);
            }
        } catch (Exception e) {
            throw e;
        }
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                user, authentication.getCredentials(), Arrays.asList(
                new SimpleGrantedAuthority("ROLE_USERS"),
                new SimpleGrantedAuthority("ROLE_ACTUATOR")));
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication));
    }

}
