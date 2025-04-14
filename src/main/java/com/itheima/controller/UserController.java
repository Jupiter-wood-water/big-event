package com.itheima.controller;

import ch.qos.logback.core.util.StringUtil;
import com.auth0.jwt.JWT;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.sevice.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{3,16}$") String username, @Pattern(regexp = "^\\S{3,16}$")String password){
        //查询用户
        User u = userService.findByUserName(username);
        if ( u == null){
            //没有占用，进行注册
            userService.register(username,password);
            return Result.success();
        }else{
            //被占用
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{3,16}$") String username, @Pattern(regexp = "^\\S{3,16}$")String password){
        //根据用户名查询用户
        User user = userService.findByUserName(username);
        if (user == null){
            //判断用户是否存在
            return Result.error("用户名错误");
        }
        //判断密码是否正确 user对象中的password是密文
        if (Md5Util.getMD5String(password).equals(user.getPassword())){
            //登陆成功
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",user.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }else {
            //登录成功
            return Result.error("密码错误");
        }
    }

    @GetMapping("/userInfo")
    public Result<User> getUserInfo(){
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    /**
     * 更新用户信息
     * @param user
     */
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    /**
     * 修改用户头像
     * @param avatarUrl
     * @return
     */
    @PatchMapping("/updateAvatar")
    public Result updataAvator(@RequestParam @URL String avatarUrl){
        userService.updateAvator(avatarUrl);
        return Result.success();
    }

    /**
     * 更新用户密码
     * @param params
     * @return
     */
    @PatchMapping("/updatePwd")
    public Result updataPwd(@RequestBody Map<String,String> params){
        //1.校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }

        //原密码是否正确
        //调用userService根据用户名拿到原密码，再和oldpwd比对
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        if (!user.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码错误");
        }

        //newPwd和rePwd是否一样
        if (!newPwd.equals(rePwd)){
            return Result.error("两次填写的密码不一致");
        }

        //2.调用service完成密码更新
        userService.updatePwd(Md5Util.getMD5String(newPwd), (Integer) map.get("id"));

        return Result.success();
    }


}
