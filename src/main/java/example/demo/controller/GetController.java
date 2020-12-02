package example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import example.demo.entity.Result;
import example.demo.service.OauthUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get/")
public class GetController {
    @Autowired
    private OauthUserDetailService userService;

    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping("userInfo")
    public Result getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails)authentication.getPrincipal();

        if(principal != null){
            JSONObject user = userService.getProtectedUserInfo(principal.getUsername());
            return Result.ok(user);
        }
        return Result.error("用户不存在");
    }
}
