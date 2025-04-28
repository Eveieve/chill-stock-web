package com.chilluminati.chillstock.admin.warehouse.service.func;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Service
public class CreateAreaCode implements BiFunction< Integer,Integer, String>  {
    @Override
    public String apply(Integer storageId, Integer warehouseId) {
        Map<Integer,String> supplierTemp = new HashMap();
        supplierTemp.put(1,"A-");
        supplierTemp.put(2,"B-");
        supplierTemp.put(3,"C-");
        supplierTemp.put(4,"D-");
        supplierTemp.put(5,"E-");
        supplierTemp.put(6,"F-");

        StringBuilder areaCode = new StringBuilder();
        areaCode.append(supplierTemp.get(storageId));
        areaCode.append(warehouseId);
        areaCode.append("-" + (int)(Math.random() * 1000000));
        return areaCode.toString();
    }
}


