<%@include file="includes/header.jsp" %>
<div class="row">
    <div class="col-md-8 col-md-offset-2">
        <div class="panel panel-default">

            <div class="panel-heading">
                <h3 class="panel-title"><spring:message code="signup"/></h3>
            </div>

            <div class="panel-body">
                <%-- TODO1: ADD DATE FIELDS TO DATES --%>

                <form:form modelAttribute="addTimeSlotForm" class="form-horizontal" role="form" method="post">
                    <form:errors/>

                    <div class="form-group">
                        <div class="col-sm-2 control-label">
                            <form:label path="date_from" class="col-sm-2 control-label">Date From</form:label>
                        </div>
                        <div class="col-sm-10">
                            <form:input path="date_from" type="date" class="form-control" placeholder="event name"/>
                        </div>
                        <form:errors cssClass="error" path="date_from">EWWW1</form:errors>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2 control-label">
                            <form:label path="date_to" class="col-sm-2 control-label">Date To</form:label>
                        </div>
                        <div class="col-sm-10">
                            <form:input path="date_to" type="date" class="form-control" placeholder="event name"/>
                        </div>
                        <form:errors cssClass="error" path="date_to">EWW2</form:errors>
                    </div>

                    <div class="form-group">
                        <form:label path="price">price</form:label>
                        <form:input path="price" class="form-control" />
                        <p class="help-block">price</p>
                    </div>
                    <div class="form-group">
                        <form:label path="place_id">place_id</form:label>
                        <form:input path="place_id" class="form-control" />
                        <p class="help-block"> place_id</p>
                    </div>

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
<%@include file="includes/footer.jsp" %>