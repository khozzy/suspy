<%@include file="includes/header.jsp"%>

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
                <input id="username" name="username" type="email" class="form-control" placeholder="${emailPlaceholder}" />
            </div>

            <div class="form-group">
                <label for="password"><spring:message code='password.header'/></label>
                <spring:message code='password.signin.placeholder' var="passwordPlaceholder"/>
                <input type="password" id="password" name="password" class="form-control" placeholder="${passwordPlaceholder}" />
                <form:errors cssClass="error" path="password" />
            </div>

            <button type="submit" class="btn btn-primary"><spring:message code='signin'/></button>

        </form:form>
    </div>
</div>

<%@include file="includes/footer.jsp"%>