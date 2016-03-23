package com.huix.reports.web.rest.mapper;

import com.huix.reports.domain.*;
import com.huix.reports.web.rest.dto.ReportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Report and its DTO ReportDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReportMapper {

    @Mapping(source = "db.id", target = "dbId")
    @Mapping(source = "db.name", target = "dbName")
    ReportDTO reportToReportDTO(Report report);

    @Mapping(source = "dbId", target = "db")
    Report reportDTOToReport(ReportDTO reportDTO);

    default Db dbFromId(Long id) {
        if (id == null) {
            return null;
        }
        Db db = new Db();
        db.setId(id);
        return db;
    }
}
