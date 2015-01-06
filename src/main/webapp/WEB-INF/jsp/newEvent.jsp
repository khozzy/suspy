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
                    <label for="textArea" class="col-lg-2 control-label">Details</label>
                    <div class="col-sm-9">
                        <textarea class="form-control" rows="2" id="textArea"></textarea>
                        <span class="help-block">Type some extra details about your event.</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="select" class="col-lg-2 control-label">Where</label>
                    <div class="col-sm-9">
                        <select
                                class="form-control"
                                ng-model="eventPlace"
                                ng-change="updateTimeslots()"
                                ng-options="obj.name for obj in places track by obj.id">
                        </select>

                        <span class="help-block">Where will the event take place</span>
                    </div>
                </div>

                <div class="form-group" ng-show="timeslots">
                    <label for="select" class="col-lg-2 control-label">When</label>
                    <div class="col-sm-9">
                        <select
                                class="form-control"
                                ng-model="eventTime"
                                ng-options="(obj.from | date:'dd-MM-yy HH:mm') + ' until ' + (obj.to | date:'dd-MM-yy HH:mm') + ' (' + obj.price + ' PLN)' for obj in timeslots track by obj.id"
                                >
                        </select>

                        <span class="help-block">Available timeslots in selected location</span>
                    </div>
                </div>

               <!-- <div class="form-group col-sm-offset-2" ng-show="eventName && eventPlace && eventTime">
                    <div class="pull-right">
                        <div class="col-sm-12">
                            <h3>{{eventName}}</h3>
                            <p class="text-muted">{{eventPlace.name}}</p>
                            <p class="text-info">
                                <strong>Start time:</strong> {{eventTime.from | date:'dd-MM-yy HH:mm'}}<br/>
                                <strong>End time:</strong> {{eventTime.to | date:'dd-MM-yy HH:mm'}}
                            </p>
                            You will have to pay <strong>{{eventTime.price}} PLN</strong>

                            <%--
                            Card number: 4242-4242-4242-4242
                            Expiry: 12/16 (or any other date in the near future)
                            CVC: 999
                            --%>

                            <div>
                                <br />

                                <form stripe-form="stripeCallback" class="form-horizontal" name="checkoutForm">
                                    <input ng-model="number"
                                           placeholder="Card Number"
                                           payments-format="card"
                                           payments-validate="card"
                                           name="card"
                                           class="form-control"/>

                                    <input ng-model="expiry"
                                           placeholder="Expiration"
                                           payments-format="expiry"
                                           payments-validate="expiry"
                                           name="expiry"
                                           class="form-control"/>

                                    <input ng-model="cvc"
                                           placeholder="CVC"
                                           payments-format="cvc"
                                           payments-validate="cvc"
                                           name="cvc"
                                           class="form-control"/>
                                                                    
                                    
                                </form>

                               
                            </div>
                        </div>
                    </div>
                </div>-->
                <input type="submit" value="Submit" class="btn btn-material-cyan" />
            </form>
        </div>
    </div>
</div>

<%@include file="includes/footer.jsp" %>