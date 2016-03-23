package com.huix.reports.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * Definición y configuración de los reportes
 */
@ApiModel(description = ""
    + "Definición y configuración de los reportes")
@Entity
@Table(name = "report")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Report extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Nombre del Reporte
     */
    @ApiModelProperty(value = ""
        + "Nombre del Reporte")
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    
    /**
     * Descripción del contenido del reporte
     */
    @ApiModelProperty(value = ""
        + "Descripción del contenido del reporte")
    @NotNull
    @Size(max = 2000)
    @Column(name = "description", length = 2000, nullable = false)
    private String description;
    
    /**
     * Icono que mostrara en el front el reportebasado en awesome icons y/o
     * ionic icons
     */
    @ApiModelProperty(value = ""
        + "Icono que mostrara en el front el reportebasado en awesome icons "
        + "y/o ionic icons")
    @Column(name = "icon")
    private String icon;
    
    /**
     * Color del panel basado en bootstrap colors
     */
    @ApiModelProperty(value = ""
        + "Color del panel basado en bootstrap colors")
    @Column(name = "color")
    private String color;
    
    /**
     * Bandera que indica si se encuentraactivo o inactivo el reporte,esto
     * incide en el despliegue del reporte
     */
    @ApiModelProperty(value = ""
        + "Bandera que indica si se encuentraactivo o inactivo el reporte,esto "
        + "incide en el despliegue del reporte")
    @NotNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
    
    @ManyToOne
    @JoinColumn(name = "db_id")
    private Db db;

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

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getEnabled() {
        return enabled;
    }
    
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Db getDb() {
        return db;
    }

    public void setDb(Db db) {
        this.db = db;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Report report = (Report) o;
        if(report.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, report.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Report{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", description='" + description + "'" +
            ", icon='" + icon + "'" +
            ", color='" + color + "'" +
            ", enabled='" + enabled + "'" +
            '}';
    }
}
