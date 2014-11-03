<%@include file="includes/header.jsp" %>

<div class="panel panel-primary">

    <div class="panel-heading">
        <h3 class="panel-title"><spring:message code='forgotPassword'/>?</h3>
    </div>

    <div class="panel-body">

        <form:form modelAttribute="forgotPasswordForm" role="form">

            <form:errors/>

            <div class="form-group">
                <form:label path="email"><spring:message code='email.header'/></form:label>
                <spring:message code='email.signin.placeholder' var="emailPlaceholder"/>
                <form:input path="email" type="email" class="form-control" placeholder="${emailPlaceholder}"/>
                <form:errors cssClass="error" path="email"/>
            </div>

            <button type="submit" class="btn btn-default"><spring:message code='resetPassword'/></button>

        </form:form>
    </div>
</div>

<%@include file="includes/footer.jsp" %>
