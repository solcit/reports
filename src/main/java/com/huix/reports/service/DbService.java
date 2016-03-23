package com.huix.reports.service;

import com.huix.reports.domain.Db;
import com.huix.reports.repository.DbRepository;
import com.huix.reports.web.rest.dto.DbDTO;
import com.huix.reports.web.rest.mapper.DbMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Db.
 */
@Service
@Transactional
public class DbService {

    private final Logger log = LoggerFactory.getLogger(DbService.class);
    
    @Inject
    private DbRepository dbRepository;
    
    @Inject
    private DbMapper dbMapper;
    
    /**
     * Save a db.
     * @return the persisted entity
     */
    public DbDTO save(DbDTO dbDTO) {
        log.debug("Request to save Db : {}", dbDTO);
        Db db = dbMapper.dbDTOToDb(dbDTO);
        db = dbRepository.save(db);
        DbDTO result = dbMapper.dbToDbDTO(db);
        return result;
    }

    /**
     *  get all the dbs.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Db> findAll(Pageable pageable) {
        log.debug("Request to get all Dbs");
        Page<Db> result = dbRepository.findAll(pageable); 
        return result;
    }

    /**
     *  get one db by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public DbDTO findOne(Long id) {
        log.debug("Request to get Db : {}", id);
        Db db = dbRepository.findOne(id);
        DbDTO dbDTO = dbMapper.dbToDbDTO(db);
        return dbDTO;
    }

    /**
     *  delete the  db by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Db : {}", id);
        dbRepository.delete(id);
    }
}
