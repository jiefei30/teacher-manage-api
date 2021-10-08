package com.hist.teachermanage.api.BO;

import com.hist.teachermanage.api.entity.Department;
import com.hist.teachermanage.api.entity.User;
import lombok.Data;

/**
 * @author DELL
 * @date 2020/7/23 15:47
 */
@Data
public class UserDepartmentBO extends User {

    //关联院系实体类
    private Department department;
}
