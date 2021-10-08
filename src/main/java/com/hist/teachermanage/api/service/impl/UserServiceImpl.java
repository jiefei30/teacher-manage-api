package com.hist.teachermanage.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hist.teachermanage.api.BO.UserDepartmentBO;
import com.hist.teachermanage.api.entity.User;
import com.hist.teachermanage.api.mapper.UserMapper;
import com.hist.teachermanage.api.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 教职工用户表 服务实现类
 * </p>
 *
 * @author Wangmingcan
 * @since 2020-07-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public IPage<UserDepartmentBO> selectVo(Page page, QueryWrapper<UserDepartmentBO> queryWrapper) {
        return userMapper.selectVo(page,queryWrapper);
    }
}
