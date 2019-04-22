package cn.sanleny.userservice.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: sanleny
 * @Date: 2019-04-21
 * @Description: cn.sanleny.userservice.util
 * @Version: 1.0
 */
public class BPwdEncoderUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String  BCryptPassword(String password){
        return encoder.encode(password);
    }

    public static boolean matches(CharSequence rawPassword, String encodedPassword){
        return encoder.matches(rawPassword,encodedPassword);
    }

    public static void main(String args[]){
        System.out.println(BPwdEncoderUtil.BCryptPassword("123456"));
        System.out.println(BPwdEncoderUtil.matches("123456","$2a$10$w.YkaN9iEVo58kSBSdu2JOUt82DM1/S6jFP/9rW8eCxjCapPiEpI6"));

    }
}
