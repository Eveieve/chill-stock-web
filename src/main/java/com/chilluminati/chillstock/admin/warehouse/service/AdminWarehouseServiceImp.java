package com.chilluminati.chillstock.admin.warehouse.service;

import com.chilluminati.chillstock.admin.user.repository.AdminUserRepo;
import com.chilluminati.chillstock.admin.warehouse.dto.AdminAreaDto;
import com.chilluminati.chillstock.admin.warehouse.dto.AdminAreaWithRemainDistanceDto;
import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseDto;
import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseRemainSpaceDto;
import com.chilluminati.chillstock.admin.warehouse.repository.AdminAreaRepository;
import com.chilluminati.chillstock.admin.warehouse.repository.AdminWareHouseRepository;
import com.chilluminati.chillstock.admin.warehouse.service.func.ListMapperService;
import com.chilluminati.chillstock.admin.warehouse.service.func.WarehouseDtoToVo;
import com.chilluminati.chillstock.admin.warehouse.vo.*;
import com.chilluminati.chillstock.common.ResultList;
import com.chilluminati.chillstock.security.EmailUserDetails;
import com.chilluminati.chillstock.webclient.GeoPoint;
import com.chilluminati.chillstock.webclient.KakaoGeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminWarehouseServiceImp implements AdminWarehouseService{

    private final AdminWareHouseRepository adminWareHouseRepository;
    private final AdminAreaRepository adminAreaRepository;
    private final AdminUserRepo adminUserRepo;
    private final KakaoGeoService kakaoGeoService;

    private final Function<AdminWarehouseDto, AdminWarehouseVo> warehouseDtoToVo;
    private final Function<AdminWarehouseVo, AdminWarehouseDto> warehouseVoToDto;
    private final BiFunction<List<AdminWarehouseVo>, Function<AdminWarehouseVo, AdminWarehouseDto>, List<AdminWarehouseDto>> listMapperService;
    private final Supplier<Integer> getAuthUserIdDetails;
    private final Function<List<AdminStorageVo>, Map<Integer,String>> listToMapStorage;

    @Override
    public void registerWarehouse(AdminWarehouseDto adminWarehouseDto) {
        AdminWarehouseVo adminWarehouseVo = warehouseDtoToVo.apply(adminWarehouseDto);
        adminWareHouseRepository.createWarehouse(adminWarehouseVo);
    }

    @Override
    public List<AdminWarehouseDto> getAllWarehouses() {
        return listMapperService.apply(adminWareHouseRepository.adminGetAllWarehouses(),
                warehouseVoToDto::apply);
    }

    @Override
    public List<AdminWarehouseRemainSpaceDto> getAllWarehousesWithRemain() {
        // 1. 창고별 남은 공간 정보 1회 조회
        Map<Integer, Integer> remainSpaceMap = adminAreaRepository.getAllAdminWarehouseSpaceUsage().stream()
                .collect(Collectors.toMap(
                        AdminWarehouseSpaceRemainVo::getWarehouseId,
                        AdminWarehouseSpaceRemainVo::getRemainSpace
                ));

        // 2. 창고 목록 조회 후 DTO 변환
        return adminWareHouseRepository.adminGetAllWarehouses().stream()
                .map(adminWarehouseVo -> {
                    Integer remainSpace = remainSpaceMap.getOrDefault(adminWarehouseVo.getWarehouseId(), 0);
                    return AdminWarehouseRemainSpaceDto.builder()
                            .warehouseId(adminWarehouseVo.getWarehouseId())
                            .warehouseName(adminWarehouseVo.getWarehouseName())
                            .warehouseSpaceRemain(remainSpace) // 남은공간
                            .warehouseAddress(adminWarehouseVo.getWarehouseAddress())
                            .warehouseAmount(adminWarehouseVo.getWarehouseAmount())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public ResultList<List<AdminWarehouseVo>> getAllWarehousesByAddress(String address) {
        List<AdminWarehouseVo> list = adminWareHouseRepository.adminGetAllWarehouseByAddress(address);

        return list.isEmpty()
                ? ResultList.emptyList("해당 주소에 일치하는 창고가 없습니다.")
                : ResultList.success(list, address + "에 대한 일치하는 창고 리스트 목록");
    }

    @Override
    public void updateWarehouse(AdminWarehouseDto adminWarehouseDto) {
        AdminWarehouseVo adminWarehouseVo = warehouseDtoToVo.apply(adminWarehouseDto);
        adminWareHouseRepository.adminUpdateWarehouseById(adminWarehouseVo);
    }

    /**
     * 창고 아이디로 해당 창고정보 검색
     * @param warehouseId
     * @return
     */
    @Override
    public AdminWarehouseDto getAdminWarehouseById(Integer warehouseId) {
        Optional<AdminWarehouseVo> adminWarehouseVo = adminWareHouseRepository.adminGetWarehouseById(warehouseId);
        return adminWarehouseVo
                .map(warehouseVoToDto::apply)
                .orElseThrow(() -> new NoSuchElementException("해당 ID의 창고 없음: " + warehouseId));
    }

    @Override
    public AdminWarehouseRemainSpaceDto getAdminWarehouseRemainSpaceById(Integer warehouseId) {
        Optional<AdminWarehouseSpaceRemainVo> adminWarehouseSpaceUsageById = adminAreaRepository.getAdminWarehouseSpaceUsageById(warehouseId);
        return adminWarehouseSpaceUsageById.map(
                vo -> AdminWarehouseRemainSpaceDto.builder()
                        .warehouseId(vo.getWarehouseId())
                        .warehouseSpaceRemain(vo.getRemainSpace())
                        .build()
        ).orElseThrow(() -> new NoSuchElementException("해당 id의 창고 없음" + warehouseId));
    }

    @Override
    public List<AdminAreaWithRemainDistanceDto> getAllAdminAreaWithRemainDistance() {
        Integer userId = getAuthUserIdDetails.get();

        String userAddress = adminUserRepo.getUserBizById(userId).getBusinessAddress();

        GeoPoint userGeoPoint = kakaoGeoService.getGeoByAddress(userAddress).orElseThrow(()->
                new RuntimeException("해당 주소 유효하지 않음"));


        Map<Integer, Integer> remainSpaceMap = adminAreaRepository.getAllAdminAreaSpaceUsage().stream()
                .collect(Collectors.toMap(
                        AdminAreaSpaceRemainVo::getAreaId,
                        AdminAreaSpaceRemainVo::getRemainSpace
                ));

        return adminAreaRepository.AdminGetAllAreas().stream().map(
                vo -> AdminAreaWithRemainDistanceDto.builder()
                        .areaId(vo.getAreaId())
                        .areaSpace(vo.getAreaSpace())
                        .areaCode(vo.getAreaCode())
                        .areaPrice(vo.getAreaPrice())
                        .warehouseId(vo.getWarehouseId())
                        .storageId(vo.getStorageId())
                        .remainSpace(remainSpaceMap.get(vo.getAreaId()))
                        .distance(userGeoPoint.distanceTo(kakaoGeoService.getGeoByAddress(
                                adminWareHouseRepository.adminGetWarehouseById(vo.getWarehouseId())
                                        .orElseThrow(()->new RuntimeException("해당 창고아이디 없음"))
                                        .getWarehouseAddress()
                                ).orElseThrow(()->new NoSuchElementException("해당 유저아이디 없음")
                        )))
                .build()
        ).collect(Collectors.toList());
    }

    @Override
    public List<AdminAreaDto> getAreasByWarehouseId(Integer warehouseId){
        List<AdminStorageVo> adminStorageVos = adminAreaRepository.AdminGetAllStorages();

        Map<Integer, Integer> remainSpaceMap = adminAreaRepository.getAllAdminAreaSpaceUsage().stream()
                .collect(Collectors.toMap(
                        AdminAreaSpaceRemainVo::getAreaId,
                        AdminAreaSpaceRemainVo::getRemainSpace
                ));

        //구역리스트 아이디값 메시지로 변환
        return adminAreaRepository.AdminGetAreaById(warehouseId).stream().map((vo) -> AdminAreaDto.builder()
                        .areaId(vo.getAreaId())
                        .areaSpace(vo.getAreaSpace())
                        .areaCode(vo.getAreaCode())
                        .areaPrice(vo.getAreaPrice())
                        .warehouseId(vo.getWarehouseId())
                        .storageMessage(listToMapStorage.apply(adminStorageVos).get(vo.getStorageId()))
                        .remainSpace(remainSpaceMap.get(vo.getAreaId()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public void updateStorageIdByAreaId(Integer areaId, Integer storageId) {
        adminAreaRepository.updateStorageIdByAreaId(areaId, storageId);
    }
}
