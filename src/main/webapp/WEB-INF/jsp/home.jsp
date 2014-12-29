<%@include file="includes/header.jsp" %>
<span ng-controller="SearchController">
            <div class="row" id="invitation">
                <div class="jumbotron" style="text-align: center">
                    <h1>Welcome to Suspy!</h1>
                    <p>Create or find teams, events and book places rapidly.</p>
                    <p>
                    <form class="form-horizontal" name="searchForm" role="search">
                        <div class="form-group">

                            <div class="col-sm-6 col-lg-offset-3">
                                <input id="searchInput" class="form-control"  style="text-align: center"
                                       placeholder="Search for events, places, teams" ng-model="form.query" />
                            </div>
                            <br><br><br>
                                <div id="eventButton" class="btn btn-material-cyan btn-raised radio radio-success">
                                    <label style="margin-right:25px">
                                        <span class="glyphicon glyphicon-glass"></span>Events
                                        <input type="radio" ng-model="form.selection"
                                               value="events">
                                        <span class=circle style="margin-left:110px"></span><span class=check style="margin-left:110px"></span>
                                    </label>
                                </div>

                                <div class="btn btn-material-lightgreen btn-raised radio radio-success">
                                    <label style="margin-right:25px">
                                        <span class="glyphicon glyphicon-home"></span>Places
                                        <input type="radio" ng-model="form.selection"
                                               value="places">
                                        <span class=circle style="margin-left:110px"></span><span class=check style="margin-left:110px"></span>
                                    </label>
                                </div>

                                <div class="btn btn-material-pink btn-raised radio radio-success">
                                    <label style="margin-right:25px">
                                        <span class="glyphicon mdi-social-group"></span>Teams
                                        <input type="radio" ng-model="form.selection"
                                               value="teams">
                                        <span class=circle style="margin-left:105px"></span><span class=check style="margin-left:105px"></span>
                                    </label>
                                </div>
                            <!--<button type="button" class="btn btn-primary btn-lg" data-loading-text="Searching..."
                                    id="showSearchResults" autocomplete="off" ng-click="search()">Search</button>-->
                        </div>

                    </form>

                </div>
            </div>

            <div class="row" id="listOfResults" ng-cloak>
                <section id="searchResults" ng-switch="form.selection" ng-show="form.query">

                    <h3 ng-hide="results">No results found :(</h3>

                    <%--
                        results of event search
                    --%>

                    <div class="row" id="eventsResults" ng-switch-when="events" ng-show="results">
                        <div class="col-sm-12 col-md-6" ng-repeat="event in results track by $index">
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
                                            <p class="h5"><span class="h4">Team:</span>{{event.team.name}}</p>
                                            <p class="h5"><span class="h4">Where:</span>{{event.timeSlot.place.name}}</p>
                                            <p class="h5"><span class="h4">From:</span>{{event.timeSlot.from| date:'EEEE dd-MM-yy HH:mm'}}</p>
                                            <p class="h5"><span class="h4">To:</span>{{event.timeSlot.to| date:'EEEE dd-MM-yy HH:mm'}}</p>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row text-center">
                                        <p><a href="events/{{event.id}}" class="btn btn-material-cyan btn-raised" role="button">Show event</a></p>
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
                        <div class="col-sm-12 col-md-6" ng-repeat="place in results track by $index">
                            <div class="thumbnail">
                                <div class="caption">
                                    <div class="row text-center">
                                        <h4><a href="places/{{place.id}}">{{place.name}}</a></h4>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="col-md-6 text-center">
                                            <img data-src="holder.js/100x100" alt="..." src="public/lib/assets/profile-default.jpg">
                                        </div>
                                        <div class="col-md-6">
                                            <p class="h5"><span class="h4">City:</span>  {{place.address.city}}</p>
                                            <p class="h5"><span class="h4">Capacity:</span> {{place.capacity}}</p>
                                            <p class="h5"><span class="h4">Owner:</span> {{place.owner.name}}</p>

                                        </div>
                                    </div>
                                    <br>
                                    <div class="row text-center">
                                        <p><a href="places/{{place.id}}" class="btn btn-material-lightgreen btn-raised" role="button">Show place</a> <a href="places/timeslots?id={{place.id}}" class="btn btn-material-grey btn-raised" role="button">Show timeslots</a></p>
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
                                        <tr ng-repeat="team in results track by $index">
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