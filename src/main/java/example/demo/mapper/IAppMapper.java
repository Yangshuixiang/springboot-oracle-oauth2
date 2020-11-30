package example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import example.demo.entity.App;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/28 15:53
 * @Email: shuixiang.yang@tcl.com
 */

@Mapper
//@CacheNamespace(flushInterval = 10)
/*二级缓存的应用场景：
    对于访问多的查询请求且用户对查询结果实时性要求不高，此时可采用mybatis二级缓存技术降低数据库访问量，提高访问速度，业务场景比如：耗时较高的统计分析sql、电话账单查询sql等。
    实现方法如下：通过设置刷新间隔时间，由mybatis每隔一段时间自动清空缓存，根据数据变化频率设置缓存刷新间隔flushInterval，比如设置为30分钟、60分钟、24小时等，根据需求而定。
    一般情况，不要开启二级缓存！
    局限性太大！
    比如一个mapper.xml中的查询sql，关联了多个表！ 其中一个表的数据被修改了，又没到刷新时间，就无法获取到最新的数据！！！
    使用二级缓存，慎之又慎！！！
*/
public interface IAppMapper extends BaseMapper<App> {

    //根据clientId获取客户端信息
    App findByClientId(String clientId) ;
}
