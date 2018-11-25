package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();


    List<User> findUserByName(String username);

    int findTotal();

    List<User> findUserByVo(QueryVo queryVo);

    /**
     * user为查询可能传入的条件
     * @param user
     * @return
     */
    List<User> findUserByCondition(User user);

    List<User> findUserByIds(QueryVo queryVo);
}
