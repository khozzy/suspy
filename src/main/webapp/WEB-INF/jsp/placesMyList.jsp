<%@include file="includes/header.jsp" %>

<div class="row">
    <h2><spring:message code='place.list'/></h2>

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
            <c:forEach items="${placesFound}" var="place">
                <tr>
                    <td><c:out value="${place.name}" /></td>
                    <td><c:out value="${place.address.city}"/></td>
                    <td><c:out value="${place.address.street}"/></td>
                    <td><c:out value="${place.address.houseNumber}"/></td>
                    <td><c:out value="${place.capacity}"/></td>
                    <%--<td><c:out value="${place.owner.name}"/></td>--%>
                    <form>
                        <td><a href="/place/edit?id=<c:out value='${place.id}'/>" class="btn btn-primary"><spring:message code='modify'/></a></td>
                        <td><a href="/place/timeslot/mylist?id=<c:out value='${place.id}'/>" class="btn btn-primary"><spring:message code='place.edit.timeslotEditButton'/></a></td>
                    </form>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

    <script>
        $(document).ready(function() {
            $("#places_table").DataTable();
        })

    </script>

<%@include file="includes/footer.jsp" %>