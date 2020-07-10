package com.estate.dto;
import org.springframework.web.multipart.MultipartFile;


import java.util.Date;
import java.util.List;
import java.util.Map;

public class BuildingDTO extends AbstractDTO<BuildingDTO> {

    private static final long serialVersionUID = 4428522696190004907L;

    private String name;
    private Map<String, String> districts;
    private String ward;
    private String street;
    private String address;
    private String district;
    private String districtName;
    private String structure;
    private Integer floorArea;
    private String direction;
    private String type;
    private Integer rentArea;
    private Integer areaFrom;
    private Integer areaTo;
    private Integer price;
    private Integer priceFrom;
    private Integer priceTo;
    private String serviceFee;
    private String motorParkingCharge;
    private String carParkingCharge;
    private String extraCharge;
    private String electricCharge;
    private String deposit;
    private String payment;
    private String timeForRent;
    private String timeForDecorate;
    private String managerName;
    private String managerPhone;
    private String agencyCharge;
    private String types;
    private String[] typeArrays = new String[]{};
    private String description;
    private String link;
    private String map;
    private String avatar;
    private String imageName;
    private String avatarBase64;
    private List<UserDTO> users;
    private String user;
    private int prioritize = 0;
   // private int existsBuildingAndCustomer = 0;
    private List<UserDTO> userAssignment;
    private String staffName;
    private Long userId;
    private MultipartFile multipartFile;
    private Long staffUse;
    private Integer numberOfRooms;

    private boolean existBuildingInContract = false;

    private Integer checkBuildingInContract;

    private boolean unitPrice;

    private String contentWeb;

    private String nameUser;

    private String phoneUser;

    private UnitPriceDto unitPriceDto;

    public UnitPriceDto getUnitPriceDto() {
        return unitPriceDto;
    }

    public void setUnitPriceDto(UnitPriceDto unitPriceDto) {
        this.unitPriceDto = unitPriceDto;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String getContentWeb() {
        return contentWeb;
    }

    public void setContentWeb(String contentWeb) {
        this.contentWeb = contentWeb;
    }

    public Integer getCheckBuildingInContract() {
        return checkBuildingInContract;
    }

    public void setCheckBuildingInContract(Integer checkBuildingInContract) {
        this.checkBuildingInContract = checkBuildingInContract;
    }

    public boolean isExistBuildingInContract() {
        return existBuildingInContract;
    }

    public void setExistBuildingInContract(boolean existBuildingInContract) {
        this.existBuildingInContract = existBuildingInContract;
    }

    public boolean isUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(boolean unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getStaffUse() {
        return staffUse;
    }

    public void setStaffUse(Long staffUse) {
        this.staffUse = staffUse;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getAvatarBase64() {
        if (avatarBase64 == null) {
            return avatarBase64;
        }
        return avatarBase64.split(",")[1];
    }

    public void setAvatarBase64(String avatarBase64) {
        this.avatarBase64 = avatarBase64;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<String, String> getDistricts() {
        return districts;
    }

    public void setDistricts(Map<String, String> districts) {
        this.districts = districts;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getMotorParkingCharge() {
        return motorParkingCharge;
    }

    public void setMotorParkingCharge(String motorParkingCharge) {
        this.motorParkingCharge = motorParkingCharge;
    }

    public String getCarParkingCharge() {
        return carParkingCharge;
    }

    public void setCarParkingCharge(String carParkingCharge) {
        this.carParkingCharge = carParkingCharge;
    }

    public String getExtraCharge() {
        return extraCharge;
    }

    public void setExtraCharge(String extraCharge) {
        this.extraCharge = extraCharge;
    }

    public String getElectricCharge() {
        return electricCharge;
    }

    public void setElectricCharge(String electricCharge) {
        this.electricCharge = electricCharge;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getTimeForRent() {
        return timeForRent;
    }

    public void setTimeForRent(String timeForRent) {
        this.timeForRent = timeForRent;
    }

    public String getTimeForDecorate() {
        return timeForDecorate;
    }

    public void setTimeForDecorate(String timeForDecorate) {
        this.timeForDecorate = timeForDecorate;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getAgencyCharge() {
        return agencyCharge;
    }

    public void setAgencyCharge(String agencyCharge) {
        this.agencyCharge = agencyCharge;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }


    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String[] getTypeArrays() {
        if (types == null) {
            return typeArrays;
        }
        return types.split(",");
    }

    public void setTypeArrays(String[] typeArrays) {
        this.typeArrays = typeArrays;
    }


    public int getPrioritize() {
        return prioritize;
    }

    public void setPrioritize(int prioritize) {
        this.prioritize = prioritize;
    }

    public List<UserDTO> getUserAssignment() {
        return userAssignment;
    }

    public void setUserAssignment(List<UserDTO> userAssignment) {
        this.userAssignment = userAssignment;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
    }

    public Integer getRentArea() {
        return rentArea;
    }

    public void setRentArea(Integer rentArea) {
        this.rentArea = rentArea;
    }

    public Integer getAreaFrom() {
        return areaFrom;
    }

    public void setAreaFrom(Integer areaFrom) {
        this.areaFrom = areaFrom;
    }

    public Integer getAreaTo() {
        return areaTo;
    }

    public void setAreaTo(Integer areaTo) {
        this.areaTo = areaTo;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Integer getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Integer priceTo) {
        this.priceTo = priceTo;
    }

//	public int getExistsBuildingAndCustomer() {
//		return existsBuildingAndCustomer;
//	}
//
//	public void setExistsBuildingAndCustomer(int existsBuildingAndCustomer) {
//		this.existsBuildingAndCustomer = existsBuildingAndCustomer;
//	}


    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }
}
