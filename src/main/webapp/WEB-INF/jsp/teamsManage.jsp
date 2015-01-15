<%--
  Created by IntelliJ IDEA.
  User: Tomasz
  Date: 2015-01-13
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@include file="includes/header.jsp" %>

<div class="row" ng-controller="teamsManage">
  <div class="jumbotron">
    <h2>My Teams</h2>

    <table class="table">
      <thead>
        <tr>
          <th>Team name</th>
          <th>Members</th>
          <th>Events</th>
        </tr>
      </thead>
        <tr ng-repeat="team in teams">
          <td><a href="../../teams/{{team.id}}">{{ team.name }}</a></td>
          <td>{{ team.members.length }}</td>
          <td>{{ team.events.length }}</td>
        </tr>
      <tbody>

      </tbody>
    </table>
  </div>
</div>

<%@include file="includes/footer.jsp" %>