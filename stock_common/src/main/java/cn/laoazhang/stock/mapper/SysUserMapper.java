package cn.laoazhang.stock.mapper;

import cn.laoazhang.stock.pojo.domain.SysPermissionDomain;
import cn.laoazhang.stock.pojo.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
* @author laoazhang
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2024-12-24 22:24:22
* @Entity cn.laoazhang.stock.pojo.entity.SysUser
*/
public interface SysUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    SysUser getUserByUserName(@Param("userName") String userName);

    /**
     * 根据用户id查询用户权限
     * @param userId
     * @return
     */
    List<SysPermissionDomain> getUserPermission(@Param("userId") Long userId);
}
