package com.hyp.learn.shiro.cha06.dao;


import com.hyp.learn.shiro.cha06.entity.Role;

public interface RoleDao {

    Role createRole(Role role);

    void deleteRole(Long roleId);

    void correlationPermissions(Long roleId, Long... permissionIds);

    void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
