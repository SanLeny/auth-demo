package cn.sanleny.userservice.controller;

import cn.sanleny.userservice.entity.JWT;
import cn.sanleny.userservice.feign.AuthServiceClient;
import cn.sanleny.userservice.util.BPwdEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @Author: sanleny
 * @Date: 2019-04-21
 * @Description: cn.sanleny.userservice.controller
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthServiceClient authServiceClient;


    @PostMapping("/login")
    public JWT login(@RequestParam("username") String username , @RequestParam("password") String password){
//        password = BPwdEncoderUtil.BCryptPassword(password);
        return authServiceClient.getToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==","password",username,password);

    }


    @GetMapping(value = "/foo")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getFoo() {
        return "i'm foo, " + UUID.randomUUID().toString();
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }


    @PreAuthorize("hasAnyAuthority('atest:atest:atest')")
    @GetMapping("/atest")
    public String atest(){
        return "atest";
    }

    @PreAuthorize("hasAnyAuthority('list:list:list')")
    @GetMapping("/btest")
    public String btest(){
        return "btest";
    }

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/ctest")
    public String ctest(){
        return "ctest";
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/dtest")
    public String dtest(){
        return "dtest";
    }

}
