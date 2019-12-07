package com.cyb.blogserver.config;

import com.cyb.authority.domain.CybAuthorityUser;
import com.cyb.authority.service.CybAuthorityUserService;
import com.cyb.blogserver.dao.UserMapper;
import com.cyb.blogserver.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.*;

@Component("cybAuthorityUserService")
public class CybAuthorityUserServiceImpl implements CybAuthorityUserService {

    private static final Logger logger = LoggerFactory.getLogger(CybAuthorityUserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Override
    public Set<String> queryRolesByName(String userName){
        logger.info("start queryRolesByName");
        Set<String> resultSet = new HashSet<String>();
        return userMapper.queryRolesByName(userName);
    }

    @Override
    public Set<String> queryPermissionByName(String userName){
        logger.info("start queryPermissionByName");
        Set<String> resultSet = new HashSet<String>();
        return userMapper.queryPermissionByName(userName);
    }

    @Override
    public List<CybAuthorityUser> selectByName(String name){
        logger.info("start selectByName:\t" + name);
        List<CybAuthorityUser> resultList = new ArrayList<CybAuthorityUser>();
        List<User> list = userMapper.selectByUserName(name);
        if(null != list && list.size() > 0){
            for(User user : list){
                CybAuthorityUser cybAuthorityUser = new CybAuthorityUser();
                cybAuthorityUser.setName(user.getUserName());
                cybAuthorityUser.setPassword(user.getPassword());
                resultList.add(cybAuthorityUser);
            }
        }
        return resultList;
    }
}
