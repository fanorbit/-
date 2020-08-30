package com.leyou.user.api;

import com.leyou.user.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping
public interface UserApi {

    /**
     * 根据用户名和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/query")
    User queryUser(@RequestParam(value = "username") String username,
                   @RequestParam(value = "password") String password);
}
