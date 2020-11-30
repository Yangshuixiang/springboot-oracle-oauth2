package example.demo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/28 15:57
 * @Email: shuixiang.yang@tcl.com
 */

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //密码模式必须设置对应的AuthenticationManager，所以这里必须暴露出来，否则系统找不到。
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
