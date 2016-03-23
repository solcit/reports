package com.huix.reports.web.rest;

import com.huix.reports.Application;
import com.huix.reports.domain.Db;
import com.huix.reports.repository.DbRepository;
import com.huix.reports.service.DbService;
import com.huix.reports.web.rest.dto.DbDTO;
import com.huix.reports.web.rest.mapper.DbMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the DbResource REST controller.
 *
 * @see DbResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DbResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";
    private static final String DEFAULT_USER_NAME = "AAAAA";
    private static final String UPDATED_USER_NAME = "BBBBB";
    private static final String DEFAULT_PASSWORD = "AAAAA";
    private static final String UPDATED_PASSWORD = "BBBBB";
    private static final String DEFAULT_SERVER = "AAAAA";
    private static final String UPDATED_SERVER = "BBBBB";
    private static final String DEFAULT_PORT = "AAAAA";
    private static final String UPDATED_PORT = "BBBBB";

    private static final Boolean DEFAULT_ENABLED = false;
    private static final Boolean UPDATED_ENABLED = true;

    @Inject
    private DbRepository dbRepository;

    @Inject
    private DbMapper dbMapper;

    @Inject
    private DbService dbService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDbMockMvc;

    private Db db;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DbResource dbResource = new DbResource();
        ReflectionTestUtils.setField(dbResource, "dbService", dbService);
        ReflectionTestUtils.setField(dbResource, "dbMapper", dbMapper);
        this.restDbMockMvc = MockMvcBuilders.standaloneSetup(dbResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        db = new Db();
        db.setName(DEFAULT_NAME);
        db.setUserName(DEFAULT_USER_NAME);
        db.setPassword(DEFAULT_PASSWORD);
        db.setServer(DEFAULT_SERVER);
        db.setPort(DEFAULT_PORT);
        db.setEnabled(DEFAULT_ENABLED);
    }

    @Test
    @Transactional
    public void createDb() throws Exception {
        int databaseSizeBeforeCreate = dbRepository.findAll().size();

        // Create the Db
        DbDTO dbDTO = dbMapper.dbToDbDTO(db);

        restDbMockMvc.perform(post("/api/dbs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dbDTO)))
                .andExpect(status().isCreated());

        // Validate the Db in the database
        List<Db> dbs = dbRepository.findAll();
        assertThat(dbs).hasSize(databaseSizeBeforeCreate + 1);
        Db testDb = dbs.get(dbs.size() - 1);
        assertThat(testDb.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDb.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testDb.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testDb.getServer()).isEqualTo(DEFAULT_SERVER);
        assertThat(testDb.getPort()).isEqualTo(DEFAULT_PORT);
        assertThat(testDb.getEnabled()).isEqualTo(DEFAULT_ENABLED);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = dbRepository.findAll().size();
        // set the field null
        db.setName(null);

        // Create the Db, which fails.
        DbDTO dbDTO = dbMapper.dbToDbDTO(db);

        restDbMockMvc.perform(post("/api/dbs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dbDTO)))
                .andExpect(status().isBadRequest());

        List<Db> dbs = dbRepository.findAll();
        assertThat(dbs).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUserNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = dbRepository.findAll().size();
        // set the field null
        db.setUserName(null);

        // Create the Db, which fails.
        DbDTO dbDTO = dbMapper.dbToDbDTO(db);

        restDbMockMvc.perform(post("/api/dbs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dbDTO)))
                .andExpect(status().isBadRequest());

        List<Db> dbs = dbRepository.findAll();
        assertThat(dbs).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = dbRepository.findAll().size();
        // set the field null
        db.setPassword(null);

        // Create the Db, which fails.
        DbDTO dbDTO = dbMapper.dbToDbDTO(db);

        restDbMockMvc.perform(post("/api/dbs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dbDTO)))
                .andExpect(status().isBadRequest());

        List<Db> dbs = dbRepository.findAll();
        assertThat(dbs).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkServerIsRequired() throws Exception {
        int databaseSizeBeforeTest = dbRepository.findAll().size();
        // set the field null
        db.setServer(null);

        // Create the Db, which fails.
        DbDTO dbDTO = dbMapper.dbToDbDTO(db);

        restDbMockMvc.perform(post("/api/dbs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dbDTO)))
                .andExpect(status().isBadRequest());

        List<Db> dbs = dbRepository.findAll();
        assertThat(dbs).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnabledIsRequired() throws Exception {
        int databaseSizeBeforeTest = dbRepository.findAll().size();
        // set the field null
        db.setEnabled(null);

        // Create the Db, which fails.
        DbDTO dbDTO = dbMapper.dbToDbDTO(db);

        restDbMockMvc.perform(post("/api/dbs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dbDTO)))
                .andExpect(status().isBadRequest());

        List<Db> dbs = dbRepository.findAll();
        assertThat(dbs).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDbs() throws Exception {
        // Initialize the database
        dbRepository.saveAndFlush(db);

        // Get all the dbs
        restDbMockMvc.perform(get("/api/dbs?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(db.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
                .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
                .andExpect(jsonPath("$.[*].server").value(hasItem(DEFAULT_SERVER.toString())))
                .andExpect(jsonPath("$.[*].port").value(hasItem(DEFAULT_PORT.toString())))
                .andExpect(jsonPath("$.[*].enabled").value(hasItem(DEFAULT_ENABLED.booleanValue())));
    }

    @Test
    @Transactional
    public void getDb() throws Exception {
        // Initialize the database
        dbRepository.saveAndFlush(db);

        // Get the db
        restDbMockMvc.perform(get("/api/dbs/{id}", db.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(db.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.server").value(DEFAULT_SERVER.toString()))
            .andExpect(jsonPath("$.port").value(DEFAULT_PORT.toString()))
            .andExpect(jsonPath("$.enabled").value(DEFAULT_ENABLED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDb() throws Exception {
        // Get the db
        restDbMockMvc.perform(get("/api/dbs/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDb() throws Exception {
        // Initialize the database
        dbRepository.saveAndFlush(db);

		int databaseSizeBeforeUpdate = dbRepository.findAll().size();

        // Update the db
        db.setName(UPDATED_NAME);
        db.setUserName(UPDATED_USER_NAME);
        db.setPassword(UPDATED_PASSWORD);
        db.setServer(UPDATED_SERVER);
        db.setPort(UPDATED_PORT);
        db.setEnabled(UPDATED_ENABLED);
        DbDTO dbDTO = dbMapper.dbToDbDTO(db);

        restDbMockMvc.perform(put("/api/dbs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dbDTO)))
                .andExpect(status().isOk());

        // Validate the Db in the database
        List<Db> dbs = dbRepository.findAll();
        assertThat(dbs).hasSize(databaseSizeBeforeUpdate);
        Db testDb = dbs.get(dbs.size() - 1);
        assertThat(testDb.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDb.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testDb.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testDb.getServer()).isEqualTo(UPDATED_SERVER);
        assertThat(testDb.getPort()).isEqualTo(UPDATED_PORT);
        assertThat(testDb.getEnabled()).isEqualTo(UPDATED_ENABLED);
    }

    @Test
    @Transactional
    public void deleteDb() throws Exception {
        // Initialize the database
        dbRepository.saveAndFlush(db);

		int databaseSizeBeforeDelete = dbRepository.findAll().size();

        // Get the db
        restDbMockMvc.perform(delete("/api/dbs/{id}", db.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Db> dbs = dbRepository.findAll();
        assertThat(dbs).hasSize(databaseSizeBeforeDelete - 1);
    }
}
