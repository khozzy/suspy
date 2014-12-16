<%@include file="includes/header.jsp" %>

    <script src="/public/lib/js/datatables.min.js"></script>

    <h2>Places</h2>

    <table id="places_table" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Name</th>
                <th>City</th>
                <th>Capacity</th>
                <th>Owner</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach items="${placesFound}" var="place">
                <tr>
                    <td><c:out value="${place.name}" /></td>
                    <td><c:out value="${place.address.city}"/></td>
                    <td><c:out value="${place.capacity}"/></td>
                    <td><c:out value="${place.owner.name}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>


    <script>
        $(document).ready(function() {
            $("#places_table").DataTable();
        })
    </script>

<%@include file="includes/footer.jsp" %>