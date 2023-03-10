package top.mxzero.uaa.dto;

import lombok.Data;

/**
 * @author Zero
 * @email qianmeng6879@163.com
 * @since 2023/3/6
 */
@Data
public class UserParam {
    private String username;

    private String password;
    private String email;
    private String code;
}
