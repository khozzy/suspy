<%@include file="includes/header.jsp" %>

        <div class="col-md-2 column">
        <h3>Profile</h3>
        <div class="list-group">
            <a href="#" class="list-group-item active">Overview</a>
            <c:if test="${user.editable}">
                <a href="="/users/${user.id}/edit" class="list-group-item">Edit profile</a>
                <a href="="/users/${user.id}/change-password" class="list-group-item">Change password</a>
                <a href="="/users/${user.id}/change-email" class="list-group-item">Change e-mail</a>
                <a href="="/users/${user.id}/advanced" class="list-group-item">Advanced</a>
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
                <h1>
                    <c:out value="${user.name}" /> <c:out value="${user.surname}" />
                    <c:if test="${user.observable}">
                        <c:choose>
                            <c:when test="${observed}">
                                <a class="btn btn-default" href="<c:url value='/users/${user.id}/stopObserving'/>">
                                    <span class="glyphicon glyphicon-eye-close"></span> &nbsp; Don't observe</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-default" href="<c:url value='/users/${user.id}/startObserving'/>">
                                    <span class="glyphicon glyphicon-eye-open"></span> &nbsp; Observe</a>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </h1>
                <sec:authorize access="isAuthenticated()">
                    <dl class="dl-horizontal">
                        <dt>Email</dt>
                        <dd><c:out value="${user.email}" /></dd>
                        <dt>Role</dt>
                        <dd><c:out value="${user.roles}" /></dd>
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

<%@include file="includes/footer.jsp" %>