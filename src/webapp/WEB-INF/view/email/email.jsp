<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:attribute name="title">Javastudy.ru MVC_HTML5_Angular</jsp:attribute>

    <jsp:body>
        <!-- Page Content -->
        <div class="container">

            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Contact
                        <small>Java Mail API and Spring Mail</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a>
                        </li>
                        <li class="active">Contact</li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <!-- Content Row -->
            <div class="row">
                <!-- Contact Details Column -->
                <div class="col-md-4">
                    <c:if test="${pageContext.request.getParameter('resultSending') == 'true'}">
                        <h3 style="color:green">Email already send!</h3>
                    </c:if>
                    <c:if test="${pageContext.request.getParameter('resultSending') == 'false'}">
                        <h3 style="color:red">Email wasn't send!</h3>
                    </c:if>
                    <h3>Contact Details</h3>
                    <p>
                        3481 Melrose Place<br>Beverly Hills, CA 90210<br>
                    </p>
                    <p><i class="fa fa-phone"></i>
                        <abbr title="Phone">P</abbr>: (123) 456-7890</p>
                    <p><i class="fa fa-envelope-o"></i>
                        <abbr title="Email">E</abbr>: <a href="mailto:anshelen@yandex.ru">anshelen@yandex.ru</a>
                    </p>
                    <p><i class="fa fa-clock-o"></i>
                        <abbr title="Hours">H</abbr>: Monday - Friday: 9:00 AM to 5:00 PM</p>
                    <ul class="list-unstyled list-inline list-social-icons">
                        <li>
                            <a href="#"><i class="fa fa-facebook-square fa-2x"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-linkedin-square fa-2x"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-twitter-square fa-2x"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-google-plus-square fa-2x"></i></a>
                        </li>
                    </ul>
                </div>
            </div>
            <!-- /.row -->
            <c:url value="/email/send" var="send"/>
            <!-- Contact Form -->
            <!-- In order to set the email address and subject line for the contact form go to the bin/contact_me.php file. -->
            <div class="row">
                <div class="col-md-8">
                    <h3>Send us a Message</h3>
                    <form:form name="sentMessage" id="contactForm" action="${send}"
                               modelAttribute="emailModel" method="post" novalidate="true" >
                        <div class="control-group form-group">
                            <div class="controls">
                                <label>Full Name:</label>
                                <input type="text" class="form-control" id="name" name="name"
                                       required data-validation-required-message="Please enter your name.">
                                <p class="help-block"></p>
                            </div>
                        </div>
                        <div class="control-group form-group">
                            <div class="controls">
                                <label>Phone Number:</label>
                                <input type="tel" class="form-control" id="phone" name="phoneNumber"
                                       required data-validation-required-message="Please enter your phone number.">
                            </div>
                        </div>
                        <div class="control-group form-group">
                            <div class="controls">
                                <label>Email Address:</label>
                                <input type="email" class="form-control" id="email" name="email"
                                       required data-validation-required-message="Please enter your email address.">
                            </div>
                        </div>
                        <div class="control-group form-group">
                            <div class="controls">
                                <label>Message:</label>
                                <textarea rows="10" cols="100" class="form-control" id="message" name="message"
                                          required data-validation-required-message="Please enter your message" maxlength="999" style="resize:none"></textarea>
                            </div>
                        </div>
                        <div id="success"></div>
                        <!-- For success/fail messages -->
                        <button type="submit" class="btn btn-primary">Send Message</button>
                    </form:form>
                </div>

            </div>
            <!-- /.row -->

            <hr>
        </div>
        <!-- /.container -->
    </jsp:body>

</page:template>