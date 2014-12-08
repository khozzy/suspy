<%@include file="includes/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><spring:message code="place.form"/></h3>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="addPlaceForm" class="form-horizontal" role="form" method="post">
                        <form:errors/>
                        <div class="form-group">
                            <div class="col-sm-2 control-label">
                                <label class="col-sm-2 control-label">Name</label>
                            </div>
                            <div class="col-sm-10">
                                <input class="form-control" placeholder="event name"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-2 control-label">
                                <form:label path="name">Places Name</form:label>
                            </div>
                            <div class="col-sm-10">
                                <form:input path="name" class="form-control" />
                                <p class="help-block">Your Place's name.</p>
                            </div>
                        </div>

                        <div class="form-group">
                            <form:label path="city">City</form:label>
                            <form:input path="city" class="form-control" />
                            <p class="help-block">City where you live</p>
                        </div>
                        <div class="form-group">
                            <form:label path="street">Street</form:label>
                            <form:input path="street" class="form-control" />
                            <p class="help-block">Street on which you live</p>
                        </div>
                        <div class="form-group">
                            <form:label path="houseNumber">House number</form:label>
                            <form:input path="houseNumber" class="form-control" />
                            <p class="help-block">House number in which you live</p>
                        </div>

                        <div class="form-group">
                            <form:label path="capacity">Capacity</form:label>
                            <form:input path="capacity" class="form-control" />
                            <p class="help-block">Max number of people that can be in your place</p>
                        </div>

                        <%-- TODO1: ADD radio button activites!!!! --%>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-9">
                                <form:button type="submit" class="btn btn-default"><spring:message
                                        code='signup'/></form:button>
                                    <%-- TODO: CHANGE Name of finish form button --%>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="includes/footer.jsp" %>