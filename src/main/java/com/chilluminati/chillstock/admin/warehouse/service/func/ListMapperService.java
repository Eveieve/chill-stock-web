package com.chilluminati.chillstock.admin.warehouse.service.func;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ListMapperService<T, R> implements
        BiFunction<List<T>, Function<T, R>, List<R>> {

    @Override
    public List<R> apply(List<T> list, Function<T, R> mapper) {
        return list.stream()
                .map(mapper::apply)
                .collect(Collectors.toList());
    }
}
