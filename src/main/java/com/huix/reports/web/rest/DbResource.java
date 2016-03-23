package com.huix.reports.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.huix.reports.domain.Db;
import com.huix.reports.service.DbService;
import com.huix.reports.web.rest.util.HeaderUtil;
import com.huix.reports.web.rest.util.PaginationUtil;
import com.huix.reports.web.rest.dto.DbDTO;
import com.huix.reports.web.rest.mapper.DbMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Db.
 */
@RestController
@RequestMapping("/api")
public class DbResource {

    private final Logger log = LoggerFactory.getLogger(DbResource.class);
        
    @Inject
    private DbService dbService;
    
    @Inject
    private DbMapper dbMapper;
    
    /**
     * POST  /dbs -> Create a new db.
     */
    @RequestMapping(value = "/dbs",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DbDTO> createDb(@Valid @RequestBody DbDTO dbDTO) throws URISyntaxException {
        log.debug("REST request to save Db : {}", dbDTO);
        if (dbDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("db", "idexists", "A new db cannot already have an ID")).body(null);
        }
        DbDTO result = dbService.save(dbDTO);
        return ResponseEntity.created(new URI("/api/dbs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("db", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dbs -> Updates an existing db.
     */
    @RequestMapping(value = "/dbs",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DbDTO> updateDb(@Valid @RequestBody DbDTO dbDTO) throws URISyntaxException {
        log.debug("REST request to update Db : {}", dbDTO);
        if (dbDTO.getId() == null) {
            return createDb(dbDTO);
        }
        DbDTO result = dbService.save(dbDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("db", dbDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dbs -> get all the dbs.
     */
    @RequestMapping(value = "/dbs",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<DbDTO>> getAllDbs(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Dbs");
        Page<Db> page = dbService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dbs");
        return new ResponseEntity<>(page.getContent().stream()
            .map(dbMapper::dbToDbDTO)
            .collect(Collectors.toCollection(LinkedList::new)), headers, HttpStatus.OK);
    }

    /**
     * GET  /dbs/:id -> get the "id" db.
     */
    @RequestMapping(value = "/dbs/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DbDTO> getDb(@PathVariable Long id) {
        log.debug("REST request to get Db : {}", id);
        DbDTO dbDTO = dbService.findOne(id);
        return Optional.ofNullable(dbDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /dbs/:id -> delete the "id" db.
     */
    @RequestMapping(value = "/dbs/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDb(@PathVariable Long id) {
        log.debug("REST request to delete Db : {}", id);
        dbService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("db", id.toString())).build();
    }
}
