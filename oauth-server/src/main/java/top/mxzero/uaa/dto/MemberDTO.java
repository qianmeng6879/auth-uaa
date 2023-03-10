package top.mxzero.uaa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Zero
 * @email qianmeng6879@163.com
 * @since 2023/3/5
 */
@Data
public class MemberDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String avatar;
    private String realName;
    private Date createTime;
    private Date updateTime;
}
