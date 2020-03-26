package com.hyp.learn.shiro.cha06.service;


import com.hyp.learn.shiro.cha06.entity.Permission;

public interface PermissionService {
    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);
}
