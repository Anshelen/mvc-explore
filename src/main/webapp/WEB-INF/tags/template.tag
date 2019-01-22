<!DOCTYPE html>
<%@tag description="Template Site tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@attribute name="title" fragment="true" %>
<html>
<head>
    <title><jsp:invoke fragment="title"/></title>

    <!-- Bootstrap Core CSS -->
    <spring:url value="/resources/css/bootstrap.css" var="bootstrap"/>
    <link href="${bootstrap}" rel="stylesheet" />

    <!-- Custom CSS -->
    <spring:url value="/resources/css/modern-business.css" var="startertemplate"/>
    <link href="${startertemplate}" rel="stylesheet" />

    <!-- Custom Fonts -->
    <spring:url value="/resources/font-awesome/css/font-awesome.min.css" var="fontawesome"/>
    <link href="${fontawesome}" rel="stylesheet" />

    <!-- jQuery -->
    <spring:url value="/resources/js/jquery-2.1.4.min.js" var="jqueryjs"/>
    <script src="${jqueryjs}"></script>

    <!-- Bootstrap Core JavaScript -->
    <spring:url value="/resources/js/bootstrap.min.js" var="js"/>
    <script src="${js}"></script>
</head>

<body>

<c:url value="/file.html" var="file"/>
<c:url value="/jdbc.html" var="jdbc"/>
<c:url value="/email.html" var="email" />
<c:url value="/rest.html" var="rest" />
<c:url value="/orm.html" var="orm" />
<c:url value="/runtimeException.html" var="runtimeException" />
<c:url value="/jstl.html" var="jstl" />
<c:url value="/redirectExample" var="redirectExample" />
<c:url value="/scope.html" var="scope" />
<c:url value="/cookie.html" var="cookieView" />
<c:url value="/security.html" var="security" />

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">Start Bootstrap</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var="isUSer"/>


                <c:if test="${not isUSer}">
                    <li style="padding-top: 15px; padding-bottom: 15px; color: red">
                        <c:if test="${empty param.error}">
                            Вы не вошли в приложение
                        </c:if>
                    </li>
                    <li> <a style="color: Green;" href="<c:url value="/login.html"/>">Login</a> </li>
                </c:if>



                <c:if test="${isUSer}">
                    <li style="padding-top: 15px; padding-bottom: 15px; color: green">
                        Вы вошли как:
                        <security:authentication property="principal.username"/> с ролью:
                        <b><security:authentication property="principal.authorities"/></b>

                    </li>
                    <li> <a style="color: red;" href="<c:url value="/j_spring_security_logout"/>">Logout</a> </li>
                </c:if>


                <c:url value="/about.html" var="about"/>
                <li><a href="${about}">About</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Tutorial<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="${file}">Загрузка файла PDF и Excel</a>
                        </li>
                        <li>
                            <a href="${jdbc}">JDBC c JDBCTemplates</a>
                        </li>
                        <li>
                            <a href="${email}">Работа с Java Mail API</a>
                        </li>
                        <li>
                            <a href="${rest}">Rest Services</a>
                        </li>
                        <li>
                            <a href="${orm}">Spring MVC и Hibernate 5</a>
                        </li>
                        <li>
                            <a href="${runtimeException}">Runtime Exception</a>
                        </li>
                        <li>
                            <a href="${jstl}">JSTL Example</a>
                        </li>
                        <li>
                            <a href="${redirectExample}">Redirect Example</a>
                        </li>
                        <li>
                            <a href="${scope}">Session Object Example</a>
                        </li>
                        <li>
                            <a href="${cookieView}">Работа с cookie</a>
                        </li>
                        <li>
                            <a href="${security}">Spring Security</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>


<jsp:doBody/>


<div class="container">
    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Copyright © Javastudy.ru 2016</p>
            </div>
        </div>
    </footer>
</div>

</body>

</html>