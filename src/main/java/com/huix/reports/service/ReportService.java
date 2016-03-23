package com.huix.reports.service;

import com.huix.reports.domain.Report;
import com.huix.reports.repository.ReportRepository;
import com.huix.reports.web.rest.dto.ReportDTO;
import com.huix.reports.web.rest.mapper.ReportMapper;
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
 * Service Implementation for managing Report.
 */
@Service
@Transactional
public class ReportService {

    private final Logger log = LoggerFactory.getLogger(ReportService.class);
    
    @Inject
    private ReportRepository reportRepository;
    
    @Inject
    private ReportMapper reportMapper;
    
    /**
     * Save a report.
     * @return the persisted entity
     */
    public ReportDTO save(ReportDTO reportDTO) {
        log.debug("Request to save Report : {}", reportDTO);
        Report report = reportMapper.reportDTOToReport(reportDTO);
        report = reportRepository.save(report);
        ReportDTO result = reportMapper.reportToReportDTO(report);
        return result;
    }

    /**
     *  get all the reports.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Report> findAll(Pageable pageable) {
        log.debug("Request to get all Reports");
        Page<Report> result = reportRepository.findAll(pageable); 
        return result;
    }

    /**
     *  get one report by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ReportDTO findOne(Long id) {
        log.debug("Request to get Report : {}", id);
        Report report = reportRepository.findOne(id);
        ReportDTO reportDTO = reportMapper.reportToReportDTO(report);
        return reportDTO;
    }

    /**
     *  delete the  report by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Report : {}", id);
        reportRepository.delete(id);
    }
}
