package com.huix.reports.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * Fuente de datos disponibles para la consultade información de los reportes
 */
@ApiModel(description = ""
    + "Fuente de datos disponibles para la consultade información de los "
    + "reportes")
@Entity
@Table(name = "db")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Db extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Nombre de la Base de datos
     */
    @ApiModelProperty(value = ""
        + "Nombre de la Base de datos")
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    
    /**
     * Nombre del usuario de la base de datos
     */
    @ApiModelProperty(value = ""
        + "Nombre del usuario de la base de datos")
    @NotNull
    @Column(name = "user_name", nullable = false)
    private String userName;
    
    /**
     * Contraseña del usuario de la base de datos,tener cuidado con estos datos
     * ya que las contraseñasse almacenan en claro en base de datos,como buena
     * practica se aconseja asegurarseque el usuario es de solo lectura
     */
    @ApiModelProperty(value = ""
        + "Contraseña del usuario de la base de datos,tener cuidado con estos "
        + "datos ya que las contraseñasse almacenan en claro en base de "
        + "datos,como buena practica se aconseja asegurarseque el usuario es "
        + "de solo lectura")
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;
    
    /**
     * IP o nombre de dominio del servidordonde se encuentra la base de datos
     */
    @ApiModelProperty(value = ""
        + "IP o nombre de dominio del servidordonde se encuentra la base de "
        + "datos")
    @NotNull
    @Column(name = "server", nullable = false)
    private String server;
    
    /**
     * Puerto de la base de datos
     */
    @ApiModelProperty(value = ""
        + "Puerto de la base de datos")
    @Column(name = "port")
    private String port;
    
    /**
     * Bandera que indica si la base de datos estaactiva o inactiva para ser
     * usada en los reportes
     */
    @ApiModelProperty(value = ""
        + "Bandera que indica si la base de datos estaactiva o inactiva para "
        + "ser usada en los reportes")
    @NotNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
    
    @OneToMany(mappedBy = "db")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Report> reports = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getServer() {
        return server;
    }
    
    public void setServer(String server) {
        this.server = server;
    }

    public String getPort() {
        return port;
    }
    
    public void setPort(String port) {
        this.port = port;
    }

    public Boolean getEnabled() {
        return enabled;
    }
    
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Db db = (Db) o;
        if(db.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, db.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Db{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", userName='" + userName + "'" +
            ", password='" + password + "'" +
            ", server='" + server + "'" +
            ", port='" + port + "'" +
            ", enabled='" + enabled + "'" +
            '}';
    }
}
