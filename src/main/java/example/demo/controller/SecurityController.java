package example.demo.controller;

import example.demo.entity.Result;
import example.demo.utils.MD5Util;
import example.demo.utils.RandImageUtil;
import example.demo.utils.RandomUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author alex
 * @date 2020/07/17
 */
@RestController
public class SecurityController {
    @Resource
    private RedisTemplate<String, String> redisTemplate;


    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("oauthTest")
    public String oauthTest() {
        return "oauthTest";
    }

    @RequestMapping("resourceTest")
    public String resourceTest() {
        return "resourceTest";
    }

    @GetMapping("/code/{key}")
    @ResponseBody
    public Result getCode(@PathVariable String key) {
        String code = RandomUtils.randomGen(4);
        String lowerCaseCode = code.toLowerCase();
        String realKey = MD5Util.MD5Encode(lowerCaseCode + key, "utf-8");

        redisTemplate.opsForValue().set(realKey, code, 5, TimeUnit.MINUTES);

        try {
            String base64 = RandImageUtil.generate(code);
            return Result.ok(base64);
        } catch (IOException e) {
            return Result.error("获取验证码错误");
        }
    }

}
