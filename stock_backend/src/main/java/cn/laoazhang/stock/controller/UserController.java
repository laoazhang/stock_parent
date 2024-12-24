package cn.laoazhang.stock.controller;

import cn.laoazhang.stock.pojo.entity.SysUser;
import cn.laoazhang.stock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/user/{userName}")
    public SysUser getUserService(@PathVariable("userName") String userName) {
        return userService.getUserByUserName(userName);
    }
}
