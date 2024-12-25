package cn.laoazhang.stock.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.laoazhang.stock.constant.StockConstant;
import cn.laoazhang.stock.mapper.SysUserMapper;
import cn.laoazhang.stock.pojo.entity.SysUser;
import cn.laoazhang.stock.service.UserService;
import cn.laoazhang.stock.utils.IdWorker;
import cn.laoazhang.stock.vo.req.LoginReqVo;
import cn.laoazhang.stock.vo.resp.LoginRespVo;
import cn.laoazhang.stock.vo.resp.R;
import cn.laoazhang.stock.vo.resp.ResponseCode;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author : laoazhang
 * @date : 2024/12/24 22:47
 * @description : 定义服务接口实现
 */
@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IdWorker idWorker;

    /**
     * 根据用户名称查询用户信息
     *
     * @param userName 用户名称
     * @return
     */
    @Override
    public SysUser getUserByUserName(String userName) {
        SysUser user = sysUserMapper.getUserByUserName(userName);
        return user;
    }

    /**
     * 用户登录功能
     * @param vo
     * @return
     */
    @Override
    public R<LoginRespVo> login(LoginReqVo vo) {
        //1.校验参数的合法性
        if (vo == null || StringUtils.isBlank(vo.getUsername()) || StringUtils.isBlank((vo.getPassword()))) {
            return R.error(ResponseCode.DATA_ERROR.getMessage());
        }
        //2.校验验证码和sessionId是否有效
        if (StringUtils.isBlank(vo.getCode()) || StringUtils.isBlank(vo.getSessionId())) {
            return R.error(ResponseCode.DATA_ERROR.getMessage());
        }
        //3.根据Rkey从redis中获取缓存的校验码
        String rCode = (String) redisTemplate.opsForValue().get(StockConstant.CHECK_PREFIX + vo.getSessionId());
        //判断获取的验证码是否存在，以及是否与输入的验证码相同
        if (StringUtils.isBlank(rCode) || !rCode.equalsIgnoreCase(vo.getCode())) {
            //验证码输入有误
            return R.error(ResponseCode.CHECK_CODE_ERROR);
        }
        //4.根据账户名称去数据库查询获取用户信息
        SysUser user = sysUserMapper.getUserByUserName(vo.getUsername());
        //5.判断用户是否存在，存在则密码校验比对
        if (user == null || !passwordEncoder.matches(vo.getPassword(), user.getPassword())) {
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }
        //6.组装登录成功数据
        LoginRespVo respVo = new LoginRespVo();
        //属性名称与类型必须相同，否则属性值无法copy
        BeanUtils.copyProperties(user, respVo);
        return R.ok(respVo);
    }


    /**
     * 获取验证码
     * @return
     */
    @Override
    public R<Map> getCaptchaCode() {
        //参数分别是宽、高、验证码长度、干扰线数量
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(250, 40, 4, 5);
        //设置背景颜色清灰
        captcha.setBackground(Color.lightGray);
        //自定义校验码生成方式
//        captcha.setGenerator(new CodeGenerator() {
//            @Override
//            public String generate() {
//                return RandomStringUtils.randomNumeric(4);
//            }
//            @Override
//            public boolean verify(String code, String userInputCode) {
//                return code.equalsIgnoreCase(userInputCode);
//            }
//        });
        //获取图片中的验证码，默认生成的校验码包含文字和数字，长度为4
        String checkCode = captcha.getCode();
        log.info("生成校验码:{}", checkCode);
        //生成sessionId
        String sessionId = String.valueOf(idWorker.nextId());
        //将sessionId和校验码保存在redis下，并设置缓存中数据存活时间一分钟
        redisTemplate.opsForValue().set(StockConstant.CHECK_PREFIX + sessionId, checkCode, 1, TimeUnit.MINUTES);
        //组装响应数据
        HashMap<String, String> info = new HashMap<>();
        info.put("sessionId", sessionId);
        info.put("imageData", captcha.getImageBase64());//获取base64格式的图片数据
        //设置响应数据格式
        return R.ok(info);
    }
}
