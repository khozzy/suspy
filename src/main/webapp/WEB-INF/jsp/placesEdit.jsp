<%@include file="includes/header.jsp" %>

    <script src="/public/lib/js/datatables.min.js"></script>

<div class="row">
    <h2><spring:message code='place.edit'/></h2>

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
        <td><c:out value="${editedPlace.name}" /></td>
        <td><c:out value="${editedPlace.address.city}"/></td>
        <td><c:out value="${editedPlace.address.street}"/></td>
        <td><c:out value="${editedPlace.address.houseNumber}"/></td>
        <td><c:out value="${editedPlace.capacity}"/></td>
        </tbody>
    </table>
    <br>
    <div class="row2">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><spring:message code="place.form"/></h3>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="editPlaceForm" class="form-horizontal" role="form" method="post">
                        <errors/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="name"><spring:message code="place.form.place_name"/></label>
                            <div class="col-sm-10">
                                <input id="name" name="name" type="text" placeholder="" value="${editedPlace.name}" class="form-control input-md">

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="city"><spring:message code="place.form.city"/></label>
                            <div class="col-sm-10">
                                <input id="city" name="city" type="text" placeholder="" value="${editedPlace.address.city}" class="form-control input-md">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="street"><spring:message code="place.form.street"/></label>
                            <div class="col-sm-10">
                                <input id="street" name="street" type="text" placeholder="" value="${editedPlace.address.street}" class="form-control input-md">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="houseNumber"><spring:message code="place.form.houseNumber"/></label>
                            <div class="col-sm-10">
                                <input id="houseNumber" name="houseNumber" type="text" placeholder="" value="${editedPlace.address.houseNumber}" class="form-control input-md">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="capacity"><spring:message code="place.form.capacity"/></label>
                            <div class="col-sm-10">
                                <input id="capacity" name="capacity" type="text" placeholder="" value="${editedPlace.capacity}" class="form-control input-md">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-9">
                                <form:button type="submit" class="btn btn-default"><spring:message code='place.edit.submit'/></form:button>

                            </div>
                        </div>
                    </form:form>
                    <td><a href="/place/mylist" class="btn btn-primary">
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