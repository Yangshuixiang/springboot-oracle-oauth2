package example.demo.controller;

import example.demo.adapter.ClientDetailsAdapter;
import example.demo.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Set;

/**
 * @author alex
 * @date 2020/08/11
 */
@RestController
@SessionAttributes("authorizationRequest")
public class ApprovalController {

    @Autowired
    private OauthClientDetailsService clientDetailsService;

    /**
     * 自定义批准页面
     *
     * @param model
     * @return
     */
    @RequestMapping("approval")
    public ModelAndView approval(Map<String, Object> model) {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");

        ModelAndView view = new ModelAndView();
        view.setViewName("approval");

        ClientDetailsAdapter clientDetails =
                (ClientDetailsAdapter) clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());

        Set<String> scope = authorizationRequest.getScope();
        if (scope.contains("read")) {
            view.addObject("get", true);
        } else {
            view.addObject("get", false);
        }
        if (authorizationRequest.getAuthorities().contains(new SimpleGrantedAuthority("admin"))
                && scope.contains("write")) {
            view.addObject("update", true);
        } else {
            view.addObject("update", false);
        }

        if (clientDetails != null) {
            view.addObject("appName", clientDetails.getClientDetails().getAppName());
        }

        return view;
    }
}
