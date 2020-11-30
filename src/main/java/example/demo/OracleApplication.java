package example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/26 16:10
 * @Email: shuixiang.yang@tcl.com
 */

@SpringBootApplication
//@MapperScan("example.demo.mapper")类已申明@Mapper
public class OracleApplication {

    public static void main(String[] args) {

        SpringApplication.run(OracleApplication.class);
    }
}
