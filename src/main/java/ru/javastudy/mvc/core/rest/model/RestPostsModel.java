package ru.javastudy.mvc.core.rest.model;

/**
 * Created for JavaStudy.ru on 28.02.2016.
 */
public class RestPostsModel {

    private String userId;
    private String id;
    private String title;
    private String body;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
