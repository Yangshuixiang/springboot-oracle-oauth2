package example.demo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/28 15:59
 * @Email: shuixiang.yang@tcl.com
 */

@Data
public class OAuthClientDetails implements ClientDetails, Serializable {

    private static final long serialVersionUID = 1L;

    private String id ;

    private String clientId ;

    private boolean secretRequired ;

    private String clientSecret ;

    private boolean scoped ;

    private Set<String> resourceIds ;

    private Set<String> scope = new HashSet<>();

    private Set<String> authorizedGrantTypes = new HashSet<>();

    private Set<String> registeredRedirectUri = new HashSet<>();

    private Collection<GrantedAuthority> authorities ;

    private boolean autoApprove ;

    private Integer accessTokenValiditySeconds ;

    private Integer refreshTokenValiditySeconds ;

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
