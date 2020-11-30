package example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import example.demo.controller.dto.StudentDTO;
import example.demo.entity.Student;
import org.apache.ibatis.annotations.Mapper;

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
}
