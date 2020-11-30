package example.demo.entity;


import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/28 15:32
 * @Email: shuixiang.yang@tcl.com
 */

@TableName("app")
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class App implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * 客户端密钥
     */
    private String clientSecret;
    /**
     * 跳转地址
     */
    private String redirectUri;

}
