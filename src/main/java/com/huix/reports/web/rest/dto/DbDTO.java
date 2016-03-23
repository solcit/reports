package com.huix.reports.web.rest.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Db entity.
 */
public class DbDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;


    @NotNull
    private String userName;


    @NotNull
    private String password;


    @NotNull
    private String server;


    private String port;


    @NotNull
    private Boolean enabled;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DbDTO dbDTO = (DbDTO) o;

        if ( ! Objects.equals(id, dbDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DbDTO{" +
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
