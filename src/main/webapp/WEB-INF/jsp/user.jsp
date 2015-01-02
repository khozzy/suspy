<%@include file="includes/header.jsp" %>

<div class="row panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">
            <c:out value="${user.name}" /> <c:out value="${user.surname}" />
            <c:if test="${user.observable}">
                <c:choose>
                    <c:when test="${observed}">
                        <a class="btn btn-default btn-xs pull-right" style="margin:0 0 0 0;padding: 0 0 0 0;"
                           href="<c:url value='/users/${user.id}/stopObserving'/>">
                            <span class="glyphicon glyphicon-eye-close"></span> &nbsp; Don't observe</a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-default pull-right" style="margin:0 0 0 0;padding: 0 0 0 0;"
                           href="<c:url value='/users/${user.id}/startObserving'/>">
                            <span class="glyphicon glyphicon-eye-open"></span> &nbsp; Observe</a>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </h3>
    </div>
    <div class="panel-body">
            <div class="col-md-2 column">
                <div class="list-group">
                    <div class="list-group-item">
                        <a href="#">Overview</a>
                    </div>
                    <div class="list-group-separator"></div>
                    <c:if test="${user.editable}">
                        <div class="list-group-item">
                            <a href="/users/${user.id}/edit" class="list-group-item">Edit profile</a>
                        </div>
                        <div class="list-group-separator"></div>
                        <div class="list-group-item">
                            <a href="/users/${user.id}/change-password" class="list-group-item">Change password</a>
                        </div>
                        <div class="list-group-separator"></div>
                        <div class="list-group-item">
                            <a href="/users/${user.id}/change-email" class="list-group-item">Change e-mail</a>
                        </div>
                        <div class="list-group-separator"></div>
                        <div class="list-group-item">
                            <a href="/users/${user.id}/advanced" class="list-group-item">Advanced</a>
                        </div>
                    </c:if>
                </div>
            </div>
        <div class="col-md-10 column">
            <div class="row clearfix">
                <div class="col-md-4 column">
                    <div class="media profile-picture">
                        <a class="media-left" href="#">
                            <img src="/public/lib/assets/profile-default.jpg" alt="profile-picture">
                        </a>
                    </div>
                </div>
                <div class="col-md-8 column">
                    <sec:authorize access="isAuthenticated()">
                        <dl class="dl-horizontal">
                            <dt>Email</dt>
                            <dd><c:out value="${user.email}" /></dd>
                            <dt>Role</dt>
                            <dd ><c:out value="${user.roles}" /></dd>
                            <dt>City</dt>
                            <dd><c:out value="${user.address.city}" /></dd>
                            <dt>Street</dt>
                            <dd><c:out value="${user.address.street}" /></dd>
                            <dt>House number</dt>
                            <dd><c:out value="${user.address.houseNumber}" /></dd>
                        </dl>
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                        To see user details you need to log in.
                    </sec:authorize>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="includes/footer.jsp" %>