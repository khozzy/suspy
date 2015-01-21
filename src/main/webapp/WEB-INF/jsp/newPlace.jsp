<%@include file="includes/header.jsp" %>
    <div class="row" ng-controller="newPlace" >
            <div class="panel panel-material-orange">
                <div class="panel-heading">
                    <h3 class="panel-title"><spring:message code="place.form"/></h3>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="addPlaceForm" class="form-horizontal form-group-material-orange" role="form" method="post">
                        <errors/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="name"><spring:message code="place.form.place_name"/></label>
                            <div class="col-sm-9">
                                <input id="name" name="name" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="city"><spring:message code="place.form.city"/></label>
                            <div class="col-sm-9">
                                <input id="city" name="city" type="text" ng-model="asyncSelected" placeholder="Locations loaded via google geo api" typeahead="address for address in getLocation($viewValue)" typeahead-loading="loadingLocations" class="form-control input-md">
                                <i ng-show="loadingLocations" class="glyphicon glyphicon-refresh"></i>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="street"><spring:message code="place.form.street"/></label>
                            <div class="col-sm-9">
                                <input id="street" name="street" type="text" placeholder="" class="form-control input-md">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="houseNumber"><spring:message code="place.form.houseNumber"/></label>
                            <div class="col-sm-9">
                                <input id="houseNumber" name="houseNumber" type="text" placeholder="" class="form-control input-md">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="capacity"><spring:message code="place.form.capacity"/></label>
                            <div class="col-sm-9">
                                <input id="capacity" name="capacity" type="text" placeholder="" class="form-control input-md">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">Img</label>
                            <div class="col-sm-9">
                                    <span class="btn btn-default btn-file">
                                        <input type="file">
                                    </span>
                            </div>
                        </div>

                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title">Timeslots</h4>
                                    </div>
                                    <div class="modal-body">
                                        <!-- ADD TIMESLOT -->
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label" for="timeSlotListBox"><spring:message code='place.form.timeslot.list'/></label>
                                            <div class="col-sm-9">
                                                <textarea readonly class="form-control" id="timeSlotListBox" name="timeSlotListBox"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label" for="price"><spring:message code="place.form.timeslot.cost"/></label>
                                            <div class="col-sm-9">
                                                <input id="price" name="price" type="text" placeholder="" class="form-control input-md">

                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label" for="date_from" ><spring:message code='place.form.timeslot.date_from'/></label>
                                            <div class="col-sm-9">
                                                <p class="input-group">
                                                    <input id="date_from" type="text" class="form-control" datepicker-popup="{{format}}" ng-model="dt1" is-open="opened1" datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
                                                    <span class="input-group-btn">
                                                        <button type="button" class="btn btn-default btn-xs" ng-click="open($event, 'opened1')"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></button>
                                                     </span>
                                                </p>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-2 control-label" for="hour_from"><spring:message code='place.form.timeslot.hour_from'/></label>
                                            <div class="col-sm-9">
                                                <input id="hour_from" name="hour_from" type="time" placeholder="00:00" class="form-control input-md">

                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-2 control-label" for="date_to"><spring:message code='place.form.timeslot.date_to'/></label>
                                            <div class="col-sm-9">
                                                <p class="input-group">
                                                    <input id="date_to" type="text" class="form-control" datepicker-popup="{{format}}" ng-model="dt2" is-open="opened2" datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
                                                    <span class="input-group-btn">
                                                        <button type="button" class="btn btn-default btn-xs" ng-click="open($event, 'opened2')"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></button>
                                                     </span>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label" for="hour_to"><spring:message code='place.form.timeslot.hour_to'/></label>
                                            <div class="col-sm-9">
                                                <input id="hour_to" name="hour_to" type="time" placeholder="00:00" class="form-control input-md">

                                            </div>
                                        </div>


                                        <div class="form-group">
                                            <label class="col-sm-2 control-label" for="addTimeSlotButton"></label>
                                            <div class="col-sm-9">
                                                <input type="button" id="addTimeSlotButton" name="addTimeSlotButton" class="btn btn-material-grey" onClick="addTimeSlotToList(this.form)" Value="<spring:message code='place.form.timeslot.add'/>">
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal-dialog -->
                        </div><!-- /.modal -->
                        <label class="col-sm-2 control-label" > </label>
                            <input type="button" class="btn btn-material-grey" data-toggle="modal" data-target="#myModal" value="Add timeslots">
                        
                        <%-- TODO1: ADD radio button activites!!!! --%>

                        <!--  HIDDEN -->
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="timeSlotList" style="visibility:hidden"><spring:message code='place.form.timeslot.list'/></label>
                            <div class="col-sm-10">
                                <textarea class="form-control" id="timeSlotList" name="timeSlotList" style="visibility:hidden"></textarea>
                            </div>
                        </div>
                        
                        <!--  /HIDDEN -->
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-9">
                                <form:button type="submit" class="btn btn-material-orange pull-right"><spring:message code='place.form.submit'/></form:button>
                                    <%-- TODO: CHANGE Name of finish form button --%>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
    </div>

<script>
    function addTimeSlotToList(form)
    {
        var price = parseFloat(form.price.value);
        if(!(Number(price)===price) || price<=0){
            return;
        }
        if(form.date_from.value.length<=0 || form.date_to.value.length<=0 ||
                form.hour_from.value.length<=0 || form.hour_to.value.length<=0){
            return;
        }
        form.timeSlotList.value += form.date_from.value + " " +  form.hour_from.value + "," + form.date_to.value + " " + form.hour_to.value + ","+ form.price.value;
        form.timeSlotListBox.value += form.date_from.value + " " + form.hour_from.value + " - " + form.date_to.value + " " + form.hour_to.value + ", " + "Cost" + ": "+ form.price.value + "\n";
    }
</script>
<%@include file="includes/footer.jsp" %>