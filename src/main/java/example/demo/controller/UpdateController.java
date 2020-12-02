package example.demo.controller;

import example.demo.entity.Result;
import example.demo.service.OauthUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/update/")
public class UpdateController {
    @Autowired
    private OauthUserDetailService userDetailService;

    /**
     * 修改用户姓名
     * @param username
     * @return
     */
    @RequestMapping("username")
    public Result updateUserName(String username){
        if(username == null || "".equals(username.trim())){
            return Result.error("用户姓名不能为空");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails)authentication.getPrincipal();

        if(userDetailService.updateUserName(principal.getUsername(), username)){
            return Result.ok();
        }else{
            return Result.error("修改用户姓名失败");
        }
    }
}
