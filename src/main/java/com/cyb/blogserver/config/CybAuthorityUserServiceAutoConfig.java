package com.cyb.blogserver.config;

import com.cyb.authority.service.CybAuthorityUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenyingbo
 */
@Configuration
public class CybAuthorityUserServiceAutoConfig {

    @Bean
    public CybAuthorityUserService userService(){
        CybAuthorityUserService userService = new CybAuthorityUserServiceImpl();
        System.out.println("cyb.authority.cybAuthorityUserService init success!");
        return userService;
    }
}
