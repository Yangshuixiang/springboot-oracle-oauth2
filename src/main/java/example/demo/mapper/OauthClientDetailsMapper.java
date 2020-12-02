package example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import example.demo.entity.OauthClientDetails;

@Mapper
public interface OauthClientDetailsMapper extends BaseMapper<OauthClientDetails> {
}
