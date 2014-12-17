<%@include file="includes/header.jsp" %>
       <span ng-controller="SearchController">
            <div class="jumbotron" style="text-align: center">
                <h1>Welcome in Suspy!</h1>
                <p>Create or find teams, events and book places rapidly.</p>
                <p>
                <form class="form-horizontal" name="searchForm" role="search" action="#">
                    <div class="form-group">

                        <div class="col-sm-6 col-lg-offset-3">
                            <input class="form-control"  style="text-align: center"
                                   placeholder="Search for teams, events, places" ng-model="query" />
                        </div>
                        <br><br><br>
                        <label class="btn btn-info">
                            Events
                            <input id="event" type="radio" name="radioButtons" ng-model="selection" ng-change="event" class="radio-inline" style="margin: 0 5px 0 20px;">
                        </label>
                        <label class="btn btn-danger">
                            Places
                        <input id="place" type="radio" name="radioButtons" ng-model="selection" ng-change="place" class="radio-inline" style="margin: 0 5px 0 20px;">
                        </label>
                        <!--<button type="button" class="btn btn-primary btn-lg" data-loading-text="Searching..."
                                id="showSearchResults" autocomplete="off" ng-click="search()">Search</button>-->
                    </div>
                </form>
            </div>

            <section id="searchResults">
                {{search(query)}}
            </section>

            <script src="/public/lib/js/placesSearch.js"></script>
           <script src="/public/lib/js/datatables.min.js"></script>
       </span>
<%@include file="includes/footer.jsp" %>