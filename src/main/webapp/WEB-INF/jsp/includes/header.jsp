<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Suspy!</title>

    <!-- bower:css -->
    <link rel="stylesheet" href="/public/lib/bower_components/bootstrap/dist/css/bootstrap.css" />
    <link rel="stylesheet" href="/public/lib/bower_components/bootstrap-material-design/dist/css/material.css" />
    <link rel="stylesheet" href="/public/lib/bower_components/bootstrap-material-design/dist/css/ripples.css" />
    <link rel="stylesheet" href="/public/lib/bower_components/datatables/media/css/jquery.dataTables.css" />
    <link rel="stylesheet" href="/public/lib/bower_components/angular-hotkeys/build/hotkeys.min.css" />
    <!-- endbower -->

    <link href="/public/lib/css/style.css" rel="stylesheet">
    <!--<link href="/public/lib/css/commentsStyle.css" rel="stylesheet">-->

    <!-- bower:js -->
    <script src="/public/lib/bower_components/modernizr/modernizr.js"></script>
    <script src="/public/lib/bower_components/jquery/dist/jquery.js"></script>
    <script src="/public/lib/bower_components/angular/angular.js"></script>
    <script src="/public/lib/bower_components/bootstrap/dist/js/bootstrap.js"></script>
    <script src="/public/lib/bower_components/bootstrap-material-design/dist/js/material.js"></script>
    <script src="/public/lib/bower_components/bootstrap-material-design/dist/js/ripples.js"></script>
    <script src="/public/lib/bower_components/datatables/media/js/jquery.dataTables.js"></script>
    <script src="/public/lib/bower_components/angular-scroll/angular-scroll.js"></script>
    <script src="/public/lib/bower_components/angular-route/angular-route.js"></script>
    <script src="/public/lib/bower_components/angular-bootstrap/ui-bootstrap-tpls.js"></script>
    <script src="/public/lib/bower_components/angular-hotkeys/build/hotkeys.min.js"></script>
    <script src="/public/lib/bower_components/angular-resource/angular-resource.js"></script>
    <!-- endbower -->
    
    <script type="text/javascript" src="https://js.stripe.com/v2/"></script>
    <script src="https://checkout.stripe.com/checkout.js"></script>
    <script src="/public/lib/js/app.js"></script>
    <script src="/public/lib/js/controllers.js"></script>
    <script src="/public/lib/js/eventController.js"></script>
    <script src="/public/lib/js/placeController.js"></script>
    <script src="/public/lib/js/teamController.js"></script>
    <script src="/public/lib/js/scripts.js"></script>
</head>

