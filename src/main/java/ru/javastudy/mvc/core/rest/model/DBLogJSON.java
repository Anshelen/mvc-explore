package ru.javastudy.mvc.core.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created for JavaStudy.ru on 26.02.2016.
 */
@Entity //use for JPA. For JDBC you can clear this
@Table(name = "LOG")
public class DBLogJSON implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "IDLOG")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IDLOG;
    @Column(name = "LOGSTRING")
    private String LOGSTRING;

    public DBLogJSON() {
    }

    public DBLogJSON(int idLog, String logString) {
        this.IDLOG = idLog;
        this.LOGSTRING = logString;
    }

    public int getIDLOG() {
        return IDLOG;
    }

    public void setIDLOG(int iDLOG) {
        IDLOG = iDLOG;
    }

    public String getLOGSTRING() {
        return LOGSTRING;
    }

    public void setLOGSTRING(String lOGSTRING) {
        LOGSTRING = lOGSTRING;
    }
}
