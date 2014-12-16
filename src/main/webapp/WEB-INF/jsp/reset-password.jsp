<%@include file="includes/header.jsp" %>

    <div class="panel panel-default">

        <div class="panel-heading">
            <h3 class="panel-title"><spring:message code='changePassword'/></h3>
        </div>

        <div class="panel-body">

            <form:form modelAttribute="resetPasswordForm" role="form">

                <form:errors/>

                <div class="form-group">
                    <form:label path="password"><spring:message code='enterNewPassword'/></form:label>
                    <spring:message code='enterNewPassword' var="passwordPlaceholder"/>
                    <form:password path="password" class="form-control" placeholder="${passwordPlaceholder}"/>
                    <form:errors cssClass="error" path="password"/>
                </div>

                <div class="form-group">
                    <form:label path="retypePassword"><spring:message code='reenterNewPassword'/></form:label>
                    <spring:message code='reenterNewPassword' var="passwordConfirmPlaceholder"/>
                    <form:password path="retypePassword" class="form-control" placeholder="${passwordConfirmPlaceholder}"/>
                    <form:errors cssClass="error" path="retypePassword"/>
                </div>

                <button type="submit" class="btn btn-default"><spring:message code='changePassword'/></button>

            </form:form>
        </div>
    </div>

<%@include file="includes/footer.jsp" %>
