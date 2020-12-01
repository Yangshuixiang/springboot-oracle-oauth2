package example.demo.controller;

import example.demo.controller.dto.StudentDTO;
import example.demo.entity.JsonResult;
import example.demo.entity.Student;
import example.demo.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/26 16:35
 * @Email: shuixiang.yang@tcl.com
 */

@RestController
@RequestMapping("/note")
public class StudentController {

    private Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;

    @PostMapping("/getAll")
    public JsonResult getNotes() {
        logger.info("请求获取student、list");
        JsonResult result = null;

        try {
            List<Student> notes = studentService.selectList();
            result = JsonResult.success(notes);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取studentList,出错");
            result = JsonResult.fail("系统异常");
        }
        return result;
    }

    @PostMapping("/insert")
    public JsonResult insertStuInfo() {
        studentService.insertStuInfo();
        return JsonResult.success();
    }

    @PostMapping("/queryStuByPage")
    public JsonResult queryStuByPage(@RequestBody StudentDTO studentDTO){

        List<Student> pageStu = studentService.queryStuByPage(studentDTO);

        return JsonResult.success(pageStu);
    }
}
