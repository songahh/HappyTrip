package com.ssafy.enjoytrip.attraction.model.service;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.AttractionListDto;
import com.ssafy.enjoytrip.attraction.model.mapper.AttractionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class AttractionServiceImpl implements AttractionService{

    private final AttractionMapper am;

    @Override
    @Transactional(readOnly = true)
    public AttractionListDto listAttraction(Map<String, String> map) throws Exception {
        Map<String, Object> param = new HashMap<>();
        int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : (String) map.get("pgno"));
        int sizePerPage = Integer.parseInt(map.get("spp") == null ? "10" : (String) map.get("spp"));
        int start = currentPage * sizePerPage - sizePerPage;
        String word = map.get("word") == null ? "" : (String) map.get("word");
        param.put("start", start);
        param.put("listsize", sizePerPage);
        param.put("word", word);

        List<AttractionDto> list = am.listAttraction(param);
        log.info("listAttraction found {}", list);
        int totalAttractionCount = am.getTotalAttractionCount(param);
        int totalPageCount = (totalAttractionCount - 1) / sizePerPage + 1;
        AttractionListDto attractionListDto = new AttractionListDto();
        attractionListDto.setAttractions(list);
        attractionListDto.setCurrentPage(currentPage);
        attractionListDto.setTotalPageCount(totalPageCount);
        return attractionListDto;
    }

    @Override
    @Transactional(readOnly = true)
    public AttractionDto getAttraction(Map<String, String> map) throws Exception {
        Map<String, Object> param = new HashMap<>();
        if(map.get("lat") == null || map.get("lon") == null)
            throw new RuntimeException("위치정보를 알려주세요.");

        Double latitude = Double.parseDouble(map.get("lat"));
        Double longitude = Double.parseDouble(map.get("lon"));

        param.put("latitude", latitude);
        param.put("longitude", longitude);

        return am.getAttraction(param);
    }
}