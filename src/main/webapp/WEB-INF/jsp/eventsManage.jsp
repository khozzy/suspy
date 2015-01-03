<%@include file="includes/header.jsp" %>

<div class="row">
  <h2>Events</h2>

  <table id="events_table" class="display" style="border-collapse:collapse;" cellspacing="0" width="100%">
    <thead>
    <tr>
      <th><spring:message code='event.name'/></th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${eventsFound}"  var="event">
      <tr>
        <td><c:out value="${event.name}" /></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>


<script>
  $(document).ready(function() {
    $("#events_table").DataTable();
  })

</script>

<%@include file="includes/footer.jsp" %>