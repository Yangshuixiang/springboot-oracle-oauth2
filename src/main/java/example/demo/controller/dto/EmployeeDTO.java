package example.demo.controller.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/26 17:16
 * @Email: shuixiang.yang@tcl.com
 */

@Accessors(chain = true)
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class EmployeeDTO {

    private Integer EMPNO;   //员工号
    private String  ENAME;   //员工名
    private String  JOB;     //工种
    private Integer MGR;     //上级
    private Integer DEPTNO;  //部门号
}
