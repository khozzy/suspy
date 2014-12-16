<%@include file="includes/header.jsp" %>
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><spring:message code="place.form"/></h3>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="addPlaceForm" class="form-horizontal" role="form" method="post">
                        <errors/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="name"><spring:message code="place.form.place_name"/></label>
                            <div class="col-sm-10">
                                <input id="name" name="name" type="text" placeholder="" class="form-control input-md">

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="city"><spring:message code="place.form.city"/></label>
                            <div class="col-sm-10">
                                <input id="city" name="city" type="text" placeholder="" class="form-control input-md">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="street"><spring:message code="place.form.street"/></label>
                            <div class="col-sm-10">
                                <input id="street" name="street" type="text" placeholder="" class="form-control input-md">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="houseNumber"><spring:message code="place.form.houseNumber"/></label>
                            <div class="col-sm-10">
                                <input id="houseNumber" name="houseNumber" type="text" placeholder="" class="form-control input-md">
                            </div>
                        </div>
                        <!-- ADD TIMESLOT -->
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="timeSlotListBox"><spring:message code='place.form.timeslot.list'/></label>
                            <div class="col-sm-10">
                                <textarea readonly class="form-control" id="timeSlotListBox" name="timeSlotListBox"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="price"><spring:message code="place.form.timeslot.cost"/></label>
                            <div class="col-sm-10">
                                <input id="price" name="price" type="text" placeholder="" class="form-control input-md">

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="date_from" ><spring:message code='place.form.timeslot.date_from'/></label>
                            <div class="col-sm-10">
                                <input id="date_from" type="date" class="form-control" placeholder="event name"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="hour_from"><spring:message code='place.form.timeslot.hour_from'/></label>
                            <div class="col-sm-10">
                                <input id="hour_from" name="hour_from" type="time" placeholder="00:00" class="form-control input-md">

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="date_to"><spring:message code='place.form.timeslot.date_to'/></label>
                            <div class="col-sm-10">
                                <input id="date_to" type="date" class="form-control" placeholder="event name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="hour_to"><spring:message code='place.form.timeslot.hour_to'/></label>
                            <div class="col-sm-10">
                                <input id="hour_to" name="hour_to" type="time" placeholder="00:00" class="form-control input-md">

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="addTimeSlotButton"></label>
                            <div class="col-sm-10">
                                <INPUT TYPE="button" id="addTimeSlotButton" name="addTimeSlotButton" class="btn btn-success" onClick="addTimeSlotToList(this.form)" Value="<spring:message code='place.form.timeslot.add'/>">
                            </div>
                        </div>
                        <%-- TODO1: ADD radio button activites!!!! --%>

                        <!--  HIDDEN -->
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="timeSlotList" style="visibility:hidden"><spring:message code='place.form.timeslot.list'/></label>
                            <div class="col-sm-10">
                                <textarea class="form-control" id="timeSlotList" name="timeSlotList" style="visibility:hidden"></textarea>
                            </div>
                        </div>
                        <!--  /HIDDEN -->
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-9">
                                <form:button type="submit" class="btn btn-default"><spring:message code='place.form.submit'/></form:button>
                                    <%-- TODO: CHANGE Name of finish form button --%>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>

<script>
    function addTimeSlotToList(form)
    {
        var price = parseFloat(form.price.value);
        if(!(Number(price)===price) || price<=0){
            return;
        }
        if(form.date_from.value.length<=0 || form.date_to.value.length<=0 ||
                form.hour_from.value.length<=0 || form.hour_to.value.length<=0){
            return;
        }
        form.timeSlotList.value += form.date_from.value + " " +  form.hour_from.value + "," + form.date_to.value + " " + form.hour_to.value + ","+ form.price.value+ ";";
        form.timeSlotListBox.value += form.date_from.value + " " + form.hour_from.value + " - " + form.date_to.value + " " + form.hour_to.value + ", " + "Cost" + ": "+ form.price.value + "\n";
    }
</script>
<%@include file="includes/footer.jsp" %>