package example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import example.demo.controller.dto.StudentDTO;
import example.demo.entity.Student;
import example.demo.mapper.IStudentMapper;
import example.demo.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/26 16:36
 * @Email: shuixiang.yang@tcl.com
 */
@Service
public class StudentService extends ServiceImpl<IStudentMapper, Student> {

    @Autowired
    IStudentMapper studentMapper;

    public List<Student> selectList() {

        Student student = new Student();
        //student.setNama("张三");
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(student);
        List<Student> studentList = studentMapper.selectList(queryWrapper);
        return studentList;
    }

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

    public List<Student> queryStuByPage(StudentDTO studentDTO) {
        //分页条件设置
        if (studentDTO.getOffset() < 1) {
            studentDTO.setOffset(Constant.DEFAULT_NUM);
        }
        if (studentDTO.getLimit() < 1) {
            studentDTO.setLimit(Constant.DEFAULT_SIZE);
        }
        //分页搜索
        IPage<Student> page = new Page(studentDTO.getOffset(), studentDTO.getLimit());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("name");
        if (!StringUtils.isEmpty(studentDTO.getName())){
            queryWrapper.like("name",studentDTO.getName());
        }
        if (!StringUtils.isEmpty(studentDTO.getNickName())){
            queryWrapper.like("nick_name",studentDTO.getNickName());
        }
        if (!StringUtils.isEmpty(studentDTO.getGuardian())){
            queryWrapper.like("guardian",studentDTO.getGuardian());
        }
        if (!StringUtils.isEmpty(studentDTO.getClassId())){
            queryWrapper.eq("class_id",studentDTO.getClassId());
        }
        IPage<Student> pageResult = studentMapper.queryStuByPage(page, queryWrapper);

        return null;
    }
}
