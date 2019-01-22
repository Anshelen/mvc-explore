package ru.javastudy.mvc.core.rest.model;

import java.util.List;

/**
 * Created for JavaStudy.ru on 05.03.2016.
 */
public class DBLogsJSON {

    private List<DBLogJSON> logList;

    public List<DBLogJSON> getLogList() {
        return logList;
    }

    public void setLogList(List<DBLogJSON> logList) {
        this.logList = logList;
    }
}