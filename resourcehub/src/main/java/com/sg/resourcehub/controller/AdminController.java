/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.controller;

import com.sg.resourcehub.models.Role;
import com.sg.resourcehub.models.User;
import com.sg.resourcehub.service.responses.CreateUserResponse;
import com.sg.resourcehub.service.responses.DeleteUserResponse;
import com.sg.resourcehub.service.responses.DisplayRolesResponse;
import com.sg.resourcehub.service.responses.DisplayUsersResponse;
import com.sg.resourcehub.service.responses.GetRoleByIdResponse;
import com.sg.resourcehub.service.responses.GetUserByIdResponse;
import com.sg.resourcehub.service.ResourceService;
import com.sg.resourcehub.service.responses.UpdateUserResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Software Guld
 */
@Controller
public class AdminController {

    @Autowired
    ResourceService service;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/admin")
    public String homePage(Model model) {
        DisplayUsersResponse response = service.getAllUsers();
        DisplayRolesResponse rolesResponse = service.getRolesByName();
        model.addAttribute("users", response.getAllUsers());
        model.addAttribute("roles", rolesResponse.getAllRoles());
        return "Admin";
    }

    @PostMapping("/addUser")
    public String addUser(String[] roleIdList, String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setEnabled(true);

        Set<Role> userRoles = new HashSet<>();
        //look at how users get edited and add them similarly for below
        //currently we have a ROLE_SUPPLIER and ROLE_REQUESTER not role user
        // so we need to find out which type they are since we don't have a ROLE_USER
        for (String roleId : roleIdList) {
            GetRoleByIdResponse response = service.getRoleById(Integer.parseInt(roleId));
            userRoles.add(response.getSingleRole());
        }
        user.setRoles(userRoles);

        CreateUserResponse userResponse = service.createUser(user);
        userResponse.setSingleUser(user);

        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(Integer id) {
        DeleteUserResponse response = service.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/editUser")
    public String editUserDisplay(Model model, Integer id, Integer error) {
        GetUserByIdResponse response = service.getUserById(id);
        User user = response.getSingleUser();
        DisplayRolesResponse roleResponse = service.getRolesByName();
        List<Role> roleList = roleResponse.getAllRoles();

        model.addAttribute("user", user);
        model.addAttribute("roles", roleList);

        if (error != null) {
            if (error == 1) {
                model.addAttribute("error", "Passwords did not match, password was not updated.");
            }
        }

        return "editUser";
    }

    @PostMapping(value = "/editUser")
    public String editUserAction(String[] roleIdList, Boolean enabled, Integer id) {
        GetUserByIdResponse response = service.getUserById(id);
        User user = response.getSingleUser();
        if (enabled != null) {
            user.setEnabled(enabled);
        } else {
            user.setEnabled(false);
            
        }

        Set<Role> roleList = new HashSet<>();
        for (String roleId : roleIdList) {
            GetRoleByIdResponse roleResponse = service.getRoleById(Integer.parseInt(roleId));
            Role role = roleResponse.getSingleRole();
//            Role role = roles.getRoleById(Integer.parseInt(roleId));
            roleList.add(role);
        }
        user.setRoles(roleList);
//        CreateUserResponse userResponse = service.createUser(user);
//        userResponse.setSingleUser(user);
        UpdateUserResponse updateResponse = service.updateUser(user);
        updateResponse.setUpdatedUser(user);

        return "redirect:/admin";
    }

    @PostMapping("editPassword")
    public String editPassword(Integer id, String password, String confirmPassword) {
        GetUserByIdResponse response = service.getUserById(id);
        User user = response.getSingleUser();
        if (password.equals(confirmPassword)) {
            user.setPassword(encoder.encode(password));
            UpdateUserResponse updateResponse = service.updateUser(user);
            updateResponse.setUpdatedUser(user);
            return "redirect:/admin";
        } else {
            return "redirect:/editUser?id=" + id + "&error=1";
        }
    }
}
