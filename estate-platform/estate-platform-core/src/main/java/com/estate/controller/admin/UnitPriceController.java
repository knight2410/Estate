package com.estate.controller.admin;

import com.estate.constant.SystemConstant;
import com.estate.dto.UnitPriceDto;
import com.estate.service.IUnitPriceService;
import com.estate.utils.DisplayTagUtils;
import com.estate.utils.MessageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class UnitPriceController {

    @Autowired
    private IUnitPriceService unitPriceService;

    @GetMapping("/admin/list-unitprice")
    public ModelAndView getUnitPriceByBuildingId(@RequestParam(value = "idBuilding", required = false) Long idBuilding, @ModelAttribute(SystemConstant.MODEL) UnitPriceDto model, HttpServletRequest request ) {
        ModelAndView mav = new ModelAndView("admin/unitprice/list");
        DisplayTagUtils.initSearchBean(request, model);
        model.setMaxPageItems(5);
        List<UnitPriceDto> unitPriceDtos = unitPriceService.getAllUnitPriceByIdBuilding(idBuilding,model);
        model.setTotalItems(unitPriceService.getTotalItems(idBuilding));
        model.setListResult(unitPriceDtos);
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL, model);
        mav.addObject("idBuilding", idBuilding);
        return mav;
    }

    @GetMapping("/admin/create-unit-price/{idBuilding}")
    public String createUnitPrice(@PathVariable("idBuilding") Long idBuilding, Model model) {
        UnitPriceDto unitPriceDto = new UnitPriceDto();
        if (unitPriceService.findLastUnitPrice(idBuilding) != null) {
            unitPriceDto = unitPriceService.findLastUnitPrice(idBuilding);
        }
       // unitPriceDto.setEffectiveDate(null);
        unitPriceDto.setIdBuilding(idBuilding);
        model.addAttribute("model", unitPriceDto);
        return "admin/unitprice/create";
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
