<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>No team? TeamFinder!</title>

    <!-- Bootstrap & our CSS-->
    <link href="/public/lib/bootstrap-3.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="/public/lib/css/styles.css" rel="stylesheet">

    <!-- jQuery, Bootstrap and our JS -->
    <script src="/public/lib/js/jquery-1.11.1.min.js"></script>
    <script src="/public/lib/bootstrap-3.3.1/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Suspy</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <form:form modelAttribute="homePageSearch" role="search" method="post" cssClass="navbar-form navbar-left" >
                <div class="form-group">
                    <form:input path="searchText" cssClass="form-control" placeholder="Search" />
                    <form:radiobutton path="searchTarget" value="event" cssClass="radio-inline" cssStyle="margin: 0 5px 0 5px;"/>Events
                    <form:radiobutton path="searchTarget" value="place" cssClass="radio-inline" cssStyle="margin: 0 5px 0 5px;"/>Places

                    <form:button type="submit" class="btn btn-success">Find</form:button>
                </div>
            </form:form>
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
                    <li>
                        <a href="#"><span class="glyphicon glyphicon-globe"></span> Notifications</a>
                    </li>

                    <li>
                        <a href="/friends"><span class="glyphicon glyphicon-eye-open"></span> Friends</a>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-th-large"></span> Events <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li>
                                <a href="/new-event"><span class="glyphicon glyphicon-plus"></span> New</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#"><span class="glyphicon glyphicon-tasks"></span> Upcoming</a>
                            </li>
                            <li>
                                <a href="#"><span class="glyphicon glyphicon-wrench"></span> Manage</a>
                            </li>
                            <li>
                                <a href="#"><span class="glyphicon glyphicon-header"></span> History</a>
                            </li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>
                            <spring:message code='greeting'/>, <sec:authentication property="principal.user.name"/><b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="<c:url value='/users/' /><sec:authentication property='principal.user.id' />"><span
                                        class="glyphicon glyphicon-user"></span>
                                    <spring:message code='profile'/></a></li>
                            <!--<li><a href="#"><span class="glyphicon glyphicon-cog"></span> Settings</a></li>-->
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