package com.estate.service;

import com.estate.dto.BuildingDTO;
import com.estate.dto.ContractDto;
import com.estate.dto.UserDTO;

import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Pageable;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface IBuildingService  {
    List<BuildingDTO> searchBuildingsAssignment(BuildingDTO model,boolean isAssignment);
    List<BuildingDTO> searchBuildingsByPriority(BuildingDTO model);
    int getTotalItemsAssignment(BuildingDTO model,boolean isAssignment);
    int getTotalItemsPriority(BuildingDTO model);
    BuildingDTO insert(BuildingDTO buildingDTO);
    BuildingDTO update(BuildingDTO updateBuilding, long id);
    BuildingDTO findBuildingById(long id);
    void deleteBuilding(long[] ids);
    Map<String,String> getBuildingTypes();
    void assignBuildingToStaff(Long[] userId,long id);
    void updatePriority(String action,long id);
    List<UserDTO> getUserByBuildingAndUserLogin(Long buildingId);
    String exportReport() throws FileNotFoundException, JRException;
    List<BuildingDTO> searchBuildingWebHome(BuildingDTO model,boolean isAssignment);
    int getTotalItemsWeb(BuildingDTO model,boolean isAssignment);
}
