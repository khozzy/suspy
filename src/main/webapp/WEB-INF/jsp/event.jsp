<%@include file="includes/header.jsp" %>

<div class="col-xs-12">
    <div class="row clearfix">
        <div class="well well-lg">
            <div class="row">
                <div class="col-md-12 column">
                    <div class="row">
                        <div class="col-md-7 column">
                            <div class="row margin-bot text-right">
                                <h1 class="pull-left font-title no-margin">   <c:out value="${event.name}"/></h1>
                            </div>
                            <div class="row">
                                <div class="row">
                                    <div class="col-sm-12 column">
                                        <div class="row clearfix">
                                            <div class="col-sm-4 column">
                                                <p class="text-center no-margin"><span class="text-center glyphicon glyphicon-user glyph-big"></span></p>
                                                <p class="text-center font-md no-margin">12 people</p>
                                                <p class="text-center font-sm margin-bot-sm">1 friend</p>
                                                <p class="text-center no-margin "><b>Organizer:</b></p>
                                                <p class="text-center no-margin font-md"><a href="../../users/${event.organizer.id}">${event.organizer.name}</a></p>
                                            </div>
                                            <div class="col-sm-4 column">
                                                <p class="text-center no-margin"><span class="text-center glyphicon glyphicon-calendar glyph-big"></span></p>

                                                <p class="text-center no-margin font-md"><b>From:</b> <fmt:formatDate value="${event.timeSlot.from}" pattern="EEEE"/></p>
                                                <p class="text-center font-md"><fmt:formatDate value="${event.timeSlot.from}" pattern="dd-MM-yy HH:mm"/></p>

                                                <p class="text-center no-margin font-md"><b>To:</b> <fmt:formatDate value="${event.timeSlot.to}" pattern="EEEE"/></p>
                                                <p class="text-center font-md"><fmt:formatDate value="${event.timeSlot.to}" pattern="dd-MM-yy HH:mm"/></p>
                                            </div>
                                            <div class="col-sm-4 column">
                                                <p class="text-center no-margin"><span class="text-center glyphicon glyphicon-map-marker glyph-big"></span></p>
                                                <p class="text-center font-md no-margin"><b>Where:</b></p>
                                                <p class="text-center font-md"><a href="../../places/${event.timeSlot.place.id}"><c:out value="${event.timeSlot.place.name}"/></a></p>
                                                <button type="button" class="btn btn-sm btn-material-cyan btn-raised">
                                                    Join
                                                </button>
                                                <button type="button" class="btn btn-sm btn-material-cyan btn-raised">
                                                    Invite
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5 column">
                            <div class="media pull-right">
                                <img style="" src="/public/lib/assets/foot.jpg" alt="...">
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="well">
            <div><span class="glyphicon glyphicon-info-sign glyph-big"></span></div>
            <c:out value="${event.details}"/>
        </div>
    </div>

    <div class="row clearfix">
        <div class="well">
            <div role="tabpanel">
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active">
                        <a href="#participants" aria-controls="teams" role="tab" data-toggle="tab">Participants</a>
                    </li>
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="participants" >
                        <div ng-controller="teamProfile" ng-init="init('${event.team.id}')">
                            <accordion close-others="true">
                                <accordion-group  ng-repeat="member in team.membersData">
                                    <h1>{{member}}</h1>
                                </accordion-group>
                            </accordion>
                        </div>

                    </div>
                </div>
            </div>

        </div>
    </div>

</div>

<style>
    .font-md{
        font-size: 16px;
    }
</style>

<%@include file="includes/footer.jsp" %>
