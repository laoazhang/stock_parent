package cn.laoazhang.stock.service;

import cn.laoazhang.stock.pojo.entity.SysUser;

/**
 * @author : laoazhang
 * @date : 2024/12/24 22:43
 * @description : 定义操纵用户的服务接口
 */
public interface UserService {

    /**
     * 根据用户查询用户信息
     * @param userName 用户名称
     * @return
     */
    SysUser getUserByUserName(String userName);
}
