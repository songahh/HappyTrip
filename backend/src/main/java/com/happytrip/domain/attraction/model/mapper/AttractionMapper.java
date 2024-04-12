package com.happytrip.domain.attraction.model.mapper;

import com.happytrip.domain.attraction.model.AttractionDetailDto;
import com.happytrip.domain.attraction.model.AddrDto;
import com.happytrip.domain.attraction.model.AttractionDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AttractionMapper {
    List<AttractionDto> listAttraction(Map<String, Object> param) throws SQLException;

    int getTotalAttractionCount(Map<String, Object> param) throws SQLException;

    AttractionDto getAttraction(Map<String, Object> param) throws SQLException;
    AttractionDetailDto getAttractionDetail(int id) throws SQLException;
    List<AddrDto> getAddr() throws SQLException;
}
