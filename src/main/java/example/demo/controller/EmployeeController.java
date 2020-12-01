package example.demo.controller;


import example.demo.entity.Employee;
import example.demo.entity.JsonResult;
import example.demo.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/26 16:35
 * @Email: shuixiang.yang@tcl.com
 */

@Controller
@RequestMapping("/emp")
public class EmployeeController {

    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/getAll")
    @ResponseBody
    public JsonResult getNotes() {
        logger.info("请求获取student、list");
        JsonResult result = null;

        try {
            List<Employee> notes = employeeService.selectList();
            result = JsonResult.success(notes);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取studentList,出错");
            result = JsonResult.fail("系统异常");
        }
        return result;
    }
}
