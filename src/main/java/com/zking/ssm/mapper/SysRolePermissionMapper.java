package com.zking.ssm.mapper;

import com.zking.ssm.model.SysRolePermission;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRolePermissionMapper {
    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);
}