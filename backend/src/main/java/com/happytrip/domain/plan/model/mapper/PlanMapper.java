package com.happytrip.domain.plan.model.mapper;

import com.happytrip.domain.plan.model.PlanContentDto;
import com.happytrip.domain.plan.model.PlanMetaDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PlanMapper {

    /**
     * PlanMeta: 여행계획 정보
     * */
    List<PlanMetaDto> listPlanMeta(Map<String, Object> param) throws SQLException;
    PlanMetaDto getPlanMetaByPlanId(Long planId) throws SQLException;
    int getTotalPlanMeta(Map<String, Object> param) throws SQLException;
    void createPlanMeta(PlanMetaDto param) throws SQLException;
    void modifyPlanMeta(PlanMetaDto param) throws SQLException;
    void deletePlanMetaById(Long planId) throws SQLException;

    /**
     * PlanContent: 여행계획 세부정보
     * */
    List<PlanContentDto> listPlanContent(Map<String, Object> param) throws SQLException;
    void createPlanContent(PlanContentDto param) throws SQLException;
    void modifyPlanContent(PlanContentDto param) throws SQLException;
    void deletePlanContentById(Long planContentId) throws SQLException;
}
