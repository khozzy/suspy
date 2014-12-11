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
    <link href="/public/lib/css/commentsStyle.css" rel="stylesheet">

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


        </div>
    </nav>

<div class="panel panel-primary">

    <div class="panel-heading">
        <h3 class="panel-title"><spring:message code='signin'/></h3>
    </div>

    <div class="panel-body">

        <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                <spring:message code='signin.invalid'/>
            </div>
        </c:if>

        <c:if test="${param.logout != null}">
            <div class="alert alert-danger">
                <spring:message code='loggedOut'/>
            </div>
        </c:if>

        <form:form role="form" method="post">

            <div class="form-group">
                <label for="username"><spring:message code='email.header'/></label>
                <spring:message code='email.signin.placeholder' var="emailPlaceholder"/>
                <input id="username" name="username" type="email" class="form-control"
                       placeholder="${emailPlaceholder}"/>
            </div>

            <div class="form-group">
                <label for="password"><spring:message code='password.header'/></label>
                <spring:message code='password.signin.placeholder' var="passwordPlaceholder"/>
                <input type="password" id="password" name="password" class="form-control"
                       placeholder="${passwordPlaceholder}"/>
                <form:errors cssClass="error" path="password"/>
            </div>

            <div class="form-group">
                <div class="checkbox">
                    <label>
                        <input name="_spring_security_remember_me" type="checkbox"> <spring:message
                            code='signin.rememberme'/>
                    </label>
                </div>
            </div>

            <button type="submit" class="btn btn-primary"><spring:message code='signin'/></button>
            <a class="btn btn-default" href="<c:url value='/forgot-password'/>"><spring:message
                    code='forgotPassword'/>?</a>

        </form:form>
    </div>
</div>

<%@include file="includes/footer.jsp" %>