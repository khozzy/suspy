<%@include file="includes/header.jsp" %>
<script>
    // 14-12-2014 to 15-12-2014, cost 40;
    // <date_from><hour_from> to <date_to><hour_to>, cost <price>;
    //date_from,date_to,price;date_from,date_to,price,

    function addTimeSlotToList(form)
    {
//TODO:
        //Add validators;
        form.timeSlotList.value += form.date_from.value + " " +  form.hour_from.value +
                "," + form.date_to.value + " " + form.hour_to.value + ","+ form.price.value+ ";";
        form.timeSlotListBox.value += form.date_from.value + " " + form.hour_from.value +
                " - " + form.date_to.value + " " + form.hour_to.value +
                ", " + "Cost" + ": "+ form.price.value + "\n"; //TODO: <spring:message code="place.form.cost"/> zamiast cost
        //form.timeSlotList.value = timeSlotBoxContent;
    }
</script>
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
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="name"><spring:message code="place.form.place_name"/></label>
                            <div class="col-md-4">
                                <input id="name" name="name" type="text" placeholder="" class="form-control input-md">

                            </div>
                        </div>

                        <!-- Text input-->

                        <div class="form-group">
                            <label class="col-md-4 control-label" for="city"><spring:message code="place.form.city"/></label>
                            <div class="col-md-4">
                                <input id="city" name="city" type="text" placeholder="" class="form-control input-md">

                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="street"><spring:message code="place.form.street"/></label>
                            <div class="col-md-4">
                                <input id="street" name="street" type="text" placeholder="" class="form-control input-md">

                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="houseNumber"><spring:message code="place.form.houseNumber"/></label>
                            <div class="col-md-4">
                                <input id="houseNumber" name="houseNumber" type="text" placeholder="" class="form-control input-md">

                            </div>
                        </div>

                        <!-- ADD TIMESLOT -->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="timeSlotListBox">Time Slots</label>
                            <div class="col-md-4">
                                <textarea readonly class="form-control" id="timeSlotListBox" name="timeSlotListBox"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="price"><spring:message code="place.form.cost"/></label>
                            <div class="col-md-4">
                                <input id="price" name="name" type="price" placeholder="" class="form-control input-md">

                            </div>
                        </div>
                        <!-- Text input-->

                        <div class="form-group">
                            <div class="col-sm-2 control-label">
                                <form:label path="date_from" class="col-sm-2 control-label">Date From</form:label>
                            </div>
                            <div class="col-sm-10">
                                <form:input path="date_from" type="date" class="form-control" placeholder="event name"/>
                            </div>
                            <form:errors cssClass="error" path="date_from">EWWW1</form:errors>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="hour_from">Time from</label>
                            <div class="col-md-4">
                                <input id="hour_from" name="hour_from" type="text" placeholder="00:00" class="form-control input-md">

                            </div>
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
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="hour_to">Time to</label>
                            <div class="col-md-4">
                                <input id="hour_to" name="hour_to" type="text" placeholder="00:00" class="form-control input-md">

                            </div>
                        </div>
                        <!-- Button -->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="addTimeSlotButton"></label>
                            <div class="col-md-4">
                                <INPUT TYPE="button" id="addTimeSlotButton" name="addTimeSlotButton" class="btn btn-success"  Value="Add Timeslot" onClick="addTimeSlotToList(this.form)">

                            </div>
                        </div>
                        <%-- TODO1: ADD radio button activites!!!! --%>

                        <!--  HIDDEN -->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="timeSlotList" style="visibility:hidden">Time Slots list</label>
                            <div class="col-md-4">
                                <textarea class="form-control" id="timeSlotList" name="timeSlotList" style="visibility:hidden"></textarea>
                            </div>
                        </div>
                        <!--  /HIDDEN -->
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