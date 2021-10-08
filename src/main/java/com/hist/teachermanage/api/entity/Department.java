package com.hist.teachermanage.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 院系表
 * </p>
 *
 * @author yangluxin
 * @since 2020-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Department对象", description="院系表")
public class Department extends Model<Department> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    @ApiModelProperty(value = "0正常 1删除")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
