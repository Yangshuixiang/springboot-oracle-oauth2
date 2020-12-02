package example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import example.demo.controller.dto.StudentDTO;
import example.demo.entity.JsonResult;
import example.demo.entity.Result;
import example.demo.entity.Student;
import example.demo.service.OauthUserDetailService;
import example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/get/")
public class GetController {
    @Autowired
    private OauthUserDetailService userService;
    @Autowired
    private StudentService studentService;

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

    @PostMapping("queryStuByPage")
    public Result queryStuByPage(@RequestBody StudentDTO studentDTO){
        IPage<Student> pageStu = studentService.queryStuByPage(studentDTO);
        return Result.ok(pageStu);
    }
}
