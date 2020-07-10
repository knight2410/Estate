package com.estate.controller.admin.api;

import com.estate.dto.BuildingDTO;
import com.estate.repository.UnitPriceRepository;
import com.estate.service.IBuildingService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;


@RestController
@RequestMapping(value = "/api/admin/building")
public class BuildingAPI {

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private UnitPriceRepository unitPriceRepository;


    @PostMapping
    public ResponseEntity<BuildingDTO> createBuilding(@RequestBody BuildingDTO buildingDTO) {
        return ResponseEntity.ok(buildingService.insert(buildingDTO));

    }
    @PostMapping("/test")
    public ResponseEntity<BuildingDTO> testBuilding(@RequestParam("file") Integer siteNumber) {
        return ResponseEntity.ok(buildingService.insert(new BuildingDTO()));

    }

    @PutMapping("/{id}")
    public ResponseEntity<BuildingDTO> updateBuilding(@RequestBody BuildingDTO buildingDTO, @PathVariable("id") long id) {
        return ResponseEntity.ok(buildingService.update(buildingDTO, id));

    }
    @PostMapping("/exists-building-in-unitprice")
    public ResponseEntity<Boolean> existsBuildingInUnitprice(@RequestBody BuildingDTO buildingDTO) {
        Boolean exists = unitPriceRepository.existsByIdBuilding(buildingDTO.getId());
        return ResponseEntity.ok(exists);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBuilding(@RequestBody long[] ids) {
        if (ids.length > 0) {
            buildingService.deleteBuilding(ids);
        }
        return ResponseEntity.ok("success");
    }

    @PostMapping("/{id}/users")
    public ResponseEntity<Void> assignBuildingToStaff(@RequestBody Long[] userId, @PathVariable("id") long id) {
        buildingService.assignBuildingToStaff(userId, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/priority")
    public ResponseEntity<Void> updatePriority(@RequestParam(value = "action", required = false) String action, @PathVariable("id") long id) {
        buildingService.updatePriority(action, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllBuildings() throws FileNotFoundException, JRException {
        return ResponseEntity.ok(buildingService.exportReport());
    }
}
