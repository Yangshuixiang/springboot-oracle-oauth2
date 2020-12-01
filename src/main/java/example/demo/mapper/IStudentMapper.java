package example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import example.demo.controller.dto.StudentDTO;
import example.demo.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/26 16:11
 * @Email: shuixiang.yang@tcl.com
 */
@Mapper
public interface IStudentMapper extends BaseMapper<Student> {

    IPage<Map<String, Object>> queryByCondition(StudentDTO query);

    Map<String, Object> deviceDetail(StudentDTO query);

    Integer getDeviceActive(Map<String, Object> result);

    @Select("select s.name,s.user_id,s.nick_name,c.class_name from student s left join t_class c on s.class_id = c.class_id ${ew.customSqlSegment}")
    IPage<Student> queryStuByPage(IPage<Student> page, @Param("ew")QueryWrapper queryWrapper);
}
