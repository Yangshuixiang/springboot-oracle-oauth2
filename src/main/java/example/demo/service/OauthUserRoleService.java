package example.demo.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import example.demo.entity.OauthUserRole;
import example.demo.mapper.OauthUserRoleMapper;
import org.springframework.stereotype.Service;

@Service
public class OauthUserRoleService extends ServiceImpl<OauthUserRoleMapper, OauthUserRole> {
}
