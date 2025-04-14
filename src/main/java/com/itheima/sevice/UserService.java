package com.itheima.sevice;

import com.itheima.pojo.User;

public interface UserService {
    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 注册用户
     * @param username
     * @param password
     */
    void register(String username, String password);

    /**
     * 更新用户信息
     * @param user
     */
    void update(User user);

    /**
     * 修改用户头像
     * @param avatarUrl
     * @return
     */
    void updateAvator(String avatarUrl);

    /**
     * 更新用户密码
     * @param newPwd
     * @param id
     */
    void updatePwd(String newPwd, Integer id);
}
