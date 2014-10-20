<%@include file="includes/header.jsp"%>
<%--formularz rejestracji użytkownika i przycisk sign up
 odsyłający do /create/user.jsp - zmienić nazwy na wczytywane
z languages--%>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title"><spring:message code="signup"/></h3>
    </div>
    <div class="panel-body">
        <form:form modelAttribute="signupForm" class="form-horizontal" role="form" action="/signup" method="post">

            <div class="form-group">
                <form:label path="name" for="name" class="col-sm-2 control-label"><spring:message code='name.header'/></form:label>
                <div class="col-sm-10">
                    <spring:message code='name.placeholder' var="name.placeholder"/>
                    <form:input path="name" class="form-control" id="name" placeholder="${name.placeholder}"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="surname" for="surname" class="col-sm-2 control-label"><spring:message code='surname.header'/></form:label>
                <div class="col-sm-10">
                    <spring:message code='surname.placeholder' var="surname.placeholder"/>
                    <form:input path="surname" class="form-control" id="surname" placeholder="${surname.placeholder}"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="email" for="email" class="col-sm-2 control-label"><spring:message code='email.header'/> </form:label>
                <div class="col-sm-10">
                    <spring:message code='email.placeholder' var="email.placeholder"/>
                    <form:input path="email" type="email" class="form-control" id="email" placeholder="${email.placeholder}"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-10">
                    <spring:message code='email.repeat.placeholder' var="email.repeat.placeholder"/>
                    <input type="email" class="form-control" id="email" placeholder="${email.repeat.placeholder}"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="password" for="password" class="col-sm-2 control-label"><spring:message code='password.header' /> </form:label>
                <div class="col-sm-10">
                    <spring:message code='password.placeholder' var="password.placeholder"/>
                    <form:input path="password" type="password" class="form-control" id="password" placeholder="${password.placeholder}"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-10">
                    <spring:message code='password.repeat.placeholder' var="password.repeat.placeholder"/>
                    <input type="password" class="form-control" id="password" placeholder="${password.repeat.placeholder}"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="role" for="role" class="col-sm-2 control-label"><spring:message code='role.header'/></form:label>
                <form:select path="role" class="form-control" id="role">
                    <form:option value="SPORTSMAN"><spring:message code='role.default.header'/></form:option>
                    <form:option value="SPORTSMAN"><spring:message code='role.sportsman.header'/></form:option>
                    <form:option value="GYM_OWNER"><spring:message code='role.gym_owner.header'/></form:option>
                </form:select>
            </div>

            <div class="form-group">
                <form:label path="city" for="city" class="col-sm-2 control-label"><spring:message code='city.header'/></form:label>
                <div class="col-sm-10">
                    <spring:message code='city.placeholder' var="city.placeholder"/>
                    <form:input path="city" class="form-control" id="city" placeholder="${city.placeholder}"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="street" for="street" class="col-sm-2 control-label"><spring:message code='street.header'/></form:label>
                <div class="col-sm-10">
                    <spring:message code='street.placeholder' var="street.placeholder"/>
                    <form:input path="street" class="form-control" id="street" placeholder="${street.placeholder}"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="houseNumber" for="houseNumber" class="col-sm-2 control-label"><spring:message code='houseNumber.header'/></form:label>
                <div class="col-sm-10">
                    <spring:message code='houseNumber.placeholder' var="houseNumber.placeholder"/>
                    <form:input path="houseNumber" class="form-control" id="houseNumber" placeholder="${houseNumber.placeholder}"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="about" for="about" class="col-sm-2 control-label"><spring:message code='about.header'/></form:label>
                <spring:message code='about.placeholder' var="about.placeholder"/>
                <form:textarea path="about" class="form-control" rows="3" placeholder="${about.placeholder}"></form:textarea>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <form:label path="termsAcceptance">
                            <form:checkbox path="termsAcceptance" id="termsAcceptance"/> <spring:message code='termsAcceptance.header'/>
                        </form:label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <form:button type="submit" class="btn btn-default"><spring:message code='signup'/></form:button>
                </div>
            </div>

        </form:form>
    </div>
</div>
<%@include file="includes/footer.jsp"%>