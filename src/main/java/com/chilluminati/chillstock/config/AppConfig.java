package com.chilluminati.chillstock.config;


import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;
import com.chilluminati.chillstock.nonuser.vo.UserVO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
@ComponentScan(
        basePackages = "com.chilluminati.chillstock",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = "com\\.chilluminati\\.chillstock\\.config\\..*"
        )
)
public class AppConfig {

        @Bean
        public ModelMapper modelMapper() {
                ModelMapper modelMapper = new ModelMapper();

                modelMapper.typeMap(SignUpDTO.class, UserVO.class)
                        .addMappings(mapper -> mapper.skip(UserVO::setUserId)); // 자동 매핑 제외

                return modelMapper;
        }
}
