package cn.sanleny.userservice.entity;

import lombok.Data;

/**
 * @Author: sanleny
 * @Date: 2019-04-21
 * @Description: cn.sanleny.userservice.entity
 * @Version: 1.0
 */
@Data
public class JWT {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private String jti;

}
