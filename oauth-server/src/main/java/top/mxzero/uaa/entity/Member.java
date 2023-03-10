package top.mxzero.uaa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * @author Zero
 * @email qianmeng6879@163.com
 * @since 2023/3/5
 */
@Data
public class Member {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String avatar;
    private String realName;
    private Date createTime;
    private Date updateTime;
    @TableLogic
    private Boolean deleted;
}
