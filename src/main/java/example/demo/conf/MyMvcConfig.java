package example.demo.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/12/2 13:55
 * @Email: shuixiang.yang@tcl.com
 */


@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /**
     * 拦截某个请求跳转固定位置
     * spring 5抛弃了WebMvcConfigurerAdapter  使用 WebMvcConfigurationSupport 代替了它
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/oauthTest").setViewName("oauthTest");
        registry.addViewController("/oauthTest.html").setViewName("oauthTest");
        registry.addViewController("/resourceTest").setViewName("resourceTest");
        registry.addViewController("/resourceTest.html").setViewName("resourceTest");
    }


}
