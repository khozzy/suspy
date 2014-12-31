<%@include file="includes/header.jsp" %>

<div class="row">
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
                    <div class="togglebutton">
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
</div>

<%@include file="includes/footer.jsp" %>