<%@include file="includes/header.jsp" %>

    <script src="/public/lib/js/datatables.min.js"></script>

<div class="row">
    <h2><spring:message code='place.edit.timeslot'/></h2>

    <table id="places_table" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th><spring:message code='place.form.timeslot.date_from'/></th>
                <th><spring:message code='place.form.timeslot.date_to'/></th>
                <th><spring:message code='place.form.timeslot.cost'/></th>
            </tr>
        </thead>

        <tbody>
        <td><c:out value="${timeslot.from}" /></td>
        <td><c:out value="${timeslot.to}" /></td>
        <td><c:out value="${timeslot.price}" /></td>
        </tbody>
    </table>
    <br>
    <div class="row2">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><spring:message code="place.edit.timeslot"/></h3>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="editTimeSlotForm" class="form-horizontal" role="form" method="post">
                        <errors/>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="price"><spring:message code="place.form.timeslot.cost"/></label>
                            <div class="col-sm-10">
                                <input id="price" name="price" type="text" placeholder="" class="form-control input-md" value="${timeslot.price}"/>

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="date_from" ><spring:message code='place.form.timeslot.date_from'/></label>
                            <div class="col-sm-10">
                                <input id="date_from" name="date_from" type="date" class="form-control" placeholder="event name"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="hour_from"><spring:message code='place.form.timeslot.hour_from'/></label>
                            <div class="col-sm-10">
                                <input id="hour_from" name="hour_from" type="time" placeholder="00:00"
                                class="form-control input-md">

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="date_to"><spring:message code='place.form.timeslot.date_to'/></label>
                            <div class="col-sm-10">
                                <input id="date_to" name="date_to" type="date" class="form-control" placeholder="event name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="hour_to"><spring:message code='place.form.timeslot.hour_to'/></label>
                            <div class="col-sm-10">
                                <input id="hour_to" name="hour_to" type="time" placeholder="00:00"
                                       class="form-control input-md">

                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-9">
                                <form:button type="submit" class="btn btn-default"><spring:message code='place.edit.submit'/></form:button>
                            </div>
                        </div>
                    </form:form>
                    <td><a href="/place/timeslot/mylist?id=<c:out value='${timeslot.place.id}'/>" class="btn btn-primary">
                        <spring:message code='return'/></a></td>
                </div>
            </div>
        </div>
    </div>
</div>


    <script>
        $(document).ready(function() {
            $("#places_table").DataTable();
        })
    </script>

<%@include file="includes/footer.jsp" %>