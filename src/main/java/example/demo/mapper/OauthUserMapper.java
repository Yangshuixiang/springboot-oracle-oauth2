package example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import example.demo.entity.OauthUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OauthUserMapper extends BaseMapper<OauthUser> {

    OauthUser getUserByAccount(String account);
}
