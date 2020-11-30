package example.demo.service;

import example.demo.entity.Student;

import java.util.List;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/26 16:34
 * @Email: shuixiang.yang@tcl.com
 */
public interface IStudentService {

    List<Student> selectList();

    void insertStuInfo();

}
