<%@include file="includes/header.jsp" %>

<div class="jumbotron">
    <h2>Create Team</h2>
    <form:form modelAttribute="addTeamForm" class="form-horizontal" method="post">
        <div class="form-group">
            <label for="name" class="col-sm-3 control-label">Name</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="name" name="name" placeholder="Name">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-12 text-center">
                <form:button type="submit" class="btn btn-primary">Submit</form:button>
            </div>
        </div>
    </form:form>
</div>

<%@include file="includes/footer.jsp" %>