package example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/12/1 10:54
 * @Email: shuixiang.yang@tcl.com
 */

@ToString
@Data
@TableName("t_class")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TClass {

    private Integer classID;
    private String className;
    private String teacherMuster;
    private String mark;
}
