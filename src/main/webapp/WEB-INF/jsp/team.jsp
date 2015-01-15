<%@include file="includes/header.jsp" %>

<div ng-controller="teamProfile" ng-init="init('${team.id}')">
  <div class="jumbotron">
    <div class="row">
      <div class="col-md-12">
        <h1>
          <small>Team</small><br>
          {{ team.name }}
        </h1>
      </div>
    </div>

    <div class="row">
      <div class="col-md-4">
        <h2>
          <small>Leader</small><br>
          <a href="../../users/{{team.leader}}">{{ team.leaderName }} {{ team.leaderSurname }}</a>
        </h2>
      </div>

      <div class="col-md-4">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">Events ({{team.events.length}})</h3>
          </div>
          <div class="panel-body">
            <h4 ng-repeat="event in team.eventsData">
              <a href="../../events/{{event.id}}">{{ event.name }}</a>
            </h4>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <%--<accordion>--%>
          <%--<accordion-group heading="">--%>
            <%--<div class="row text-center">--%>
              <%--<h4 ng-repeat="member in team.membersData">--%>
                <%--<a href="../../users/{{member.id}}">{{ member.name }} {{ member.surname }}</a>--%>
              <%--</h4>--%>
            <%--</div>--%>
          <%--</accordion-group>--%>
        <%--</accordion>--%>

        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">Members ({{team.members.length}})</h3>
          </div>
          <div class="panel-body">
            <h4 ng-repeat="member in team.membersData">
              <a href="../../users/{{member.id}}">{{ member.name }} {{ member.surname }}</a>
            </h4>
          </div>
        </div>
      </div>
    </div>

  </div>
</div>
<%@include file="includes/footer.jsp" %>