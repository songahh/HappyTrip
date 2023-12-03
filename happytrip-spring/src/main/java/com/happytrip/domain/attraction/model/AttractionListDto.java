package com.happytrip.domain.attraction.model;


import lombok.Data;

import java.util.List;

@Data
public class AttractionListDto {

    private List<AttractionDto> attractions;
    private int currentPage;
    private int totalPageCount;
}
