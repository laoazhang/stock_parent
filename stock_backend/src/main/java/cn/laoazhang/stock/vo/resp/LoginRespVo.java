package cn.laoazhang.stock.vo.resp;

import cn.laoazhang.stock.pojo.domain.SysPermissionDomain;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

/**
 * @author laoazhang
 * @Date 2024/12/25
 * @Description 登录后响应前端的vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRespVo {
    /**
     * 用户ID
     * 将Long类型数字进行json格式转化时，转成String格式类型
     */
    @JsonSerialize(using = ToStringSerializer.class)
    /**
     * 电话
     */
    private String phone;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别
     */
    private int sex;

    /**
     * 状态
     */
    private int status;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 侧边栏权限树（不包含按钮权限）
     */
    private List<SysPermissionDomain> menus;

}