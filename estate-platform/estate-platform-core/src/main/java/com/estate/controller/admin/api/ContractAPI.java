package com.estate.controller.admin.api;

import com.estate.dto.ConsumeDto;
import com.estate.dto.ContractDto;
import com.estate.entity.TestEntity;
import com.estate.repository.custom.ContractRepositoryCustom;
import com.estate.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/contract")
public class ContractAPI {

    @Autowired
    private IContractService contractService;

    @Autowired
    private ContractRepositoryCustom contractRepositoryCustom;

    @PostMapping
    public ResponseEntity<ContractDto> createContract(@RequestBody ConsumeDto consumeDto) {
        return ResponseEntity.ok(contractService.save(consumeDto));
    }

    @PostMapping("/download")
    public ResponseEntity<Object> dowloadFile(HttpServletResponse response, @RequestBody ContractDto contractDto) throws IOException {
        List<ContractDto> entities = contractService.finAllContractDownload(contractDto, this.getUserAndRole());
        OutputStreamWriter fileWriter = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            StringBuilder fileContent = new StringBuilder();
            fileContent.append("Tên người tạo");
            fileContent.append(",");
            fileContent.append("Mã Phòng");
            fileContent.append(",");
            fileContent.append("Tên khách hàng");
            fileContent.append(",");
            fileContent.append("Ngày kí hợp đồng");
            fileContent.append(",");
            fileContent.append("Ngày tạo hóa đơn");
            fileContent.append(",");
            fileContent.append("Số điện SD");
            fileContent.append(",");
            fileContent.append("Số nước SD");
            fileContent.append(",");
            fileContent.append("Ngày hiệu lực");
            fileContent.append(",");
            fileContent.append("Giá điện");
            fileContent.append(",");
            fileContent.append("Giá nước");
            fileContent.append(",");
            fileContent.append("Giá phòng");
            fileContent.append(",");
            fileContent.append("Số tiền đã đóng");
            fileContent.append(",");
            fileContent.append("Số tiền nợ");
            fileContent.append(",");
            fileContent.append("Số tiền phải đóng");
            fileContent.append("\n");

            for (ContractDto item : entities) {
                fileContent.append("\"" + item.getCreatedBy() + "\"");
                fileContent.append(",");
                fileContent.append("\"" + item.getNameBuilding() + "\"");
                fileContent.append(",");
                fileContent.append("\"" + item.getNameCustomer() + "\"");
                fileContent.append(",");
                fileContent.append("\"" + dateFormat.format(item.getCreatedDate()) + "\"");
                fileContent.append(",");
                fileContent.append("\"" + dateFormat.format(item.getCreatedDateConsumer()) + "\"");
                fileContent.append(",");
                fileContent.append("\"" + item.getPowerNumber() != null ? item.getPowerNumber() : "" + "\"");
                fileContent.append(",");
                fileContent.append("\"" + item.getWaterNumber() != null ? item.getWaterNumber() : "" + "\"");
                fileContent.append(",");
                fileContent.append("\"" + dateFormat.format(item.getEffectiveDate()) + "\"");
                fileContent.append(",");
                fileContent.append("\"" + item.getElectricityPrice() + "\"");
                fileContent.append(",");
                fileContent.append("\"" + item.getWaterPrice() + "\"");
                fileContent.append(",");
                fileContent.append("\"" + item.getRoomPrice() + "\"");
                fileContent.append(",");
                fileContent.append("\"" + this.formatAmountPaid(item.getAmountPaid()) + "\"");
                fileContent.append(",");
                fileContent.append("\"" + item.getPercentDaysStay() + "\"");
                fileContent.append(",");
                fileContent.append("\"" + item.getAmountPayable() + "\"");
                fileContent.append("\n");
            }

            String fileName = "C:\\Users\\Admin\\Downloads/ChiTietHopDong.csv";
            Writer fstream = null;
            BufferedWriter out = null;
            //fileWriter = new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8);
            /***/
            fileWriter = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
            fileWriter.write(fileContent.toString());
            fileWriter.flush();
            File file = new File(fileName);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length())
                    .contentType(MediaType.parseMediaType("text/csv")).body(resource);
            return responseEntity;
        } catch (Exception e) {
            e.getStackTrace();
            return new ResponseEntity<>("error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            if (fileWriter != null)
                fileWriter.close();
        }
    }

    @PostMapping("/invoice-processing")
    public ResponseEntity<ContractDto> invoiceProcessing(@RequestBody ConsumeDto consumeDto) {
        return ResponseEntity.ok(contractService.invoiceProcessing(consumeDto));
    }

    @PostMapping("/sendMail")
    public ResponseEntity<Boolean> sendMail(@RequestBody ConsumeDto consumeDto) {
        try {
            contractService.sendMail(consumeDto);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }

    @PostMapping("/updateConsume")
    public ResponseEntity<Boolean> updateConsume(@RequestBody ConsumeDto consumeDto) {
        try {
            contractService.updateConsume(consumeDto);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteContract(@RequestBody long[] ids) {
        if (ids.length > 0) {
            contractService.deleteContract(ids);
        }
        return ResponseEntity.ok("success");
    }

    private boolean getUserAndRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isManager = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("MANAGER"));
        return isManager;
    }

    private String formatAmountPaid(Double item) {
        if (item == null) {
            return "Chờ Nhận Tiền";
        } else {
            return item.toString();
        }
    }

}
