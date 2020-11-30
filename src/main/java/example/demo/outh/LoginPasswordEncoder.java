package example.demo.outh;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/28 16:04
 * @Email: shuixiang.yang@tcl.com
 */


@Component
public class LoginPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString() ;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return this.encode(rawPassword).equals(encodedPassword) ;
    }

}
