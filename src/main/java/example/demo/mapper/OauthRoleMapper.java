package example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import example.demo.entity.OauthRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OauthRoleMapper extends BaseMapper<OauthRole> {
}
