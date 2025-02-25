package cn.laoazhang.stock.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 侧边栏权限树（不包含按钮权限）实体类
 * @TableName sys_permission
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysPermissionDomain{
    /**
     * 权限ID
     */
    private Long id;

    /**
     * 菜单权限名称
     */
    private String title;

    /**
     * 菜单图标(侧边导航栏图标)
     */
    private String icon;


    /**
     * 访问地址URL
     */
    private String path;

    /**
     * name与前端vue路由name约定一致
     */
    private String name;

    private List<SysPermissionDomain> children;

    /**
     * 按钮权限标识
     */
    private List<String> permissions;

    private int type;
}