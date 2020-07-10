package com.estate.controller.admin;

import com.estate.constant.SystemConstant;
import com.estate.dto.UserDTO;
import com.estate.service.IRoleService;
import com.estate.service.IUserService;
import com.estate.utils.DisplayTagUtils;
import com.estate.utils.MessageUtil;
import com.estate.utils.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/admin/user/list", method = RequestMethod.GET)
    public ModelAndView getUsers(@ModelAttribute(SystemConstant.MODEL) UserDTO model,
                                 HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/list");
        DisplayTagUtils.initSearchBean(request, model);
        Pageable pageable = new PageRequest(model.getPage() - 1, model.getMaxPageItems());
        List<UserDTO> users = userService.getUsers(model.getSearchValue(), pageable);
        model.setListResult(users);
        model.setTotalItems(userService.getTotalItems(model.getSearchValue()));
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }


    @RequestMapping(value = "/admin/user/edit", method = RequestMethod.GET)
    public ModelAndView getUserById(@RequestParam(value = "id", required = false) String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/edit");
        if (id != null) {
            initMessageResponse(mav, request);
            mav.addObject(SystemConstant.MODEL, userService.findUserById(Long.valueOf(id)));
        } else {
            UserDTO users = new UserDTO();
            users.setRoles(roleService.getRoles());
            mav.addObject(SystemConstant.MODEL, users);
        }
        return mav;
    }

    @RequestMapping(value = "/admin/user/update", method = RequestMethod.GET)
    public ModelAndView getUserByIdUpdate(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/edit");
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL, userService.findUserById(Long.valueOf(SecurityUtils.getPrincipal().getId())));

        return mav;
    }

    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = MessageUtil.getMessageResponse(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }

}
