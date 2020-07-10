package com.estate.controller.web;

import com.estate.constant.SystemConstant;
import com.estate.dto.BuildingDTO;
import com.estate.service.IBuildingService;
import com.estate.service.IDistrictService;
import com.estate.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IDistrictService districtService;

    @RequestMapping(value = "/web-home", method = RequestMethod.GET)
    public ModelAndView getBuildings(@ModelAttribute(SystemConstant.MODEL) BuildingDTO model,
                                     HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("home-user");
       // DisplayTagUtils.initSearchBean(request, model);
        model.setMaxPageItems(9);
        model.setCheckBuildingInContract(0);
        List<BuildingDTO> buildings = buildingService.searchBuildingWebHome(model, false);
        model.setTotalItems(buildingService.getTotalItemsWeb(model, false));
        model.setListResult(buildings);
        model.setDistricts(districtService.getDistricts());
        mav.addObject("priceMin", getPriceBuildingMin());
        mav.addObject("priceMax", getPriceBuildingMax());
        mav.addObject("areaMin", areaMin());
        mav.addObject("areaMax", areaMax());
        //initMessageResponse(mav, request);
        model.setTotalPages((int) Math.ceil((float)model.getTotalItems()/model.getMaxPageItems()));
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    @RequestMapping(value = "/web-detail", method = RequestMethod.GET)
    public ModelAndView detailHomeUser(@ModelAttribute(SystemConstant.MODEL) BuildingDTO model,
                                     HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("detail-home-user");
        model.setMaxPageItems(9);
        model.setCheckBuildingInContract(0);
        List<BuildingDTO> buildings = buildingService.searchBuildingWebHome(model, false);
        model.setTotalItems(buildingService.getTotalItemsWeb(model, false));
        model.setListResult(buildings);
        model.setDistricts(districtService.getDistricts());
        mav.addObject("priceMin", getPriceBuildingMin());
        mav.addObject("priceMax", getPriceBuildingMax());
        mav.addObject("areaMin", areaMin());
        mav.addObject("areaMax", areaMax());
        //initMessageResponse(mav, request);
        model.setTotalPages((int) Math.ceil((float)model.getTotalItems()/model.getMaxPageItems()));
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }



    private Map<Integer, String> getPriceBuildingMin() {
        Map<Integer, String> maps = new LinkedHashMap<>();
        maps.put(0, "0");
        maps.put(1000000, "1 Triệu");
        maps.put(2000000, "2 Triệu");
        maps.put(3000000, "3 Triệu");
        maps.put(4000000, "4 Triệu");
        maps.put(5000000, "5 Triệu");
        return maps;
    }

    private Map<Integer, String> getPriceBuildingMax() {
        Map<Integer, String> maps = new LinkedHashMap<>();
        maps.put(1000000, "1 Triệu");
        maps.put(2000000, "2 Triệu");
        maps.put(3000000, "3 Triệu");
        maps.put(4000000, "4 Triệu");
        maps.put(5000000, "5 Triệu");
        return maps;
    }

    private Map<Integer, String> areaMin() {
        Map<Integer, String> maps = new LinkedHashMap<>();
        maps.put(0, "0 m2");
        maps.put(10, "10 m2");
        maps.put(20, "20 m2");
        maps.put(30, "30 m2");
        maps.put(40, "40 m2");
        maps.put(50, "50 m2");
        return maps;
    }

    private Map<Integer, String> areaMax() {
        Map<Integer, String> maps = new LinkedHashMap<>();
        maps.put(10, "10 m2");
        maps.put(20, "20 m2");
        maps.put(30, "30 m2");
        maps.put(40, "40 m2");
        maps.put(50, "50 m2");
        return maps;
    }
}
