package example.demo.controller;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LogoutController {

    /**
     * 单点退出
     * @param request
     * @param response
     */
    @RequestMapping("oauth/exit")
    public void exit(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, null, null);
        try {
            response.sendRedirect(request.getHeader("referer"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
