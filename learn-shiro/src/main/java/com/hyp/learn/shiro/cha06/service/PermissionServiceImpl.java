package com.hyp.learn.shiro.cha06.service;


import com.hyp.learn.shiro.cha06.dao.PermissionDao;
import com.hyp.learn.shiro.cha06.dao.PermissionDaoImpl;
import com.hyp.learn.shiro.cha06.entity.Permission;

public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
