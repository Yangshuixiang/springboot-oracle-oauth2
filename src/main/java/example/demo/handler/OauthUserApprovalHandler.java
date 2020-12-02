package example.demo.handler;

import example.demo.adapter.ClientDetailsAdapter;
import example.demo.service.OauthClientDetailsService;
import example.demo.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;

/**
 * 重写当对于需要询问是否授权第三方应用时的方法，增加受信任的第三方处理
 */
@Slf4j
public class OauthUserApprovalHandler extends TokenStoreUserApprovalHandler {


    /**
     * 是否已批准授权（第三方应用）
     * @param authorizationRequest
     * @param userAuthentication
     * @return
     */
    @Override
    public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
        if (super.isApproved(authorizationRequest, userAuthentication)) {
            return true;
        }
        if (!userAuthentication.isAuthenticated()) {
            return false;
        }
        OauthClientDetailsService oauthClientDetailsService = SpringContextUtils.getBean("oauthClientDetailsService", OauthClientDetailsService.class);

        ClientDetailsAdapter clientDetails = (ClientDetailsAdapter)oauthClientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
        return clientDetails != null && clientDetails.isTrusted();
    }
}
