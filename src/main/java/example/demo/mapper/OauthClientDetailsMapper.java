package example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import example.demo.entity.OauthClientDetails;

/**
 * @author alex
 * @date 2020/07/21
 */

@Mapper
public interface OauthClientDetailsMapper extends BaseMapper<OauthClientDetails> {
}
