package com.estate.controller.admin;

import com.estate.constant.SystemConstant;
import com.estate.dto.CustomerDTO;
import com.estate.service.impl.CustomerService;
import com.estate.utils.DisplayTagUtils;
import com.estate.utils.MessageUtil;
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
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/customer/list", method = RequestMethod.GET)
    public ModelAndView homePage(@ModelAttribute(SystemConstant.MODEL)CustomerDTO model,
                                 HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        DisplayTagUtils.initSearchBean(request,model);
        List<CustomerDTO> customerDTOS = customerService.searchCustomersAssignment(model);
        model.setListResult(customerDTOS);
        model.setTotalItems(customerService.getTotalItemsAssignment(model));
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    @RequestMapping(value = "/admin/customer/edit",method = RequestMethod.GET)
    public ModelAndView updatOrAddCustomer(@RequestParam(value = "id", required = false) String id,
                                           HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        CustomerDTO customerDTO = new CustomerDTO();
        if(id != null){
            customerDTO = customerService.findOneById(Long.parseLong(id));
        }
        mav.addObject(SystemConstant.MODEL, customerDTO);
        return mav;
    }

    @RequestMapping(value = "/admin/customer/detail/{id}", method = RequestMethod.GET)
    public ModelAndView getCustomerDetailById(@PathVariable("id") long id,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/detail");
        mav.addObject(SystemConstant.MODEL, customerService.findCustomerById(id));
        initMessageResponse(mav,request);
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
