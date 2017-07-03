package com.loeo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.loeo.entity.SysUser;
import com.loeo.exception.DuplicateUsernameException;
import com.loeo.mapper.SysUserMapper;
import com.loeo.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public SysUser findByUserName(String username) {
        List<SysUser> sysUserList = selectByMap(new HashMap<String, Object>() {
            {put("username", username);}
        });
        if (sysUserList != null && sysUserList.size()>0) {
            if (sysUserList.size() == 1) {
                return sysUserList.get(0);
            } else if (sysUserList.size() > 1) {
                throw new DuplicateUsernameException();
            }
        }
        return null;
    }
}
