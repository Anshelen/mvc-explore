<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <!-- Page Content -->
        <div class="container">

            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Сохранение объекта
                        <small>в session, request, view scope</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a>
                        </li>
                        <li class="active">Пример работы с scope</li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <c:url value="/writeCookie" var="writeCookie" />
            <c:url value="/readCookie" var="readCookie" />
            <c:url value="/readAllCookies" var="readAllCookies" />
            <!-- Content Row -->
            <div class="row">

                <!-- Sidebar Column -->
                <div class="col-md-3">
                    <div class="list-group">

                        <a href="/index.html" class="list-group-item">Home</a>
                        <a href="${writeCookie}" class="list-group-item">Записать cookie</a>
                        <a href="${readCookie}" class="list-group-item">Прочитать cookie</a>
                        <a href="${readAllCookies}" class="list-group-item">Прочитать все cookies</a>
                    </div>
                </div>
                <!-- Content Column -->
                <div class="col-md-9">
                    CookieValue: <br />
                    <b> ${cookieValueObj} </b> <br/> <br/>
                    CookieValue in response: <br/>
                    <b>Cookie value ${cookie.cookieName.value} </b>  <br/>
                    <b>Cookie name: ${cookie.cookieName.name} </b> <br/>
                    <b>Cookie: ${cookie.cookieName} </b>
                    <br/>
                </div>
            </div>

            <!-- /.row -->

            <hr>

        </div>
        <!-- /.container -->
    </jsp:body>
</page:template>