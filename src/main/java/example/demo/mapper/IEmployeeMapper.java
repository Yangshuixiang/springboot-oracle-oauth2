package example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import example.demo.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/26 17:09
 * @Email: shuixiang.yang@tcl.com
 */
@Mapper
public interface IEmployeeMapper extends BaseMapper<Employee> {

    //查询。@Param对应参数属性注解，There is no getter for property named 'xx' in 'class java.lang.Integer
    List<Employee> test_query(@Param("EMPNO") Integer EMPNO);

    //插入
    void test_insert(Employee employee);

    //更新
    void test_update(@Param("EMPNO") Integer EMPNO, @Param("COMM") double COMM);

    //删除
    void test_delete(Integer EMPNO);

    //批量插入
    void test_multi_insert(List<Employee> results);

    //批量查询
    List<Employee> test_multi_query(int[] DEPTNOArr);

    //批量删除
    void test_multi_delete(List<Integer> EMPNOList);
}
