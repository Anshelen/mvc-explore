<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <c:url value="/jstlReturnUsers" var="jstlReturnUsers" />
        <c:url value="/jstlUser" var="jstlUser" />
        <c:url value="/jstlHTML" var="jstlHTML" />

        <!-- Page Content -->
        <div class="container">

            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">JSTL в Spring
                        <small>JSTL example</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a>
                        </li>
                        <li class="active">Java Standard Tag Library</li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <!-- Content Row -->
            <div class="row">
                <!-- Sidebar Column -->
                <div class="col-md-3">
                    <div class="list-group">

                        <a href="index.html" class="list-group-item">Home</a>
                        <a href="${jstlReturnUsers}" class="list-group-item">get all users</a>
                        <a href="${jstlUser}" class="list-group-item">Get one user</a>
                        <a href="${jstlHTML}" class="list-group-item">Get HTML</a>
                    </div>
                </div>
                <!-- Content Column -->
                <div class="col-md-9">
                    <c:if test="${not empty resultObject}">
                        Result:
                        <p>${resultObject}</p>
                    </c:if>

                    <c:if test="${not empty resultObjectList}">
                        <b>Result List:</b>
                        <table>
                            <c:forEach var="userVar" items="#{resultObjectList}">
                                <tr>
                                    <td><b>idUser: </b></td>
                                    <td><c:out value="${userVar.idUser}"/></td>
                                </tr>
                                <tr>
                                    <td><b>username: </b></td>
                                    <td><c:out value="${userVar.username}"/></td>
                                </tr>
                                <tr>
                                    <td><b>password: </b></td>
                                    <td><c:out value="${userVar.password}"/></td>
                                </tr>
                                <tr>
                                    <td><b>enabled: </b></td>
                                    <td><c:out value="${userVar.enabled}"/></td>
                                </tr>
                                <tr><td><b style="color:green">End user with id:${userVar.idUser}</b></td></tr>
                                <tr><td> <br /></td></tr>
                            </c:forEach>
                        </table>
                    </c:if>

                    <c:if test="${not empty resultHTML}">
                        <p>With <b>escapeXml='false'</b>: <c:out value="${resultHTML}" escapeXml="false"/></p>

                        <p>With <b>escapeXml='true'</b> : <c:out value="${resultHTML}" escapeXml="true"/></p>

                    </c:if>



                    <br />
                    <c:set var="dateVar" value="<%=new java.util.Date()%>"/>
                    Date format: <span><fmt:formatDate value="${dateVar}" pattern="dd MMM yyyy"/> </span>
                    <br />
                    <c:set var="number" value="666777999" />
                    Number Format: <b><fmt:formatNumber type="number" groupingUsed="true" value="${number}"/></b>
                    <br/>

                    <br />
                    <c:catch var="exceptionVar">
                        ${someNotExistedMethod()}
                    </c:catch>

                    <b>
                        <c:if test="${not empty exceptionVar}">
                            <p style="color:red">Exception msg example: </p>${exceptionVar}
                        </c:if>
                    </b>
                </div>
            </div>
            <!-- /.row -->

            <hr>

        </div>
        <!-- /.container -->

    </jsp:body>
</page:template>