<body ng-app="suspyApp" ng-controller="MainController">

    <div class="container">
        <div class="row" id="navbar">
            <nav class="navbar navbar-material-lightblue navbar-fixed-top" role="navigation">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <!--<a class="navbar-brand" href="/">Suspy</a>-->
                        <c:if test="${requestScope['javax.servlet.forward.request_uri']!='/'}"><img src="/public/lib/assets/logo.svg" height="60px"></c:if>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                        <c:if test="${requestScope['javax.servlet.forward.request_uri']!='/'}">
                            <form:form
                                    modelAttribute="homePageSearch"
                                    role="search"
                                    method="post"
                                    class="navbar-form navbar-left"
                                    style="margin: 0 0 0 0; color:white;"
                                    action="/">
                                <!--<div class="form-group">-->

                                    <div class="col-lg-4" style="margin-top:12px;">
                                        <form:input path="searchText" type="text" class="form-control" placeholder="Search" />
                                    </div>
                                    <div class="radio radio-material-cyan" >
                                        <label style="padding-left: 32px; font-size:12px;font-weight: inherit;">
                                            <form:radiobutton path="searchTarget" name="headerSearchRadio" value="events" checked="true"/>
                                            Events
                                        </label>
                                    </div>
                                    <div class="radio radio-material-orange">
                                        <label style="padding-left: 32px; font-size:12px;font-weight: inherit;">
                                            <form:radiobutton path="searchTarget" name="headerSearchRadio" value="places"/>
                                            Places
                                        </label>
                                    </div>

                                    <div class="radio radio-material-pink">
                                        <label style="padding-left: 32px; font-size:12px;font-weight: inherit;">
                                            <form:radiobutton path="searchTarget" name="headerSearchRadio" value="teams"/>
                                            Teams
                                        </label>
                                    </div>

                                    <form:button type="submit" class="btn btn-xs btn-material-primary shadow-z-1"
                                                 style="margin-left:15px; margin-top:12px;">Search</form:button>
                                <%--</div>--%>
                            </form:form>
                        </c:if>

                        <ul class="nav navbar-nav navbar-right">
                            <sec:authorize access="isAnonymous()">
                                <li>
                                    <a href="<c:url value='/signup' />"><spring:message code='signup'/>
                                        <span class="glyphicon glyphicon-list-alt"></span>
                                    </a>
                                </li>

                                <li>
                                    <a href="<c:url value='/login' />"><spring:message code='signin'/>
                                        <span class="glyphicon glyphicon-log-in"></span>
                                    </a>
                                </li>
                            </sec:authorize>
                            <sec:authorize access="isAuthenticated()">

                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                        <span class="glyphicon glyphicon-glass"></span>&nbsp; My Events <span class="caret"></span></a>
                                    <ul class="dropdown-menu dropdown-menu-material-cyan" role="menu">
                                        <li>
                                            <a href="/events/new"><span class="glyphicon glyphicon-plus"></span>&nbsp; New</a>
                                        </li>
                                        <li class="divider"></li>
                                        <li>
                                            <a href="/events/manage"><span class="glyphicon glyphicon-wrench"></span>&nbsp; Manage</a>
                                        </li>
                                    </ul>
                                </li>

                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                        <span class="glyphicon glyphicon-home"></span>&nbsp; My Places <span class="caret"></span></a>
                                    <ul class="dropdown-menu dropdown-menu-material-orange" role="menu">
                                        <li>
                                            <a href="/places/new"><span class="glyphicon glyphicon-plus"></span>&nbsp; New</a>
                                        </li>
                                        <li class="divider"></li>
                                        <li>
                                            <a href="/places/manage"><span class="glyphicon glyphicon-wrench"></span>&nbsp; Manage</a>
                                        </li>
                                    </ul>
                                </li>
                                
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon mdi-social-group"></span> &nbsp; My Teams <span class="caret"></span></a>
                                    <ul class="dropdown-menu dropdown-menu-material-pink" role="menu">
                                        <li>
                                            <a href="/teams/new"><span class="glyphicon glyphicon-plus"></span>&nbsp; New</a>
                                        </li>
                                        <li class="divider"></li>
                                        <li>
                                            <a href="/teams/manage"><span class="glyphicon glyphicon-wrench"></span>&nbsp; Manage</a>
                                        </li>
                                    </ul>
                                </li>
                                

                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> &nbsp;
                                        <spring:message code='greeting'/>, <sec:authentication property="principal.user.name"/> <span class="badge">84</span><span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<c:url value='/users/' /><sec:authentication property='principal.user.id' />"><span
                                                    class="glyphicon glyphicon-user"></span>
                                                <spring:message code='profile'/></a></li>
                                        <!--<li><a href="#"><span class="glyphicon glyphicon-cog"></span> Settings</a></li>-->
                                        <li class="divider"></li>
                                        <li>
                                            <a href="#"><span class="glyphicon glyphicon-globe"></span> Notifications <span class="badge">42</span></a>
                                        </li>

                                        <li>
                                            <a href="#"><span class="glyphicon glyphicon-envelope"></span> Messages <span class="badge">42</span></a>
                                        </li>

                                        <li>
                                            <a href="/friends"><span class="glyphicon glyphicon-eye-open"></span>&nbsp; Friends</a>
                                        </li>

                                        <li class="divider"></li>

                                        <li>
                                            <c:url var="logoutUrl" value="/logout"/>
                                            <form:form id="logoutForm" action="${logoutUrl}" method="post">
                                            </form:form>
                                            <a href="#" onclick="document.getElementById('logoutForm').submit()">
                                                 <span class="glyphicon glyphicon-log-out"></span> <spring:message code='signout'/></a>
                                        </li>
                                    </ul>
                                </li>
                            </sec:authorize>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->
                </div>
                <!-- /.container-fluid -->
            </nav>
        </div>
        <div class="row" id="warnings">
            <sec:authorize access="hasRole('ROLE_UNVERIFIED')">
                <div class="alert alert-warning alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <spring:message code='emailNotVerified.1'/> <a
                        href="<c:url value='/users/resend-verification-email'/>"><spring:message
                        code='clickHere'/></a><spring:message code='emailNotVerified.2'/>
                </div>
            </sec:authorize>

            <c:if test="${not empty flashMessage}">
                <div class="alert alert-${flashKind} alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        ${flashMessage}
                </div>
            </c:if>
        </div>