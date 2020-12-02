package example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/12/1 10:54
 * @Email: shuixiang.yang@tcl.com
 */

//@ToString
//@Data
@TableName("t_class")
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
public class TClass {

    private Integer classID;
    private String className;
    private String teacherMuster;
    private String mark;

    @Override
    public String toString() {
        return "TClass{" +
                "classID=" + classID +
                ", className='" + className + '\'' +
                ", teacherMuster='" + teacherMuster + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherMuster() {
        return teacherMuster;
    }

    public void setTeacherMuster(String teacherMuster) {
        this.teacherMuster = teacherMuster;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
