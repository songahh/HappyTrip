package com.happytrip.domain.attraction.model.service;

import com.happytrip.domain.attraction.model.AttractionListDto;
import com.happytrip.domain.attraction.model.AddrDto;
import com.happytrip.domain.attraction.model.AttractionDetailDto;

import java.util.List;
import java.util.Map;

public interface AttractionService {
    AttractionListDto listAttraction(Map<String, String> param) throws Exception;
    AttractionDetailDto getAttractionDetail(String aid) throws Exception;
    List<AddrDto> getAddr() throws Exception;
}
