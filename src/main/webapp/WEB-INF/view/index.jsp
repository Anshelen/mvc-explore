<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>

  <jsp:attribute name="title">MVC with HTML5, AngularJS and Bootstrap</jsp:attribute>

  <jsp:body>

    <header id="myCarousel" class="carousel slide">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class=""></li>
        <li data-target="#myCarousel" data-slide-to="1" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>

      <!-- Wrapper for slides -->
      <div class="carousel-inner">
        <div class="item">
          <div class="fill" style="background-image:url('${pageContext.request.contextPath}/resources/images/indexImage1.png');"></div>
          <div class="carousel-caption">
            <h2>1</h2>
          </div>
        </div>
        <div class="item active">
          <div class="fill" style="background-image:url('${pageContext.request.contextPath}/resources/images/indexImage1.png');"></div>
          <div class="carousel-caption">
            <h2>2</h2>
          </div>
        </div>
        <div class="item">
          <div class="fill" style="background-image:url('${pageContext.request.contextPath}/resources/images/indexImage1.png');"></div>
          <div class="carousel-caption">
            <h2>3</h2>
          </div>
        </div>
      </div>

      <!-- Controls -->
      <a class="left carousel-control" href="#myCarousel" data-slide="prev">
        <span class="icon-prev"></span>
      </a>
      <a class="right carousel-control" href="#myCarousel" data-slide="next">
        <span class="icon-next"></span>
      </a>
    </header>
    <!-- Page Content -->
    <div class="container">

      <!-- Marketing Icons Section -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header">
            <spring:message code="content.introMsg"/>
          </h1>
        </div>
        <div class="col-md-4">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h4><i class="fa fa-fw fa-check"></i> Bootstrap v3.2.0</h4>
            </div>
            <div class="panel-body">
              <p>Предназначен для всех, во всем мире.
                Bootstrap - интуитивно простой и в тоже время мощный интерфейсный фрейморк, повышающий скорость и облегчающий разработку web-приложений.
              </p>
              <a href="#" class="btn btn-default">Подробнее</a>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h4><i class="fa fa-fw fa-gift"></i>AngularJS</h4>
            </div>
            <div class="panel-body">
              <p>AngularJS — JavaScript-фреймворк с открытым исходным кодом. Предназначен для разработки одностраничных приложений. Его цель — расширение браузерных MVC приложений, упрощение тестирования и разработки.</p>
              <a href="#" class="btn btn-default">Подробнее</a>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h4><i class="fa fa-fw fa-compass"></i>HTML5</h4>
            </div>
            <div class="panel-body">
              <p>В HTML5 реализовано множество новых синтаксических особенностей. Например, элементы video, audio и canvas, а также возможность использования SVG и математических формул.</p>
              <a href="#" class="btn btn-default">Подробнее</a>
            </div>
          </div>
        </div>
      </div>
      <!-- /.row -->

      <c:url value="/file.html" var="file"/>
      <c:url value="/email.html" var="email"/>
      <c:url value="/orm.html" var="orm" />
      <c:url value="/runtimeException.html" var="runtimeException" />
      <c:url value="/security.html" var="security" />
      <!-- Portfolio Section -->
      <div class="row">
        <div class="col-lg-12">
          <h2 class="page-header">Здесь применяется</h2>
        </div>
        <div class="col-md-4 col-sm-6">
          <a href="${file}">
            <img class="img-responsive img-portfolio img-hover" src="${pageContext.request.contextPath}/resources/images/PDF Excel.png" alt="">
          </a>
        </div>
        <div class="col-md-4 col-sm-6">
          <a href="${orm}">
            <img class="img-responsive img-portfolio img-hover" src="${pageContext.request.contextPath}/resources/images/spring hibernate.png" alt="">
          </a>
        </div>
        <div class="col-md-4 col-sm-6">
          <a href="${email}">
            <img class="img-responsive img-portfolio img-hover" src="${pageContext.request.contextPath}/resources/images/Java Api Mail.png" alt="">
          </a>
        </div>
        <div class="col-md-4 col-sm-6">
          <a href="portfolio-item.html">
            <img class="img-responsive img-portfolio img-hover" src="${pageContext.request.contextPath}/resources/images/angularjs.jpg" alt="">
          </a>
        </div>
        <div class="col-md-4 col-sm-6">
          <a href="${runtimeException}">
            <img class="img-responsive img-portfolio img-hover" src="${pageContext.request.contextPath}/resources/images/java-exception-handling.png" alt="">
          </a>
        </div>
        <div class="col-md-4 col-sm-6">
          <a href="${security}">
            <img class="img-responsive img-portfolio img-hover" src="${pageContext.request.contextPath}/resources/images/Srping_Security.png" alt="">
          </a>
        </div>
      </div>
      <!-- /.row -->

      <!-- Features Section -->
      <div class="row">
        <div class="col-lg-12">
          <h2 class="page-header">Spring MVC & Bootstrap</h2>
        </div>
        <div class="col-md-6">
          <p>В учебном шаблоне используется:</p>
          <ul>
            <li><strong>Bootstrap v3.2.0</strong>
            </li>
            <li>jQuery v1.11.0</li>
            <li>Font Awesome v4.1.0</li>
            <li>Spring MVC 4</li>
            <li>AngularJS</li>
          </ul>
          <p>Смотрите примеры в действии, а код и описание читайте на <a href="http://javastudy.ru">javastudy.ru</a></p>
        </div>
        <div class="col-md-6">
          <img class="img-responsive" src="${pageContext.request.contextPath}/resources/images/springmvc angular.jpg" alt="">
        </div>
      </div>
      <!-- /.row -->

      <hr>

      <!-- Call to Action Section -->
      <div class="well">
        <div class="row">
          <div class="col-md-8">
            <p>Демонстрация работы Interceptor в Spring MVC. Смотрите консоль в IDE</p>
          </div>
          <div class="col-md-4">
            <a class="btn btn-lg btn-default btn-block" href="/interceptorCall/subLevel">Вызов перехватчика</a>
          </div>
        </div>
      </div>

      <hr>


    </div>
    <!-- /.container -->

    <!-- Script to Activate the Carousel -->
    <script>
      $('.carousel').carousel({
        interval: 5000 //changes the speed
      })
    </script>
  </jsp:body>

</page:template>
