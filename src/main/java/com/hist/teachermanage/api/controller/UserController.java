package com.hist.teachermanage.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hist.teachermanage.api.BO.UserDepartmentBO;
import com.hist.teachermanage.api.entity.User;
import com.hist.teachermanage.api.service.IDepartmentService;
import com.hist.teachermanage.api.service.IUserService;
import com.hist.teachermanage.common.util.StringUtils;
import com.hist.teachermanage.vo.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * <p>
 * 教职工用户表 前端控制器
 * </p>
 *
 * @author Wangmingcan
 * @since 2020-07-22
 */
@Transactional
@RestController
@Api(tags = "职工：职工管理")
@RequestMapping("/api/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    /**
     * @author Wangmingcan
     * @date 2020-07-23 09:46
     * @param
     * @return com.hist.teachermanage.vo.AjaxResponse
     * @description 用于测试接口
     */
    @GetMapping("/hello")
    @ApiOperation("测试")
    public AjaxResponse hello(){
        logger.info("hello");
        AjaxResponse ajaxResponse = AjaxResponse.newSuccess();
        ajaxResponse.setData("hello");
        return ajaxResponse;
    }

    /**
     * @author Wangmingcan
     * @date 2020-07-23 10:14
     * @param id 用户id
     * @return com.hist.teachermanage.vo.AjaxResponse
     * @description 设置某用户为管理员
     */
    @PostMapping("/setAdmin")
    @ApiOperation("设置某用户为管理员")
    public AjaxResponse setAdmin(@ApiParam(value = "用户id", required = true)
                                     @RequestParam(value = "id", defaultValue = "0") int id){
        logger.info("设置某用户为管理员");
        AjaxResponse ajaxResponse = AjaxResponse.newSuccess();
        User user = new User();
        user.setId(id).setType(2).setUpdateTime(new Date());
        boolean res = user.insertOrUpdate();
        if (!res) {
            logger.info("设置管理员失败");
            return AjaxResponse.newInstance(500,"设置管理员失败");
        }
        return ajaxResponse;
    }

    /**
     * @author Wangmingcan
     * @date 2020-07-23 10:20
     * @param id
     * @return com.hist.teachermanage.vo.AjaxResponse
     * @description 取消某用户为管理员
     */
    @PostMapping("/unSetAdmin")
    @ApiOperation("取消某用户为管理员")
    public AjaxResponse unSetAdmin(@ApiParam(value = "用户id", required = true)
                                 @RequestParam(value = "id", defaultValue = "0") int id){
        logger.info("取消某用户为管理员");
        AjaxResponse ajaxResponse = AjaxResponse.newSuccess();
        User user = new User();
        user.setId(id).setType(1).setUpdateTime(new Date());
        boolean res = user.insertOrUpdate();
        if (!res) {
            logger.info("取消管理员失败");
            return AjaxResponse.newInstance(500,"取消管理员失败");
        }
        return ajaxResponse;
    }

    /**
     * @author Wangmingcan
     * @date 2020-07-23 10:27
     * @param id 用户id
     * @return com.hist.teachermanage.vo.AjaxResponse
     * @description 查询某用户是否为管理员
     */
//    @GetMapping("/isAdmin")
//    @ApiOperation("查询某用户是否为管理员")
//    public AjaxResponse isAdmin(@ApiParam(value = "用户id",required = true)
//                                   @RequestParam(value = "id", defaultValue = "0") int id){
//        logger.info("查询某用户是否为管理员");
//        AjaxResponse ajaxResponse = AjaxResponse.newSuccess();
//        User user = userService.getById(id);
//        if (user == null) {
//            logger.info("用户不存在");
//            return AjaxResponse.newInstance(500,"用户不存在");
//        }
//        Boolean res = false;
//        if (user.getType() == 2) {
//            res = true;
//        }
//        ajaxResponse.setData(res);
//        return ajaxResponse;
//    }

    /**
     * @author Wangmingcan
     * @date 2020-07-23 10:58
     * @param user 用户实体类
     * @return com.hist.teachermanage.vo.AjaxResponse
     * @description 添加/修改教职工（添加不传id，不用传时间）
     */
    @PostMapping("/addOrUpdate")
    @ApiOperation("添加/修改教职工（添加不传id，不用传时间）")
    public AjaxResponse addOrUpdate(@RequestBody User user){
        logger.info("添加/修改教职工");
        AjaxResponse ajaxResponse = AjaxResponse.newSuccess();
        if (user.getId() == null || user.getId() == 0) {
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.eq(User::getAccount,user.getAccount());
            if (userService.getOne(userLambdaQueryWrapper) != null)
                return AjaxResponse.newInstance(500,"账号已存在");
            String password = StringUtils.md5Encode(user.getPassword());
            user.setCreateTime(new Date())
                    .setType(0)
                    .setPassword(password);
        } else
            user.setUpdateTime(new Date());
        Boolean res = user.insertOrUpdate();
        if (!res) {
            logger.info("添加/修改用户失败");
            return AjaxResponse.newInstance(500,"添加/修改用户失败");
        }
        return ajaxResponse;
    }

    /**
     * @author Wangmingcan
     * @date 2020-07-23 11:23
     * @param id 用户id
     * @return com.hist.teachermanage.vo.AjaxResponse
     * @description 删除员工
     */
    @PostMapping("/delete")
    @ApiOperation("删除员工")
    public AjaxResponse delete(@ApiParam(value = "用户id",required = true)
                                   @RequestParam(value = "id", defaultValue = "0") int id){
        logger.info("删除员工");
        AjaxResponse ajaxResponse = AjaxResponse.newSuccess();
        boolean res = userService.removeById(id);
        if (!res) {
            logger.info("删除员工失败");
            return AjaxResponse.newInstance(500,"用户不存在");
        }
        return ajaxResponse;
    }

    /**
     * @author Wangmingcan
     * @date 2020-07-23 11:28
     * @param id 用户id
     * @return com.hist.teachermanage.vo.AjaxResponse
     * @description 激活员工
     */
    @PostMapping("/activate")
    @ApiOperation("激活员工")
    public AjaxResponse activate(@ApiParam(value = "用户id",required = true)
                               @RequestParam(value = "id", defaultValue = "0") int id){
        logger.info("激活员工");
        AjaxResponse ajaxResponse = AjaxResponse.newSuccess();
        User user = new User();
        user.setId(id)
                .setUpdateTime(new Date())
                .setType(1);
        boolean res = user.insertOrUpdate();
        if (!res) {
            logger.info("激活员工失败");
            return AjaxResponse.newInstance(500,"激活员工失败");
        }
        return ajaxResponse;
    }

    /**
     * @author Wangmingcan
     * @date 2020-07-23 11:02
     * @param id 用户id
     * @param oldPassword 老密码
     * @param newPassword 新密码
     * @return com.hist.teachermanage.vo.AjaxResponse
     * @description
     */
    @PostMapping("/updatePassword")
    @ApiOperation("修改密码")
    public AjaxResponse updatePassword(@ApiParam(value = "用户id", required = true)
                                           @RequestParam(value = "id", defaultValue = "0") int id,
                                       @ApiParam(value = "老密码", required = true)
                                           @RequestParam(value = "oldPassword", defaultValue = "") String oldPassword,
                                       @ApiParam(value = "新密码", required = true)
                                           @RequestParam(value = "newPassword", defaultValue = "") String newPassword){
        AjaxResponse ajaxResponse = AjaxResponse.newSuccess();
        logger.info("修改密码");
        User user = userService.getById(id);
        if (user == null) {
            logger.info("用户不存在");
            return AjaxResponse.newInstance(500,"用户不存在");
        }
        String md5 = StringUtils.md5Encode(oldPassword);
        if (!md5.equals(user.getPassword())) {
            logger.info("老密码错误");
            return AjaxResponse.newInstance(500,"老密码错误");
        }
        User newUser = new User();
        newUser.setPassword(StringUtils.md5Encode(newPassword))
                .setId(id)
                .setUpdateTime(new Date());
        boolean res = newUser.insertOrUpdate();
        if (!res) {
            logger.info("修改失败");
            return AjaxResponse.newInstance(500,"修改失败");
        }
        return ajaxResponse;
    }

    /**
     * @author Wangmingcan
     * @date 2020-07-23 11:55
     * @param account
     * @param password
     * @param type
     * @param response
     * @return com.hist.teachermanage.vo.AjaxResponse
     * @description 登录
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    public AjaxResponse login(@ApiParam(value = "职工号",required = true)
                                  @RequestParam(value = "account", defaultValue = "") String account,
                              @ApiParam(value = "密码",required = true)
                                @RequestParam(value = "password", defaultValue = "") String password,
                              @ApiParam(value = "身份",required = true)
                                  @RequestParam(value = "type", defaultValue = "0") int type,
                              HttpServletResponse response){
        AjaxResponse ajaxResponse = AjaxResponse.newSuccess();
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAccount,account);
        User user = userService.getOne(lambdaQueryWrapper);
        if (user == null) {
            return AjaxResponse.newInstance(500,"该账号不存在");
        }
        logger.info("登录 : " + user.getAccount());
        if (user.getType() < type) {
            logger.info("登录失败 : " + user.getAccount() + " 权限不足 : " + type);
            return AjaxResponse.newInstance(500,"权限不足");
        }
        if (!user.getPassword().equals(StringUtils.md5Encode(password))) {
            logger.info("登录失败 : " + user.getAccount() + " 密码错误 : " + password);
            return AjaxResponse.newInstance(500,"密码错误");
        }
        user.setType(type);
        ajaxResponse.setData(user);
        return ajaxResponse;
    }

    /**
     * @author Wangmingcan
     * @date 2020-07-23 15:13
     * @param request
     * @return com.hist.teachermanage.vo.AjaxResponse
     * @description
     */
//    @PostMapping("/cookie")
//    @ApiOperation("cookie查询")
//    public AjaxResponse cookie(HttpServletRequest request){
//        logger.info("cookie查询");
//        AjaxResponse ajaxResponse = AjaxResponse.newSuccess();
//        Cookie[] cookies = request.getCookies();
//        if (cookies == null) {
//            logger.info("cookie不存在");
//            return AjaxResponse.newInstance(500,"cookie不存在");
//        }
//        String account = null;
//        String password = null;
//        Integer type = null;
//        for (Cookie c : cookies) {
//            if (c.getName().equals("account")) {
//                account = c.getValue();
//            }else if (c.getName().equals("status")) {
//                type = Integer.valueOf(c.getValue());
//            }else if (c.getName().equals("password")) {
//                password = c.getValue();
//            }
//        }
//        if (account == null || type == null || password == null) {
//            logger.info("cookie缺失");
//            return AjaxResponse.newInstance(500,"cookie缺失");
//        }
//        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(User::getAccount,account);
//        User user = userService.getOne(lambdaQueryWrapper);
//        if (user == null) {
//            logger.info("cookie错误");
//            return AjaxResponse.newInstance(500,"cookie错误");
//        }
//        if (!user.getPassword().equals(password) || user.getType() < type) {
//            logger.info("cookie失效");
//            return AjaxResponse.newInstance(500,"cookie失效");
//        }
//        ajaxResponse.setData(user);
//        return ajaxResponse;
//    }

    /**
     * @author Wangmingcan
     * @date 2020-07-23 18:43
     * @param page
     * @param pageSize
     * @param accout
     * @param name
     * @param type
     * @param depId
     * @return com.hist.teachermanage.vo.AjaxResponse
     * @description
     */
    @GetMapping("/get")
    @ApiOperation("分页条件查询")
    public AjaxResponse get(@ApiParam(value = "当前页")
                                @RequestParam(value = "page", defaultValue = "1") int page,
                            @ApiParam(value = "查询的数量")
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                            @ApiParam(value = "职工号")
                                @RequestParam(value = "accout", defaultValue = "") String accout,
                            @ApiParam(value = "姓名")
                                @RequestParam(value = "name", defaultValue = "") String name,
                            @ApiParam(value = "身份")
                                @RequestParam(value = "type", defaultValue = "-1") int type,
                            @ApiParam(value = "部门id")
                                @RequestParam(value = "depId", defaultValue = "0") int depId){
        AjaxResponse ajaxResponse = AjaxResponse.newSuccess();
        //分页查询，参数1：当前页  默认值为1   参数2：每页显示记录数，默认值为10
        Page<UserDepartmentBO> iPage = new Page<>(page,pageSize);
        QueryWrapper<UserDepartmentBO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u.status",0);
        //加入条件
        if (accout != "") queryWrapper.lambda().like(User::getAccount, accout);
        if (name != "") queryWrapper.like("u.name", name);
        if (type != -1) queryWrapper.lambda().eq(User::getType, type);
        if (depId != 0) queryWrapper.lambda().eq(User::getDepId, depId);

        IPage<UserDepartmentBO> userDepartmentBOIPage = userService.selectVo(iPage, queryWrapper);
        ajaxResponse.putData("total",userDepartmentBOIPage.getTotal());
        ajaxResponse.putData("info",userDepartmentBOIPage.getRecords());
        return ajaxResponse;
    }
}

