package com.chilluminati.chillstock.admin.warehouse.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminWarehouseDto {
    private Integer warehouseId; // Warehouse ID

    @NotBlank(message = "Please enter warehouse name")
    private String warehouseName; // Warehouse name

    @Min(value = 100, message = "Warehouse size must be at least 100.")
    private Integer warehouseSpace; // Warehouse size

    @NotBlank(message = "Please enter warehouse address")
    private String warehouseAddress; // Warehouse address

    private Integer warehouseAmount; // Warehouse fixed cost amount
}
