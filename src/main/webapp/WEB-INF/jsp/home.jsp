<%@include file="includes/header.jsp" %>
<span ng-controller="SearchController">
            <div class="row" id="invitation vertical-align">
                <div class="jumbotron vertical-center" style="text-align: center">
                    <h1>Welcome to Suspy!</h1>
                    <p>Create or find events and book places rapidly.</p>
                    <p>
                    <form class="form-horizontal" name="searchForm" role="search">
                        <div class="form-group form-group-material-lightblue" id="searchPanel">

                            <div class="col-sm-6 col-lg-offset-3">
                                <input id="searchInput" class="form-control form-control-material-cyan"  style="text-align: center"
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

            <div class="row" id="listOfResults" ng-cloak>

                <section id="searchResults" ng-switch="form.selection" ng-show="form.query">

                    <div class="progress" ng-hide="round(progressBarVal)==100">
                        <div class="progress-bar progress-bar-material-{{materialColour}}" style="width: {{progressBarVal}}%"></div>
                    </div>
                    <h3 ng-show="round(progressBarVal)==100 && !results">No results found :(</h3>

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
                                            <img data-src="holder.js/100x100" alt="..." src="public/lib/assets/profile-default.jpg">
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
                                            <img data-src="holder.js/100x100" alt="..." src="public/lib/assets/profile-default.jpg">
                                        </div>
                                        <div class="col-md-6">
                                            <p class="h5"><span class="h4">City:</span>  {{place.address.city}}</p>
                                            <p class="h5"><span class="h4">Capacity:</span> {{place.capacity}}</p>
                                            <p class="h5"><span class="h4">Owner:</span><a href="/users/{{place.owner.id}}"> {{place.owner.name}}</a></p>

                                        </div>
                                    </div>
                                    <br>
                                    <div class="row text-center">
                                        <p><a href="places/{{place.id}}" class="btn btn-material-orange btn-raised" role="button">Show place</a> <a href="places/timeslots?id={{place.id}}" class="btn btn-material-grey btn-raised" role="button">Show timeslots</a></p>
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
                    
                            <table id="teams_table" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%" ng-switch-when="teams" ng-show="results">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Team Leader</th>
                                </tr>
                                </thead>

                                <tbody>
                                        <tr ng-repeat="team in results track by $index" ng-show="round(progressBarVal)==100">
                                            <td><a href="teams/{{team.id}}">{{team.name}}</a></td>
                                            <td>{{team.leader.name + ' ' + team.leader.surname}}</td>
                                        </tr>
                                </tbody>
                            </table>
                            <%--
                                end of results of places search
                            --%>

                </section>
            </div>
</span>
<%@include file="includes/footer.jsp" %>