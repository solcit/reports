package com.huix.reports.repository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huix.reports.domain.Db;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class DatasourceRepository {
	private static final Logger log = LoggerFactory.getLogger(DatasourceRepository.class);
	private Map<Long, HikariDataSource> dataSourceMap = new HashMap<Long, HikariDataSource>();

	@Inject
	private DbRepository dbRepository;
	
	public HikariDataSource save(Long id){
		log.info("Creating and adding datasource {}", id);
		Db db = dbRepository.findOne(id);
		HikariConfig config = new HikariConfig();
		config.setMaximumPoolSize(10);
	    config.setDataSourceClassName(com.mysql.jdbc.jdbc2.optional.MysqlDataSource.class.getName());
	    config.addDataSourceProperty("serverName", db.getServer());
	    config.addDataSourceProperty("port", db.getPort());
	    config.addDataSourceProperty("databaseName", db.getName());
	    config.addDataSourceProperty("user", db.getUserName());
	    config.addDataSourceProperty("password", db.getPassword());
	    this.dataSourceMap.put(id, new HikariDataSource(config));
	    
	    return this.dataSourceMap.get(id);
	}
	
	public HikariDataSource findOne(Long id){
		HikariDataSource dataSource = this.dataSourceMap.get(id);
		if (dataSource == null){
			dataSource = save(id);
		}
		return dataSource;
	}
	
	public void delete(Long id){
		HikariDataSource dataSource = this.dataSourceMap.remove(id);
		if (dataSource != null) {
			log.info("Cerrando DataSource {}", id);
			dataSource.close();
		} else {
			log.warn("No se encontró un DataSource con el id: {}", id);
		}
	}

}
