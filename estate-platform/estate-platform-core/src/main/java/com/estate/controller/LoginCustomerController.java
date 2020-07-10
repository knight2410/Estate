package com.estate.controller;

import com.estate.constant.SystemConstant;
import com.estate.dto.CustomerDTO;
import com.estate.dto.DetailContract;
import com.estate.entity.CustomerEntity;
import com.estate.repository.CustomerRepository;
import com.estate.service.IContractService;
import com.estate.service.ICustomerService;
import com.estate.utils.DisplayTagUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginCustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private IContractService contractService;

    @Autowired
    private ICustomerService customerService;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("login-customer");
        CustomerDTO customerDTO = new CustomerDTO();
        mav.addObject(SystemConstant.MODEL, customerDTO);
        return mav;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String Login(@ModelAttribute(name = "model") CustomerDTO customerDTO, HttpSession session) {
        CustomerEntity customerEntity = customerRepository.findOneByEmailAndPassword(customerDTO.getEmail(), customerDTO.getPassword());
        if (customerEntity != null) {
            session.setAttribute("nameCustomer", customerEntity.getName());
            session.setAttribute("id", customerEntity.getId());
            return "redirect:/customers";
        } else {
            return "redirect:/customer?incorrectAccount";
        }
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ModelAndView HomeCustomer() {
        ModelAndView mav = new ModelAndView("home-customer");
        return mav;
    }

    @GetMapping("/customers/contract-customer")
    public ModelAndView historyCustomer(@ModelAttribute(SystemConstant.MODEL) DetailContract model, HttpServletRequest request, HttpSession session) {
        ModelAndView mav = new ModelAndView("contract-customer");
        Long idCustomer = (Long) session.getAttribute("id");
        DisplayTagUtils.initSearchBean(request, model);
        model.setMaxPageItems(5);
        List<DetailContract> detailContracts = contractService.findAllDetailContractByIdCustomer(idCustomer, model);
        model.setTotalItems(contractService.getTotalItemsContractByIdCustomer(idCustomer, model));
        model.setListResult(detailContracts);
        model.setIdCustomer(idCustomer);
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }


    @GetMapping("/customers/detail")
    public ModelAndView detailContract(@RequestParam(value = "idContract", required = false) Long idContract, @ModelAttribute(SystemConstant.MODEL) DetailContract model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("detail-contract-customer");
        DisplayTagUtils.initSearchBean(request, model);
        model.setMaxPageItems(5);
        List<DetailContract> detailContracts = contractService.findAllDetailContract(idContract, model);
        model.setTotalItems(contractService.getTotalItemsContract(idContract, model));
        model.setListResult(detailContracts);
        model.setIdContract(idContract);
        mav.addObject(SystemConstant.MODEL, model);
        mav.addObject("idContract", idContract);
        return mav;
    }

    @RequestMapping(value = "/customers/edit", method = RequestMethod.GET)
    public ModelAndView updatOrAddCustomer(HttpSession session,
                                           HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("customer-update");
        Long id = (Long) session.getAttribute("id");
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO = customerService.findOneById(id);
        mav.addObject(SystemConstant.MODEL, customerDTO);
        return mav;
    }
}
