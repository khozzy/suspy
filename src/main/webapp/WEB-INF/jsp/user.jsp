<%@include file="includes/header.jsp" %>

<div class="row">
    <div class="col-md-2 column">
        <h3>Profile</h3>
        <div class="list-group">
            <a href="#" class="list-group-item active">Overview</a>
            <a href="#" class="list-group-item">Change name</a>
            <a href="#" class="list-group-item">Change password</a>
            <a href="#" class="list-group-item">Change e-mail</a>
            <a href="#" class="list-group-item">Advanced</a>
        </div>
     </div>
     <div class="col-md-10 column">
        <div class="row clearfix">
            <div class="col-md-4 column">
                <div class="media profile-picture">
                    <a class="media-left event-img" href="#">
                        <img src="/public/lib/assets/profile-default.jpg" alt="prifile-picture">
                    </a>
                </div>
            </div>
            <div class="col-md-8 column">
                <h1>
                    <sec:authentication property="principal.user.name"/>
                </h1>
            </div>
        </div>
    </div>
</div>
<div class="clearfix"/>

<%@include file="includes/footer.jsp" %>