package com.hist.teachermanage.api.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hist.teachermanage.api.BO.UserDepartmentBO;
import com.hist.teachermanage.api.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 教职工用户表 服务类
 * </p>
 *
 * @author Wangmingcan
 * @since 2020-07-22
 */
public interface IUserService extends IService<User> {

    IPage<UserDepartmentBO> selectVo(Page page, @Param(Constants.WRAPPER) QueryWrapper<UserDepartmentBO> queryWrapper);
}
