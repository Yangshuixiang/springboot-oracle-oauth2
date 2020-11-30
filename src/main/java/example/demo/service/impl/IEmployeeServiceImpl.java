package example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import example.demo.entity.Employee;
import example.demo.mapper.IEmployeeMapper;
import example.demo.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/26 17:15
 * @Email: shuixiang.yang@tcl.com
 */

@Service
public class IEmployeeServiceImpl implements IEmployeeService {

    @Autowired
    IEmployeeMapper employeeMapper;

    @Override
    public List<Employee> test_query(Integer EMPNO) {
        return employeeMapper.test_query(EMPNO);
    }

    @Override
    public void test_insert(Employee employee) {
        employeeMapper.test_insert(employee);
    }

    @Override
    public void test_update(Integer EMPNO, double COMM) {
        employeeMapper.test_update(EMPNO,COMM);
    }

    @Override
    public void test_delete(Integer EMPNO) {
        employeeMapper.test_delete(EMPNO);
    }

    @Override
    public void test_multi_insert(List<Employee> results) {
        employeeMapper.test_multi_insert(results);
    }

    @Override
    public List<Employee> test_multi_query(int[] DEPTNOArr) {
        return employeeMapper.test_multi_query(DEPTNOArr);
    }

    @Override
    public void test_multi_delete(List<Integer> EMPNOList) {
        employeeMapper.test_multi_delete(EMPNOList);
    }

    @Override
    public List<Employee> selectList() {

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(new Employee());
        return employeeMapper.selectList(queryWrapper);
    }
}
