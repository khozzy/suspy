<%@include file="includes/header.jsp" %>

    <script src="/public/lib/js/datatables.min.js"></script>
<div class="row">
    <h2><spring:message code='place.list.timeslot'/></h2>

    <table id="places_table" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th><spring:message code='place.form.place_name'/></th>
                <th><spring:message code='place.form.city'/></th>
                <th><spring:message code='place.form.street'/></th>
                <th><spring:message code='place.form.houseNumber'/></th>
                <th><spring:message code='place.form.capacity'/></th>
            </tr>
        </thead>

        <tbody>
                <tr>
                    <td><c:out value="${editedPlace.name}" /></td>
                    <td><c:out value="${editedPlace.address.city}"/></td>
                    <td><c:out value="${editedPlace.address.street}"/></td>
                    <td><c:out value="${editedPlace.address.houseNumber}"/></td>
                    <td><c:out value="${editedPlace.capacity}"/></td>
                </tr>
        </tbody>
    </table>
    <table id="timeslots_table" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th><spring:message code='place.form.timeslot.date_from'/></th>
            <th><spring:message code='place.form.timeslot.date_to'/></th>
            <th><spring:message code='place.form.timeslot.cost'/></th>
        </tr>
        </thead>
        <br>
        <tbody>
        <c:forEach items="${timeSlotsFound}" var="timeslot">
            <tr>
                <td><c:out value="${timeslot.from}" /></td>
                <td><c:out value="${timeslot.to}" /></td>
                <td><c:out value="${timeslot.price}" /></td>
                <form:form>
                    <td><a href="/place/timeslot/edit?id=<c:out value='${timeslot.id}'/>" class="btn btn-primary">
                    <spring:message code='modify'/></a></td>
                </form:form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form:form>
        <td><a href="/place/timeslot/mylist?id=<c:out value='x'/>" class="btn btn-primary">
            <spring:message code='place.edit.addNewTimeslot.button'/></a></td>
        <td><a href="/place/mylist/" class="btn btn-primary">
            <spring:message code='return'/></a></td>
    </form:form>

</div>

    <script>
        $(document).ready(function() {
            $("#places_table").DataTable();
        })

    </script>

<%@include file="includes/footer.jsp" %>