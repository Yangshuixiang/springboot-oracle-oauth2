package example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/26 16:13
 * @Email: shuixiang.yang@tcl.com
 */

//@Data
//@ToString
@TableName("student")
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.INPUT)
    private String userId;
    private String name;
    private String nickName;
    private Integer classId;
    private String mark;
    private String guardian;

    public Student() {
    }

    public Student(String name, String nickName, String mark, String guardian) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.nickName = nickName;
        this.classId = new Random().nextInt(9) + 1;
        this.mark = mark;
        this.guardian = guardian;
    }

    @Override
    public String toString() {
        return "Student{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", classId=" + classId +
                ", mark='" + mark + '\'' +
                ", guardian='" + guardian + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }
}
