package com.chilluminati.chillstock.admin.warehouse.vo;


import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminStorageVo {
    private Integer storageId;
    private String storageName;      // ENUM이지만 DB에서는 String으로 전달됨
    private Integer minTemp;
    private Integer maxTemp;
    private String description;
}
