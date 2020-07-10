package com.estate.controller.admin;

import com.estate.constant.SystemConstant;
import com.estate.converter.UnitPriceConverter;
import com.estate.dto.ConsumeDto;
import com.estate.dto.ContractDto;
import com.estate.dto.DetailContract;
import com.estate.dto.UnitPriceDto;
import com.estate.entity.ConsumeEntity;
import com.estate.entity.ContractEntity;
import com.estate.entity.PaymentEntity;
import com.estate.entity.UnitPriceEntity;
import com.estate.repository.ContractRepository;
import com.estate.repository.PaymentRepository;
import com.estate.repository.custom.ConsumeRepositoryCustom;
import com.estate.repository.custom.UnitPriceRepositoryCustom;
import com.estate.service.IContractService;
import com.estate.service.ICustomerService;
import com.estate.service.IUserService;
import com.estate.utils.DisplayTagUtils;
import com.estate.utils.MessageUtil;
import com.estate.utils.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ContractController {

    @Autowired
    private IContractService contractService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private UnitPriceRepositoryCustom unitPriceRepositoryCustom;

    @Autowired
    private UnitPriceConverter unitPriceConverter;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ConsumeRepositoryCustom consumeRepositoryCustom;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private IUserService userService;

    @GetMapping("/admin/list-contract")
    public ModelAndView listContract(@ModelAttribute(SystemConstant.MODEL) ContractDto model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/contract/list");
        DisplayTagUtils.initSearchBean(request, model);
        model.setMaxPageItems(5);
        List<ContractDto> contractDtos = contractService.finAllContract(model, this.getUserAndRole());
        model.setTotalItems(contractService.getTotalItems(model, this.getUserAndRole()));
        model.setListResult(contractDtos);
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL, model);
        mav.addObject("nowCreate", new Date().getMonth() + 1);
        mav.addObject(SystemConstant.MAP_USERS, userService.getStaffs());
        return mav;
    }

    @GetMapping("/admin/create-contract")
    public ModelAndView createContract(@RequestParam(value = "idBuilding", required = false) Long idBuilding, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/contract/create");
        ConsumeDto consumeDto = new ConsumeDto();
        UnitPriceDto unitPriceDto = unitPriceConverter.convertToDto(unitPriceRepositoryCustom.findByIdBuildingAndEffectiveDate(idBuilding));
        consumeDto.setUnitPriceDto(unitPriceDto);
        consumeDto.setIdBuilding(idBuilding);
        if (this.getUserAndRole()) {
            mav.addObject("customders", customerService.getCustomerNotContractAndByUserIdIsManager());
        } else {
            mav.addObject("customders", customerService.getCustomerNotContractAndByUserId(SecurityUtils.getPrincipal().getId()));
        }
        mav.addObject(SystemConstant.MODEL, consumeDto);
        return mav;
    }

    //Xử Lý Hóa Đơn
    @GetMapping("/admin/invoice-processing")
    public ModelAndView invoiceProcessing(@RequestParam(value = "idContract", required = false) Long idContract) {
        ModelAndView mav = new ModelAndView("admin/contract/invoice-processing");
        ContractEntity contractEntity = contractRepository.findOne(idContract);
        UnitPriceDto unitPriceDto = unitPriceConverter.convertToDto(unitPriceRepositoryCustom.findByIdBuildingAndEffectiveDate(contractEntity.getIdBuilding()));
        ConsumeEntity consumeEntity = consumeRepositoryCustom.getConsumeLast(idContract);

        ConsumeDto consumeDto = new ConsumeDto();
        consumeDto.setPowerNumber(consumeEntity.getPowerNumber());
        consumeDto.setWaterNumber(consumeEntity.getWaterNumber());
        consumeDto.setPercentDaysStay(consumeEntity.getPercentDaysStay());
        consumeDto.setContractId(idContract);
        consumeDto.setUnitPriceDto(unitPriceDto);
        consumeDto.setIdBuilding(contractEntity.getIdBuilding());
        consumeDto.setIdCustomer(contractEntity.getIdCustomer());
        mav.addObject(SystemConstant.MODEL, consumeDto);
        return mav;
    }

    //Xử Lý Tính Tiền
    @GetMapping("/admin/waiting-money")
    public ModelAndView waitingMoney(@RequestParam(value = "idContract", required = false) Long idContract) {
        ModelAndView mav = new ModelAndView("admin/contract/waiting-money");
        ContractEntity contractEntity = contractRepository.findOne(idContract);
        UnitPriceDto unitPriceDto = unitPriceConverter.convertToDto(unitPriceRepositoryCustom.findByIdBuildingAndEffectiveDate(contractEntity.getIdBuilding()));
        ConsumeEntity consumeEntity = consumeRepositoryCustom.getConsumeLast(idContract);
        PaymentEntity paymentEntity = paymentRepository.findOneByIdConsume(consumeEntity.getId());
        ConsumeDto consumeDto = new ConsumeDto();
        consumeDto.setPowerNumber(this.findTwoConsume(idContract).getPowerNumber());
        consumeDto.setWaterNumber(this.findTwoConsume(idContract).getWaterNumber());
        consumeDto.setContractId(idContract);
        consumeDto.setUnitPriceDto(unitPriceDto);
        consumeDto.setIdBuilding(contractEntity.getIdBuilding());
        consumeDto.setIdCustomer(contractEntity.getIdCustomer());
        consumeDto.setNote(consumeEntity.getNote());
        consumeDto.setPercentDaysStay(consumeEntity.getPercentDaysStay());
        consumeDto.setAmountPaid(paymentEntity.getAmountPaid());
        consumeDto.setAmountPayable(paymentEntity.getAmountPayable());
        consumeDto.setId(consumeEntity.getId());
        mav.addObject(SystemConstant.MODEL, consumeDto);
        return mav;
    }


    @GetMapping("/admin/detail-contract")
    public ModelAndView detailContract(@RequestParam(value = "idContract", required = false) Long idContract, @ModelAttribute(SystemConstant.MODEL) DetailContract model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/contract/detail");
        DisplayTagUtils.initSearchBean(request, model);
        model.setMaxPageItems(5);
        List<DetailContract> detailContracts = contractService.findAllDetailContract(idContract, model);
        model.setTotalItems(contractService.getTotalItemsContract(idContract,model));
        model.setListResult(detailContracts);
        model.setIdContract(idContract);
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL, model);
        mav.addObject("idContract", idContract);
        return mav;
    }

    @GetMapping("/admin/contract-history-building")
    public ModelAndView historyBuilding(@RequestParam(value = "idBuilding", required = false) Long idBuilding, @ModelAttribute(SystemConstant.MODEL) DetailContract model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/contract/contract-history-building");
        DisplayTagUtils.initSearchBean(request, model);
        model.setMaxPageItems(5);
        List<DetailContract> detailContracts = contractService.findAllDetailContractByIdBuilding(idBuilding, model);
        model.setTotalItems(contractService.getTotalItemsContractByIdBuilding(idBuilding,model));
        model.setListResult(detailContracts);
        model.setIdBuilding(idBuilding);
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    @GetMapping("/admin/contract-history-customer")
    public ModelAndView historyCustomer(@RequestParam(value = "idCustomer", required = false) Long idCustomer, @ModelAttribute(SystemConstant.MODEL) DetailContract model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/contract/contract-history-customer");
        DisplayTagUtils.initSearchBean(request, model);
        model.setMaxPageItems(5);
        List<DetailContract> detailContracts = contractService.findAllDetailContractByIdCustomer(idCustomer, model);
        model.setTotalItems(contractService.getTotalItemsContractByIdCustomer(idCustomer,model));
        model.setListResult(detailContracts);
        model.setIdCustomer(idCustomer);
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL, model);
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

    private ConsumeDto findTwoConsume(Long idContract) {
        List<ConsumeEntity> consumeEntity = consumeRepositoryCustom.findTwoConsume(idContract);
        ConsumeDto consumeDto = new ConsumeDto();
        consumeDto.setWaterNumber(consumeEntity.get(0).getWaterNumber() - consumeEntity.get(1).getWaterNumber());
        consumeDto.setPowerNumber(consumeEntity.get(0).getPowerNumber() - consumeEntity.get(1).getPowerNumber());
        return consumeDto;
    }
    private boolean getUserAndRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isManager = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("MANAGER"));
        return isManager;
    }
}
