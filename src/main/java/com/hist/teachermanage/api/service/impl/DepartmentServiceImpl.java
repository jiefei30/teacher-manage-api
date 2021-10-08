package com.hist.teachermanage.api.service.impl;

import com.hist.teachermanage.api.entity.Department;
import com.hist.teachermanage.api.mapper.DepartmentMapper;
import com.hist.teachermanage.api.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 院系表 服务实现类
 * </p>
 *
 * @author yangluxin
 * @since 2020-07-22
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
