<%@include file="includes/header.jsp" %>
<script src="/public/lib/js/datatables.min.js"></script>

<h2>Events</h2>

<table id="events_table" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>Name</th>
        <th>Team</th>
        <th>Where</th>
        <th>When</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${eventsFound}" var="event">
        <tr>
            <td><c:out value="${event.name}" /></td>
            <td><c:out value="${event.team.name}"/></td>
            <td><c:out value="${event.timeSlot.place.name}"/></td>
            <td><c:out value="${event.timeSlot.from} - ${event.timeSlot.to}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<script>
    $(document).ready(function() {
        $("#events_table").DataTable();
    });
</script>
<div class="clearfix"/>
<%@include file="includes/footer.jsp" %>