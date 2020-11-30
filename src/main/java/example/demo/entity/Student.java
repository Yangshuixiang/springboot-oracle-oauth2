package example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/26 16:13
 * @Email: shuixiang.yang@tcl.com
 */

@Data
@ToString
@TableName("student")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
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
}
