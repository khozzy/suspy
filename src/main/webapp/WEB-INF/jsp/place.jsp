<%--
  Created by IntelliJ IDEA.
  User: alapinsk
  Date: 2015-01-11
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@include file="includes/header.jsp" %>

<div class="col-xs-12" ng-controller="placeCtrl" ng-init="init('${place.id}')">
  <div class="row clearfix">
    <div class="well well-lg">
      <div class="row">
        <div class="col-md-12 column">
          <div class="row">
            <div class="col-md-7 column">
              <div class="row margin-bot text-right">
                <h1 class="pull-left font-title no-margin">  {{place.name}} </h1>
              </div>
              <br/>
              <div class="row">
                <div class="row">
                  <div class="col-sm-12 column">
                    <div class="row clearfix">
                      <div class="col-sm-3 column">
                        <p class="text-center no-margin"><span class="text-center glyphicon glyphicon-user glyph-big"></span></p>
                        <p class="text-center no-margin "><b>Owner:</b></p>
                        <p class="text-center no-margin font-md"><a href="../../users/{{ place.owner.id }}">{{ place.owner.name }}</a></p>
                      </div>
                      <div class="col-sm-3 column">
                        <p class="text-center no-margin"><span class="text-center glyphicon glyphicon glyphicon-asterisk glyph-big"></span></p>
                        <p class="text-center font-md no-margin"><b>Capacity:</b></p>
                        <p class="text-center font-sm margin-bot-sm">{{ place.capacity }}</p>
                      </div>
                      <div class="col-sm-3 column">
                        <p class="text-center no-margin"><span class="text-center glyphicon glyphicon-map-marker glyph-big"></span></p>
                        <p class="text-center font-md no-margin"><b>Where:</b></p>
                        <p class="text-center font-md">{{ place.address.city }}</p>
                        <p class="text-center font-md">{{ place.address.street }} {{ place.address.houseNumber }}</p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-5 column">
              <div class="media pull-right">
                <div style="height: 305px">
                  <carousel interval="myInterval">
                    <slide ng-repeat="slide in slides" active="slide.active">
                      <img ng-src="{{ slide.image }}" style="height: 300px; width: 500px">
                    </slide>
                  </carousel>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>

  <div class="row clearfix">
    <div class="well">
      <table class="table table-hover">
        <thead>
          <th>Event</th>
          <th>From</th>
          <th>To</th>
        </thead>
        <tr ng-repeat="ts in timeSlot">
          <td><a href="../../events/{{ ts.event.id }}">{{ ts.event.name }}</a></td>
          <td>{{ ts.from | date:'EEEE dd-MM-yy HH:mm' }}</td>
          <td>{{ ts.to | date:'EEEE dd-MM-yy HH:mm' }}</td>
        </tr>
      </table>
    </div>
  </div>

</div>

<style>
  .font-md{
    font-size: 16px;
  }
</style>

<%@include file="includes/footer.jsp" %>
