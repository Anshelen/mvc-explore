package ru.javastudy.mvc.core.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created for JavaStudy.ru on 05.03.2016.
 */
@XmlRootElement(name = "LOGS")
public class DBLogsXML {

    private List<DBLogXML> logList;

    @XmlElement(name = "LOG")
    public List<DBLogXML> getLogList() {
        return logList;
    }

    public void setLogList(List<DBLogXML> logList) {
        this.logList = logList;
    }
}