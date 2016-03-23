package com.huix.reports.repository;

import com.huix.reports.domain.Db;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Db entity.
 */
public interface DbRepository extends JpaRepository<Db,Long> {

}
