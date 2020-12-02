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

//@Accessors(chain = true)
//@Data
//@ToString
//@EqualsAndHashCode(callSuper = false)
public class EmployeeDTO {

    private Integer EMPNO;   //员工号
    private String  ENAME;   //员工名
    private String  JOB;     //工种
    private Integer MGR;     //上级
    private Integer DEPTNO;  //部门号


    public Integer getEMPNO() {
        return EMPNO;
    }

    public void setEMPNO(Integer EMPNO) {
        this.EMPNO = EMPNO;
    }

    public String getENAME() {
        return ENAME;
    }

    public void setENAME(String ENAME) {
        this.ENAME = ENAME;
    }

    public String getJOB() {
        return JOB;
    }

    public void setJOB(String JOB) {
        this.JOB = JOB;
    }

    public Integer getMGR() {
        return MGR;
    }

    public void setMGR(Integer MGR) {
        this.MGR = MGR;
    }

    public Integer getDEPTNO() {
        return DEPTNO;
    }

    public void setDEPTNO(Integer DEPTNO) {
        this.DEPTNO = DEPTNO;
    }
}
