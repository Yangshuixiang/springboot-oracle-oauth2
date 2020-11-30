package example.demo.conf;

import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/26 17:00
 * @Email: shuixiang.yang@tcl.com
 */
@Configuration
public class MyBatisPlusConfig {


    //配置分页
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置方言
        //paginationInterceptor.setDialectType("oracle");
        return paginationInterceptor;
    }

//    3.4.0以后使用的是MybatisPlusInterceptor：
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
//        return interceptor;
//    }

    /***
     * plus 的性能优化
     */
//    @Bean
//    public PerformanceInterceptor performanceInterceptor() {
//        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//        // SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长
//        //performanceInterceptor.setMaxTime(1000);
//        // SQL是否格式化 默认false
//        performanceInterceptor.setFormat(false);
//        return performanceInterceptor;
//    }
    @Bean
    public OracleKeyGenerator oracleKeyGenerator() {
        return new OracleKeyGenerator();
    }

}
