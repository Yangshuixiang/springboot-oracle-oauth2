package example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import example.demo.entity.Student;
import example.demo.mapper.IStudentMapper;
import example.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/26 16:36
 * @Email: shuixiang.yang@tcl.com
 */
@Service
public class IStudentServiceImpl implements IStudentService {

    @Autowired
    IStudentMapper studentMapper;

    @Override
    public List<Student> selectList() {

        Student student = new Student();
        //student.setNama("张三");
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(student);
        List<Student> studentList = studentMapper.selectList(queryWrapper);
        return studentList;
    }

    @Override
    public void insertStuInfo() {
        Student student = null;
        for (int i = 1; i <= 100000; i++) {
            //int classId = i%2==0?1:2;
            student = new Student("吴大"+i,"吴二台"+i,"","");
//            student.setClassId(classId);
//            student.setName("吴大"+i);
//            student.setNickName("吴二台"+i);
//            student.setMark("");
//            student.setGuardian("");
            studentMapper.insert(student);
        }
    }
}
