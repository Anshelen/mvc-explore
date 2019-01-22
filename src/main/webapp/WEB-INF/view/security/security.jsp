<!DOCTYPE html>
<%@ page language= "java" contentType= "text/html; charset=UTF-8" pageEncoding= "UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "spring" uri= "http://www.springframework.org/tags"%>
<%@ taglib prefix= "security" uri= "http://www.springframework.org/security/tags" %>
<%@ taglib prefix= "page" tagdir= "/WEB-INF/tags" %>

<page:template>
    <jsp:body>

        <c:url value= "/adminOrSuperUserCanCall" var= "adminOrSuperUserCanCall" />
        <c:url value= "/userOrAdminCanCallSpEL" var= "userOrAdminCanCallSpEL" />
        <c:url value= "/adminMethodSecured" var= "adminMethodSecured" />
        <c:url value= "/security/admin.html" var= "admin" />


        <!-- Page Content -->
        <div class= "container">

            <!-- Page Heading/Breadcrumbs -->
            <div class= "row">
                <div class= "col-lg-12">
                    <h1 class= "page-header">Security в Spring
                        <small>защита приложения</small>
                    </h1>
                    <ol class= "breadcrumb">
                        <li><a href= "index.html">Home</a>
                        </li>
                        <li class= "active">Security sidebar page</li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <!-- Content Row -->
            <div class= "row">
                <!-- Sidebar Column -->
                <div class= "col-md-3">
                    <div class= "list-group">

                        <a href= "index.html" class= "list-group-item">Home</a>
                        <a href= "${adminOrSuperUserCanCall}" class= "list-group-item">Админ или Супер юзер</a>
                        <a href= "${userOrAdminCanCallSpEL}" class= "list-group-item">Админ или юзер (SpEL)</a>
                        <a href= "${adminMethodSecured}" class= "list-group-item">Только админ</a>
                        <a href= "${admin}" class= "list-group-item">admin.jsp</a>
                    </div>
                </div>

                <!-- Content Column -->
                <div class= "col-md-9">
                    <p> После этого текста идет область контента только для зарегестрированных пользователей:
                        <security:authorize access= "hasAnyRole('ROLE_ADMIN','ROLE_USER', 'ROLE_SUPER_USER')"></p>
                    <b>Вы вошли как:</b> <security:authentication property= "principal.username"/> с ролью: <b><security:authentication property= "principal.authorities"/> </b>
                    <br />
                    <span style= "color: #568C00;"><security:authentication property= "principal.username"/></span>
                    <a style= "color: #568C00!important;" href= "<c:url value= "/j_spring_security_logout"/>">Logout</a>
                    </security:authorize>
                    <p>Конец области контента для зарегистрированных пользователей.</p>
                    <br />
                    <h3>Spring Security examples</h3>
                    <p><a href= "${adminOrSuperUserCanCall}">@RolesAllowed(value={"ROLE_SUPER_USER","ROLE_ADMIN"}) JSR Security</a></p> <br />
                    <p> <a href= "${userOrAdminCanCallSpEL}" >@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_SUPER_USER') || hasRole('ROLE_USER')") SpEL Spring Security</a> </p><br />
                    <p> <a href= "${adminMethodSecured}">@Secured(value={"ROLE_ADMIN"}) JSR Security</a> </p> <br />
                    <p> <a href= "${admin}">Только для Admin role на страницу admin.jsp</a> <br />
                </div>
            </div>
            <!-- /.row -->

            <hr>

        </div>
        <!-- /.container -->

    </jsp:body>
</page:template>