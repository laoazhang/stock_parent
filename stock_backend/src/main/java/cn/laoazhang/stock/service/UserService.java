package cn.laoazhang.stock.service;

import cn.laoazhang.stock.pojo.entity.SysUser;
import cn.laoazhang.stock.vo.req.LoginReqVo;
import cn.laoazhang.stock.vo.resp.LoginRespVo;
import cn.laoazhang.stock.vo.resp.R;

import java.util.Map;

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

    /**
     * 用户登录功能实现
     * @param vo
     * @return
     */
    R<LoginRespVo> login(LoginReqVo vo);

    /**
     * 登录校验码生成服务方法
     * @return
     */
    R<Map> getCaptchaCode();
}
