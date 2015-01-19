<%@include file="includes/header.jsp" %>

<div class="row">
    <div class="panel panel-material-cyan">
        <div class="panel-heading">
            <h3 class="panel-title">Create new event</h3>
        </div>
        <div class="panel-body">

            <form ng-controller="newEventController" ng-submit="addNewEvent()" class="form-horizontal form-group-material-cyan" role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-9">
                        <input class="form-control" ng-model="eventName" placeholder="Type events name here"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-lg-2 control-label">Details</label>
                    <div class="col-sm-9">
                        <input class="form-control" ng-model="eventDetails" placeholder="Type some extra details about your event"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-lg-2 control-label">Where</label>
                    <div class="col-sm-9">
                        <select
                                class="form-control"
                                ng-model="eventPlace"
                                ng-change="updateTimeslots()"
                                ng-options="obj.name for obj in places track by obj.id"
                                >
                            <option value=''>Where will the event take place</option>
                        </select>
                    </div>
                </div>

                <div class="form-group" ng-show="timeslots">
                    <label class="col-lg-2 control-label">When</label>
                    <div class="col-sm-9">
                        <select
                                class="form-control"
                                ng-model="eventTime"
                                ng-options="(obj.from | date:'dd-MM-yy HH:mm') + ' until ' + (obj.to | date:'dd-MM-yy HH:mm') + ' (' + obj.price + ' PLN)' for obj in timeslots track by obj.id"
                                >
                            <option value=''>Available timeslots in selected location</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-9">

                        <div class="pull-right">
                            <script
                                    src="https://checkout.stripe.com/checkout.js"
                                    class="stripe-button"
                                    data-key="pk_test_6pRNASCoBOKtIshFeQd4XMUh"
                                    data-name="Suspy"
                                    data-description="Event reservation (100 PLN)"
                                    data-currency="EUR"
                                    data-amount="100">
                            </script>

                            <input type="submit" value="Submit" class="btn btn-material-cyan" />
                        </div>

                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<style>
    .form-control {
        color: #9999AA;
    }
    
</style>
<%@include file="includes/footer.jsp" %>