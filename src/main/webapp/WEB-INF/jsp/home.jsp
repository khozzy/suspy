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
                                <div id="eventButton" class="btn btn-info btn-raised radio radio-success">
                                    <label style="margin-right:25px">
                                        <span class="glyphicon glyphicon-glass"></span>Events
                                        <input type="radio" ng-model="form.selection"
                                               value="events">
                                        <span class=circle style="margin-left:110px"></span><span class=check style="margin-left:110px"></span>
                                    </label>
                                </div>

                                <div class="btn btn-danger btn-raised radio radio-success">
                                    <label style="margin-right:25px">
                                        <span class="glyphicon glyphicon-home"></span>Places
                                        <input type="radio" ng-model="form.selection"
                                               value="places">
                                        <span class=circle style="margin-left:110px"></span><span class=check style="margin-left:110px"></span>
                                    </label>
                                </div>

                                <div class="btn btn-warning btn-raised radio radio-success">
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
                        <table id="events_table" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%" ng-switch-when="events" ng-show="results">
                            <thead>
                                <tr>
                                    <th class="col-md-4">Name</th>
                                    <th class="col-md-2">Team</th>
                                    <th class="col-md-2">Where</th>
                                    <th class="col-md-2">From</th>
                                    <th class="col-md-2">To</th>
                                </tr>
                            </thead>
                            <tbody>
                                    <tr ng-repeat="event in results track by $index">
                                        <td><a href="events/{{event.id}}">{{event.name}}</a></td>
                                        <td>{{event.team.name}}</td>
                                        <td>{{event.timeSlot.place.name}}</td>
                                        <td>{{event.timeSlot.from| date:'EEEE dd-MM-yy HH:mm'}}</td>
                                        <td>{{event.timeSlot.to| date:'EEEE dd-MM-yy HH:mm'}}</td>
                                    </tr>
                            </tbody>
                        </table>

                    <%--
                        end of results of event search
                    --%>

                    <%--
                        results of places search
                    --%>
                            <table id="places_table" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%" ng-switch-when="places" ng-show="results">
                                <thead>
                                    <tr>
                                        <th class="col-md-3">Name</th>
                                        <th class="col-md-2">City</th>
                                        <th class="col-md-2">Capacity</th>
                                        <th class="col-md-2">Owner</th>
                                    </tr>
                                </thead>

                                <tbody>
                                        <tr ng-repeat="place in results track by $index">
                                                <td><a href="places/{{place.id}}">{{place.name}}</a></td>
                                                <td>{{place.address.city}}</td>
                                                <td>{{place.capacity}}</td>
                                                <td>{{place.owner.name}}</td>

                                        </tr>
                                </tbody>
                            </table>
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