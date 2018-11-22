<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <c:url value="/index.html" var="toIndex" />
        <c:url value="/ormFindAllUsers" var="ormFindAllUsers" />
        <c:url value="/queryFindByIdUser/4" var="queryFindByIdUser" />
        <c:url value="/ormUpdateUser/iduser/3/enabled/false" var="ormUpdateUser" />
        <c:url value="/ormDeleteUser/iduser/8" var="ormDeleteUser" />
        <c:url value="/ormInsertUser/username/newUserHere/password/NewPass/enabled/true" var="ormInsertUser" />


        <!-- Page Content -->
        <div class="container">

            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Hibernate в Spring
                        <small>Hibernate 5</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a>
                        </li>
                        <li class="active">Hibernate page</li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <!-- Content Row -->
            <div class="row">
                <!-- Sidebar Column -->
                <div class="col-md-3">
                    <div class="list-group">

                        <a href="${toIndex}" class="list-group-item">Home</a>
                        <a href="${ormFindAllUsers}" class="list-group-item">Find All Users</a>
                        <a href="${queryFindByIdUser}" class="list-group-item">Find User by id</a>
                        <a href="${ormUpdateUser}" class="list-group-item">Update User</a>
                        <a href="${ormDeleteUser}" class="list-group-item">Delete User</a>
                        <a href="${ormInsertUser}" class="list-group-item">Insert User</a>
                    </div>
                </div>
                <!-- Content Column -->
                <div class="col-md-9">
                    <c:if test="${not empty resultObject}">
                        Result:
                        <c:if test="${resultObject == 'true'}">
                            <font color="green"><b>${resultObject}</b></font>
                        </c:if>
                        <c:if test="${resultObject == 'false'}">
                            <font color="red"><b>${resultObject}</b></font>
                        </c:if>
                        <c:if test="${resultObject !='true' and resultObject != 'false'}">
                            <p>${resultObject}</p>
                        </c:if>
                    </c:if>
                </div>
            </div>
            <!-- /.row -->

            <hr>

        </div>
        <!-- /.container -->

    </jsp:body>
</page:template>