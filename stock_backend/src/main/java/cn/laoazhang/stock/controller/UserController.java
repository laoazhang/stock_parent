package cn.laoazhang.stock.controller;

import cn.laoazhang.stock.pojo.entity.SysUser;
import cn.laoazhang.stock.service.UserService;
import cn.laoazhang.stock.vo.req.LoginReqVo;
import cn.laoazhang.stock.vo.resp.LoginRespVo;
import cn.laoazhang.stock.vo.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : laoazhang
 * @date : 2024/12/24 22:50
 * @description :
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    @GetMapping("/{userName}")
    public SysUser getUserService(@PathVariable("userName") String userName) {
        return userService.getUserByUserName(userName);
    }

    /**
     * 用户登录功能实现
     *
     * @param vo
     * @return
     */
    @PostMapping("/login")
    public R<LoginRespVo> login(@RequestBody LoginReqVo vo) {
        R<LoginRespVo> r = userService.login(vo);
        return r;
    }

    /**
     * 生成登录校验码的访问接口
     * @return
     */
    @GetMapping("/captcha")
    public R<Map> getCaptchaCode() {
        return userService.getCaptchaCode();
    }
}
