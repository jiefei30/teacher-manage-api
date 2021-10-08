package com.hist.teachermanage.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 教职工用户表
 * </p>
 *
 * @author Wangmingcan
 * @since 2020-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="教职工用户表")
public class User extends Model<User> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "教职工号")
    private String account;

    @ApiModelProperty(value = "性别，0男;1女")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "是否婚配，1是;0不是")
    private Integer marry;

    @ApiModelProperty(value = "微信号")
    private String wechat;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "院系id")
    private Integer depId;

    @ApiModelProperty(value = "0未审批；1普通教职工；2管理员；3超级管理员")
    private Integer type;

    @ApiModelProperty(value = "0正常；1删除")
    @TableLogic
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
