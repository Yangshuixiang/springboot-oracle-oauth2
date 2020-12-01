package example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import example.demo.entity.OauthRole;
import example.demo.mapper.OauthRoleMapper;
import org.springframework.stereotype.Service;


@Service
public class OauthRoleService extends ServiceImpl<OauthRoleMapper, OauthRole> {
}
