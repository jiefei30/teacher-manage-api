package com.hist.teachermanage.api.controller;


import com.hist.teachermanage.api.entity.Department;
import com.hist.teachermanage.api.service.IDepartmentService;
import com.hist.teachermanage.vo.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 院系表 前端控制器
 * </p>
 *
 * @author yangluxin
 * @since 2020-07-22
 */
@Transactional
@RestController
@Api(tags = "院系：院系管理")
@RequestMapping("/api/department")
public class DepartmentController {

    private final static Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private IDepartmentService departmentService;

    @GetMapping("/getAll")
    @ApiOperation("获取全部院系")
    public AjaxResponse getAll(){
        logger.info("获取全部院系");
        AjaxResponse ajaxResponse = AjaxResponse.newSuccess();
        ajaxResponse.setData(departmentService.list());
        return ajaxResponse;
    }
}

