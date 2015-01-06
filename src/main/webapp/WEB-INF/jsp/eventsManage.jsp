<%@include file="includes/header.jsp" %>

<div class="row" ng-controller="manageEvents">
  <h2>Events</h2>


  <accordion close-others="true">
    <accordion-group heading="{{ event.name }}" ng-repeat="event in events track by $index">
      <div class="row text-center">
        <h4>{{event.details}}</h4>
      </div>
      <br>
      <div class="row">
        <div class="col-md-6 text-center">
          <img data-src="holder.js/100x100" alt="..." src="../../public/lib/assets/profile-default.jpg" width="200" height="200">
        </div>
        <div class="col-md-6">
          <p class="h5"><span class="h4">Organizer:</span><a href="../../users/{{event.organizer.id}}">{{event.organizer.name}}</a></p>
          <p class="h5"><span class="h4">Where:</span><a href="../../places/{{event.timeSlot.place.id}}">{{event.timeSlot.place.name}}</a></p>
          <p class="h5"><span class="h4">From:</span>{{event.timeSlot.from| date:'EEEE dd-MM-yy HH:mm'}}</p>
          <p class="h5"><span class="h4">To:</span>{{event.timeSlot.to| date:'EEEE dd-MM-yy HH:mm'}}</p>
        </div>
      </div>
      <br>
      <div class="row text-right">
        <p class="showEventButton"><a href="/events/{{event.id}}" class="btn btn-material-cyan btn-raised" role="button">Show event</a></p>
      </div>
    </accordion-group>
  </accordion>
  
</div>

<style>
  
  .h5 {
    position: relative;
    right: 150px;
  }
  .showEventButton {
    position: relative;
    right: 65px;
    bottom: 10px;
    
  }
</style>

<%@include file="includes/footer.jsp" %>