package cn.laoazhang.stock.controller;

import cn.laoazhang.stock.pojo.entity.SysUser;
import cn.laoazhang.stock.service.UserService;
import cn.laoazhang.stock.vo.req.LoginReqVo;
import cn.laoazhang.stock.vo.resp.LoginRespVo;
import cn.laoazhang.stock.vo.resp.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "用户认证相关功能接口定义",tags = "用户功能 - 用户登录功能")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    @GetMapping("/{userName}")
    @ApiOperation(value = "根据用户名查询用户信息",notes = "用户信息查询",response = SysUser.class)
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
    @ApiOperation(value = "用户登录功能",notes = "用户登录",response = R.class)
    public R<LoginRespVo> login(@RequestBody LoginReqVo vo) {
        R<LoginRespVo> r = userService.login(vo);
        return r;
    }

    /**
     * 生成登录校验码的访问接口
     * @return
     */
    @GetMapping("/captcha")
    @ApiOperation(value = "验证码生成功能",response = R.class)
    public R<Map> getCaptchaCode() {
        return userService.getCaptchaCode();
    }
}
