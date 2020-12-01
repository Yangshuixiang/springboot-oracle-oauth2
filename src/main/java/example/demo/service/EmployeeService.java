package example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import example.demo.entity.Employee;
import example.demo.mapper.IEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/26 17:15
 * @Email: shuixiang.yang@tcl.com
 */

@Service
public class EmployeeService extends ServiceImpl<IEmployeeMapper, Employee> {

    @Autowired
    IEmployeeMapper employeeMapper;


    public List<Employee> test_query(Integer EMPNO) {
        return employeeMapper.test_query(EMPNO);
    }

    public void test_insert(Employee employee) {
        employeeMapper.test_insert(employee);
    }


    public void test_update(Integer EMPNO, double COMM) {
        employeeMapper.test_update(EMPNO,COMM);
    }


    public void test_delete(Integer EMPNO) {
        employeeMapper.test_delete(EMPNO);
    }


    public void test_multi_insert(List<Employee> results) {
        employeeMapper.test_multi_insert(results);
    }


    public List<Employee> test_multi_query(int[] DEPTNOArr) {
        return employeeMapper.test_multi_query(DEPTNOArr);
    }


    public void test_multi_delete(List<Integer> EMPNOList) {
        employeeMapper.test_multi_delete(EMPNOList);
    }


    public List<Employee> selectList() {

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(new Employee());
        return employeeMapper.selectList(queryWrapper);
    }
}
