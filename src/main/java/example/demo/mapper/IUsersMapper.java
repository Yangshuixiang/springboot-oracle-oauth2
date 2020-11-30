package example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import example.demo.entity.Users;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/28 15:54
 * @Email: shuixiang.yang@tcl.com
 */
@Mapper
public interface IUsersMapper extends BaseMapper<Users> {

    //登录方法
    Users findByUsernameAndPassword(String username, String password) ;

}
