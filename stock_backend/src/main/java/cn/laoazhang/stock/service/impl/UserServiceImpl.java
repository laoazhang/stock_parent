package cn.laoazhang.stock.service.impl;

import cn.laoazhang.stock.mapper.SysUserMapper;
import cn.laoazhang.stock.pojo.entity.SysUser;
import cn.laoazhang.stock.service.UserService;
import cn.laoazhang.stock.vo.req.LoginReqVo;
import cn.laoazhang.stock.vo.resp.LoginRespVo;
import cn.laoazhang.stock.vo.resp.R;
import cn.laoazhang.stock.vo.resp.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : laoazhang
 * @date : 2024/12/24 22:47
 * @description : 定义服务接口实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    public R<LoginRespVo> login(LoginReqVo vo) {
        if (vo == null || StringUtils.isBlank(vo.getUsername()) || StringUtils.isBlank((vo.getPassword()))) {
            return R.error(ResponseCode.DATA_ERROR.getMessage());
        }
        //根据用户名查询用户信息
        SysUser user = sysUserMapper.getUserByUserName(vo.getUsername());
        //判断用户是否存在，存在则密码校验比对
        if (user == null || passwordEncoder.matches(vo.getPassword(), user.getPassword())) {
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }
        //组装登录成功数据
        LoginRespVo respVo = new LoginRespVo();
        //属性名称与类型必须相同，否则属性值无法copy
        BeanUtils.copyProperties(user, respVo);
        return R.ok(respVo);
    }
}
