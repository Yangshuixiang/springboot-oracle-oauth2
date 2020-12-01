package example.demo.controller.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/26 16:52
 * @Email: shuixiang.yang@tcl.com
 */

@Accessors(chain = true)
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class StudentDTO {

    private String userId;
    private String name;
    private String nickName;
    private Integer classId;
    private String mark;
    private String guardian;
    private int offset;
    private int limit;

}
