package com.mmall.dao;

import com.mmall.pojo.Cart;

public interface CartMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}