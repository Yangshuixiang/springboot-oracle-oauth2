package example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import example.demo.entity.OauthUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author alex
 * @date 2020/08/11
 */

@Mapper
public interface OauthUserMapper extends BaseMapper<OauthUser> {

    OauthUser getUserByAccount(String account);
}
