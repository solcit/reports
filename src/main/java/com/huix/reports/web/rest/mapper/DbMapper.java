package com.huix.reports.web.rest.mapper;

import com.huix.reports.domain.*;
import com.huix.reports.web.rest.dto.DbDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Db and its DTO DbDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DbMapper {

    DbDTO dbToDbDTO(Db db);

    @Mapping(target = "reports", ignore = true)
    Db dbDTOToDb(DbDTO dbDTO);
}
