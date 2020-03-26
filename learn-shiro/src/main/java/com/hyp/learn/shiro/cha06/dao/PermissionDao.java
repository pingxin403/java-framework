package com.hyp.learn.shiro.cha06.dao;


import com.hyp.learn.shiro.cha06.entity.Permission;

public interface PermissionDao {

    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);

}
