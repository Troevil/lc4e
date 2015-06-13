package com.teddy.lc4e.core.database.service;

import com.teddy.lc4e.core.database.model.*;
import com.teddy.lc4e.core.database.repository.RolePermissionRepoistory;
import com.teddy.lc4e.core.database.repository.UserRepository;
import com.teddy.lc4e.core.database.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by teddy on 2015/6/12.
 */

public class UserService {



    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RolePermissionRepoistory rolePermissionRepoistory;
    @Autowired
    private UserRepository userRepository;

    public Set<String> findRoles(String username) {
        UserRole userRole = userRoleRepository.findByUserUserName(username);
        Set<String> roles = new HashSet<String>();
        if (userRole != null && userRole.getSysRoles() != null) {
            List<SysRole> allRoles = userRole.getSysRoles();
            for (int i = 0, len = allRoles.size(); i < len; i++) {
                roles.add(allRoles.get(i).getRoleAbbr());
            }
        }
        return roles;
    }

    public Set<String> findPermissions(String username) {
        Set<String> roles = findRoles(username);
        return findPermissions(roles);
    }

    public Set<String> findPermissions(Set<String> roles) {
        SysRolePermission rolePermission = rolePermissionRepoistory.findBySysRoleRoleAbbrIn(roles);
        Set<String> permissions = new HashSet<String>();
        if (rolePermission != null && rolePermission.getSysPermissions() != null) {
            List<SysPermission> allPermissions = rolePermission.getSysPermissions();
            for (int i = 0, len = allPermissions.size(); i < len; i++) {
                permissions.add(allPermissions.get(i).getPermissionAbbr());
            }
        }
        return permissions;
    }

    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    public boolean createUser(User user) {
       // userRepository.(user);
        return true;
    }

}
