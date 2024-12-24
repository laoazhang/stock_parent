package cn.laoazhang.stock.service.impl;

import cn.laoazhang.stock.mapper.SysUserMapper;
import cn.laoazhang.stock.pojo.entity.SysUser;
import cn.laoazhang.stock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : laoazhang
 * @date : 2024/12/24 22:47
 * @description :
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 根据用户名称查询用户信息
     * @param userName 用户名称
     * @return
     */
    @Override
    public SysUser getUserByUserName(String userName) {
        SysUser user = sysUserMapper.getUserByUserName(userName);
        return user;
    }
}
