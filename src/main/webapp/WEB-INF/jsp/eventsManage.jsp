<%@include file="includes/header.jsp" %>

<div class="row" ng-controller="manageEvents">
  <h2>Events</h2>


  <accordion close-others="true">
    <accordion-group heading="{{ event.name }}" ng-repeat="event in events">
      Additional description here.
    </accordion-group>
  </accordion>
  
</div>



<%@include file="includes/footer.jsp" %>