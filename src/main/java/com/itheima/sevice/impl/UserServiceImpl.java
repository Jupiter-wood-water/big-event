package com.itheima.sevice.impl;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.sevice.UserService;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    @Override
    public User findByUserName(String username) {
        User user = userMapper.fingByUserName(username);
        return user;
    }

    /**
     * 注册用户
     * @param username
     * @param password
     */
    @Override
    public void register(String username, String password) {
        //加密密码
        String md5String = Md5Util.getMD5String(password);
        //添加
        userMapper.register(username,md5String);
    }

    /**
     * 更新用户信息
     * @param user
     */
    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    /**
     * 修改用户头像
     * @param avatarUrl
     * @return
     */
    @Override
    public void updateAvator(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvator(avatarUrl,id);
    }

    /**
     * 更新用户密码
     * @param newPwd
     * @param id
     */
    @Override
    public void updatePwd(String newPwd, Integer id) {
        userMapper.updatePwd(newPwd,id);
    }
}
