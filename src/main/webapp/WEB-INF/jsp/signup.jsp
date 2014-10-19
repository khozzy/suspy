<%@include file="includes/header.jsp"%>
<spring:message code="signup" />
<%--formularz rejestracji użytkownika i przycisk sign up
 odsyłający do /create/user.jsp - zmienić nazwy na wczytywane
z languages--%>
<form class="form-horizontal" role="form" action="create/user" method="post">

    <div class="form-group">
        <label for="name" class="col-sm-2 control-label">Name</label>
        <div class="col-sm-10">
            <input type="text" class="form-control"  id="name" name="name" placeholder="Name">
        </div>
    </div>

    <div class="form-group">
        <label for="surname" class="col-sm-2 control-label">Surname</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="surname" name="surname" placeholder="Surname">
        </div>
    </div>

    <div class="form-group">
        <label for="email" class="col-sm-2 control-label">Email</label>
        <div class="col-sm-10">
            <input type="email" class="form-control" id="email" name="email" placeholder="Email">
        </div>
    </div>

    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">Password</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="password" name="password" placeholder="Password">
        </div>
    </div>


    <div class="form-group">
        <label for="role" class="col-sm-2 control-label">Role</label>
        <select class="form-control" id="role" name="role">
            <option value="SPORTSMAN">Sportsman</option>
            <option value="GYM_OWNER">Gym owner</option>
        </select>
    </div>

    <div class="form-group">
        <label for="city" class="col-sm-2 control-label">City</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="city" name="city" placeholder="City">
        </div>
    </div>

    <div class="form-group">
        <label for="street" class="col-sm-2 control-label">Street</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="street" name="street" placeholder="Street">
        </div>
    </div>

    <div class="form-group">
        <label for="houseNumber" class="col-sm-2 control-label">House number</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="houseNumber" name="houseNumber" placeholder="House number">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <div class="checkbox">
                <label>
                    <input type="checkbox" id="termsAcceptance" name="termsAcceptance" value="accepted"> I accept all terms and conditions.
                </label>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Sign up</button>
        </div>
    </div>
</form>
<%@include file="includes/footer.jsp"%>