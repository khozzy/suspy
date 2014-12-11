<%@include file="includes/header.jsp" %>

<div class="container">
    <div class="row ">
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
            <h3>Popular tags</h3>
            <ul class="list-group">
                <li class="list-group-item">#CrasJustoOdio</li>
                <li class="list-group-item">#DapibusFacilisis in</li>
                <li class="list-group-item">#MorbiLeo</li>
                <li class="list-group-item">#PortaConsectetur ac</li>
                <li class="list-group-item">#VestibulumEros</li>
            </ul>
        </div>
        <div class="col-xs-12 col-sm-9">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#">Upcoming</a>
                </li>
                <li>
                    <a href="#">Popular</a>
                </li>
                <li>
                    <a href="#">Proposed</a>
                </li>
            </ul>
            <!--/row-->
            <div class="tab-content">
                <div class="row panel panel-default">
                    <div class="panel-body">
                            <div class="col-md-4">
                                <div class="media">
                                    <a class="media-left event-img" href="<c:url value='/event/1'/>">
                                        <img src="/public/lib/assets/foot.jpg" alt="...">
                                    </a>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <div class="row">
                                    <div class="col-md-12">
                                        <p><a href="<c:url value='/event/1'/>"><b>Football match</b></a></p>
                                    </div>
                                </div>
                                <div class="row" >
                                    <div class="col-md-4">
                                        <div class="pull-left">
                                            <p class="text-center"><span class="glyphicon glyphicon-user glyph-big"></span><br/>Orlik przy SP nr 10</p>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div>
                                            <p class="text-center"><span class="glyphicon glyphicon-calendar glyph-big"></span><br/>Orlik przy SP nr 10</p>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="pull-right">
                                            <p class="text-center"><span class="glyphicon glyphicon-map-marker glyph-big"></span><br/>Orlik przy SP nr 10</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </div>
                </div>
            </div>
        </div>

        <!--/span-->
        <!--/span-->
    </div>
<%@include file="includes/footer.jsp" %>