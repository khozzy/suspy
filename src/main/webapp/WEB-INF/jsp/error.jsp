<%@include file="includes/header.jsp" %>
<div class="alert alert-danger">
    <p><spring:message code='unexpectedError'/>${error}, status=${status}): <i>${message}</i></p>

    <p><a href="/"><spring:message code='clickHere'/></a>
        <spring:message code='goToHomePage'/></p>
</div>
<%@include file="includes/footer.jsp" %>