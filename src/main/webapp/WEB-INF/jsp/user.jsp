<%@include file="includes/header.jsp" %>

<div ng-controller="userProfile" ng-init="init('${user.id}')">
    <div class="jumbotron">
        <div class="row">
            <div class="col-md-10">
                <h1>
                    <small>User</small><br>
                    <span class="userStyle">{{ user.name }} {{ user.surname }}</span>
                </h1>
            </div>

            <div class="col-md-2">
                <div class="btn-group" ng-show="isMyself()">
                    <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                        Settings
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="../../users/{{user.id}}/edit">Edit Profile</a></li>
                    </ul>
                </div>
                <a href="../../users/{{user.id}}/startObserving">
                    <button type="button" class="btn btn-material-teal" ng-show="!isObserved() && !isMyself()">Observe User</button>
                </a>
                <a href="../../users/{{user.id}}/stopObserving">
                    <button type="button" class="btn btn-material-teal" ng-show="isObserved()">Don't Observe</button>
                </a>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4">
                <h2>
                    <small>City</small><br>
                    {{ user.address.city }}
                </h2>

                <h2>
                    <small>About</small>
                </h2>
                <p>{{ user.about }}</p>

                <h2>
                    <small>Roles</small>
                </h2>
                <h4 ng-repeat="role in user.roles">
                    {{ role }}
                </h4>
            </div>

            <div class="col-md-4">
                <div class="panel panel-material-pink">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Teams <span class="badge">{{user.teams.length}}</span>
                        </h3>
                    </div>
                    <div class="panel-body">
                        <h4 ng-repeat="team in user.teamsData">
                            <a href="../../teams/{{team.id}}">
                                <span class="teamStyle"><span class="glyphicon mdi-social-group"></span>
                                {{ team.name }}</span>
                            </a>
                        </h4>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="panel panel-material-teal">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Observed <span class="badge">{{user.observed.length}}</span>
                        </h3>
                    </div>
                    <div class="panel-body">
                        <h4 ng-repeat="obs in user.observedData">
                            <a href="../../users/{{obs.id}}">
                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                                {{ obs.name }} {{ obs.surname }}
                            </a>
                        </h4>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<%@include file="includes/footer.jsp" %>