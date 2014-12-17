<%@include file="includes/header.jsp" %>
<span ng-controller="SearchController">
            <div class="row" id="invitation">
                <div class="jumbotron" style="text-align: center">
                    <h1>Welcome in Suspy!</h1>
                    <p>Create or find teams, events and book places rapidly.</p>
                    <p>
                    <form class="form-horizontal" name="searchForm" role="search" action="#">
                        <div class="form-group">

                            <div class="col-sm-6 col-lg-offset-3">
                                <input class="form-control"  style="text-align: center"
                                       placeholder="Search for events, places, teams" ng-model="query" />
                            </div>
                            <br><br><br>
                            <label class="btn btn-info">
                                <span class="glyphicon glyphicon-glass"></span>&nbsp; Events
                                <input id="event" type="radio" name="radioButtons" ng-model="selection" value="event" class="radio-inline" style="margin: 0 5px 0 20px;">
                            </label>
                            <label class="btn btn-danger">
                                <span class="glyphicon glyphicon-home"></span>&nbsp; Places
                            <input id="place" type="radio" name="radioButtons" ng-model="selection" value="place" class="radio-inline" style="margin: 0 5px 0 20px;">
                            </label>
                            <label class="btn btn-warning">
                                <span class="glyphicon glyphicon-star-empty"></span>&nbsp; Teams
                                <input id="team" type="radio" name="radioButtons" ng-model="selection" value="team" class="radio-inline" style="margin: 0 5px 0 20px;">
                            </label>
                            <!--<button type="button" class="btn btn-primary btn-lg" data-loading-text="Searching..."
                                    id="showSearchResults" autocomplete="off" ng-click="search()">Search</button>-->
                        </div>
                    </form>
                </div>
            </div>

            <div class="row" id="listOfResults">
                <section id="searchResults" ng-switch="selection" ng-show="query">


                    <h3 ng-hide="results">No results found :(</h3>

                    <%--
                        results of event search
                    --%>
                        <table id="events_table" class="display" cellspacing="0" width="100%" ng-switch-when="event" ng-show="results">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Team</th>
                                    <th>Where</th>
                                    <th>From</th>
                                    <th>To</th>
                                </tr>
                            </thead>
                            <tbody>
                                    <tr ng-repeat="event in results">
                                        <td>{{event.name}}</td>
                                        <td>{{event.team.name}}</td>
                                        <td>{{event.timeSlot.place.name}}</td>
                                        <td>{{event.timeSlot.from}}</td>
                                        <td>{{event.timeSlot.to}}</td>
                                    </tr>
                            </tbody>
                        </table>

                    <%--
                        end of results of event search
                    --%>

                    <%--
                        results of places search
                    --%>
                            <table id="places_table" class="display" cellspacing="0" width="100%" ng-switch-when="place" ng-show="results">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>City</th>
                                        <th>Capacity</th>
                                        <th>Owner</th>
                                    </tr>
                                </thead>

                                <tbody>
                                        <tr ng-repeat="place in results">
                                            <td>{{place.name}}</td>
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
                            <table id="teams_table" class="display" cellspacing="0" width="100%" ng-switch-when="team" ng-show="results">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Team Leader</th>
                                </tr>
                                </thead>

                                <tbody>
                                        <tr ng-repeat="team in results">
                                            <td>{{team.name}}</td>
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
           <script src="/public/lib/js/datatables.min.js"></script>
<%@include file="includes/footer.jsp" %>