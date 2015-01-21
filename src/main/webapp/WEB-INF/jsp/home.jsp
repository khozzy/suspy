<%@include file="includes/header.jsp" %>
<span ng-controller="SearchController">
            <div class="row" id="invitation">
                <div class="jumbotron text-center" style="text-align: center;">
                    <h1>Welc<img src="public/lib/assets/logo.svg" height="45px">me to Suspy!</h1>
                    <p>Create or find events and book places rapidly.</p>
                    <p>
                    <form class="form-horizontal" name="searchForm" role="search">
                        <div class="form-group form-group-material-lightblue" id="searchPanel">

                            <div class="col-sm-6 col-lg-offset-3 text-center">
                                <input id="searchInput" class="form-control form-control-material-cyan text-center"
                                       placeholder="Search for events, places, teams" ng-model="form.query" autofocus/>
                            </div>
                            <br><br><br>

                                        <label id="eventsLabel" class="btn btn-material-cyan shadow-z-5">
                                            <input type="radio" id="eventsSelected" name="selection" ng-model="form.selection"
                                                   value="events" autocomplete="off" ng-click="searchButtons()">
                                            <span class="glyphicon glyphicon-glass"></span>&nbsp;Events
                                        </label>

                                        <label id="placesLabel" class="btn btn-material-orange">
                                            <input type="radio" id="placesSelected" name="selection" ng-model="form.selection"
                                                   value="places" autocomplete="off" ng-click="searchButtons()">
                                            <span class="glyphicon glyphicon-home"></span>&nbsp;Places

                                        </label>

                                        <label id="teamsLabel" class="btn btn-material-pink">
                                            <input type="radio" id="teamsSelected" name="selection" ng-model="form.selection"
                                                   value="teams" autocomplete="off" ng-click="searchButtons()">
                                            <span class="glyphicon mdi-social-group"></span>&nbsp;Teams
                                        </label>

                            <!--<button type="button" class="btn btn-primary btn-lg" data-loading-text="Searching..."
                                    id="showSearchResults" autocomplete="off" ng-click="search()">Search</button>-->
                        </div>

                    </form>

                </div>
                
            </div>
    
             <div class="row text-center animated" ng-hide="form.query" ng-controller="homeAdvisorCtrl">
                 <div class="col-md-4">
                     <div class="well well-lg">
                         <carousel interval="myInterval2">
                             <slide ng-repeat="slide in slides2" active="slide.active">
                                 <img ng-src="{{ slide.image }}" style="height: 200px; width: 350px">
                             </slide>
                         </carousel>
                         <h2>Take all from life</h2>
                         <h4>Lorem ipsum inne sripsum</h4>
                     </div>
                 </div>
                 <div class="col-md-4">
                     <div class="well well-lg">
                         <carousel interval="myInterval1">
                             <slide ng-repeat="slide in slides1" active="slide.active">
                                 <img ng-src="{{ slide.image }}" style="height: 200px; width: 350px">
                             </slide>
                         </carousel>
                         <h2>Always have fun</h2>
                         <h4>Lorem ipsum inne sripsum</h4>
                     </div>
                 </div>
                 <div class="col-md-4">
                     <div class="well well-lg">
                         <carousel interval="myInterval3">
                             <slide ng-repeat="slide in slides3" active="slide.active">
                                 <img ng-src="{{ slide.image }}" style="height: 200px; width: 350px">
                             </slide>
                         </carousel>
                         <h2>Try something new</h2>
                         <h4>Lorem ipsum inne sripsum</h4>
                     </div>
                 </div>
             </div>

            <div class="row" id="listOfResults" ng-cloak>

                <section id="searchResults" ng-switch="form.selection" ng-show="form.query">

                    <div class="progress" ng-hide="round(progressBarVal)==100">
                        <div class="progress-bar progress-bar-material-{{materialColour}}" style="width: {{progressBarVal}}%"></div>
                        <div class="filler"></div>
                    </div>
                    <h3 ng-show="round(progressBarVal)==100 && !results" class="filler">No results found :(</h3>

                    <%--
                        results of event search
                    --%>
                    <div class="row" id="eventsResults" ng-switch-when="events" ng-show="results">
                        <div class="col-sm-12 col-md-6" ng-repeat="event in results track by $index" ng-show="round(progressBarVal)==100">
                            <div class="thumbnail">
                                <div class="caption">
                                    <div class="row text-center">
                                        <h4><a href="events/{{event.id}}">{{event.name}}</a></h4>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="col-md-6 text-center">
                                            <img data-src="holder.js/100x100" alt="..." src="/public/lib/assets/profile-default.jpg">
                                        </div>
                                        <div class="col-md-6">
                                            <p class="h5"><span class="h4">Organizer:</span><a href="/users/{{event.organizer.id}}">{{event.organizer.name}}</a></p>
                                            <p class="h5"><span class="h4">Where:</span><a href="/places/{{event.timeSlot.place.id}}">{{event.timeSlot.place.name}}</a></p>
                                            <p class="h5"><span class="h4">From:</span>{{event.timeSlot.from| date:'EEEE dd-MM-yy HH:mm'}}</p>
                                            <p class="h5"><span class="h4">To:</span>{{event.timeSlot.to| date:'EEEE dd-MM-yy HH:mm'}}</p>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row text-center">
                                        <p><a href="/events/{{event.id}}" class="btn btn-material-cyan btn-raised" role="button">Show event</a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--
                        end of results of event search
                    --%>

                    <%--
                        results of places search
                    --%>
                    <div class="row" id="placesResults" ng-switch-when="places" ng-show="results">
                        <div class="col-sm-12 col-md-6" ng-repeat="place in results track by $index" ng-show="round(progressBarVal)==100">
                            <div class="thumbnail">
                                <div class="caption">
                                    <div class="row text-center">
                                        <h4><a href="/places/{{place.id}}">{{place.name}}</a></h4>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="col-md-6 text-center">
                                            <img data-src="holder.js/100x100" alt="..." src="/public/lib/assets/profile-default.jpg">
                                        </div>
                                        <div class="col-md-6">
                                            <p class="h5"><span class="h4">City:</span>  {{place.address.city}}</p>
                                            <p class="h5"><span class="h4">Capacity:</span> {{place.capacity}}</p>
                                            <p class="h5"><span class="h4">Owner:</span><a href="/users/{{place.owner.id}}"> {{place.owner.name}}</a></p>

                                        </div>
                                    </div>
                                    <br>
                                    <div class="row text-center">
                                        <p><a href="places/{{place.id}}" class="btn btn-material-orange btn-raised" role="button">Show place</a>
                                            <input type="button" class="btn btn-material-grey btn-raised" data-toggle="modal" 
                                                   data-target="#myModal{{place.id}}" value="Show timeslots" ng-click="showTimeslots(place.id)"></p>

                                        <div class="modal fade" id="myModal{{place.id}}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                        <h4 class="modal-title">Timeslots of {{place.name}}</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="row clearfix">
                                                                <table class="table table-hover" ng-show="timeslots">
                                                                    <thead>
                                                                    <th>From</th>
                                                                    <th>To</th>
                                                                    <th>Price</th>
                                                                    <th>Event</th>
                                                                    </thead>
                                                                    <tr ng-repeat="timeslot in timeslots track by $index">
                                                                        <td>{{ timeslot.from | date:'EEEE dd-MM-yy HH:mm' }}</td>
                                                                        <td>{{ timeslot.to | date:'EEEE dd-MM-yy HH:mm' }}</td>
                                                                        <td ng-hide="timeslot.event">{{ timeslot.price }}</td>
                                                                        <td ng-show="timeslot.event">-</td>
                                                                        <td ng-show="timeslot.event"><a href="../../events/{{ timeslot.event }}">{{ timeslot.event }}</a></td>
                                                                        <td ng-hide="timeslot.event"><a href='../../events/new?place={{ place.id }}&timeslot={{timeslot.id}}' class="btn btn-material-cyan">Book</a></td>
                                                                    </tr>
                                                                </table>
                                                            <h5 ng-hide="timeslots">No timeslots found.</h5>
                                                        </div>
                                                    </div>

                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                    </div>
                                                </div><!-- /.modal-content -->
                                            </div><!-- /.modal-dialog -->
                                        </div><!-- /.modal -->
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <%--
                            end of results of places search
                        --%>

                    <%--
                    results of teams search
                --%>
                    <div class="row" id="teamsResults" ng-switch-when="teams" ng-show="results">
                        <div class="col-sm-12 col-md-6" ng-repeat="team in results track by $index" ng-show="round(progressBarVal)==100">
                            <div class="thumbnail">
                                <div class="caption">
                                    <div class="row text-center">
                                        <h4><a href="teams/{{team.id}}">{{team.name}}</a></h4>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="col-md-6 text-center">
                                            <img data-src="holder.js/100x100" alt="..." src="/public/lib/assets/profile-default.jpg">
                                        </div>
                                        <div class="col-md-6">
                                            <p class="h5"><span class="h4">Leader:</span><a href="/users/{{team.leader.id}}">{{team.leader.name}}</a></p>
                                            <p class="h5"><span class="h4">Members:</span><a href="#">{{team.members}}</a></p>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row text-center">
                                        <p><a href="/teams/{{team.id}}" class="btn btn-material-pink btn-raised" role="button">Show team</a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--
                        end of results of teams search
                    --%>

                </section>
            </div>
</span>
<%@include file="includes/footer.jsp" %>