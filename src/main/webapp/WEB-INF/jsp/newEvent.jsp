<%@include file="includes/header.jsp" %>

<div class="row">
            <div class="panel panel-material-cyan">
                <div class="panel-heading">
                    <h3 class="panel-title">New event</h3>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="newEventForm" class="form-horizontal" role="form" method="post">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Name</label>
                            <div class="col-sm-9">
                                <input class="form-control" placeholder="Event name"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">Details</label>
                            <div class="col-sm-9">
                                <textarea class="form-control" placeholder="Details"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">Date</label>
                            <div class="col-sm-9">
                                <input type="date" class="form-control" placeholder="event name"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">Time</label>
                            <div class="col-sm-9">
                                <input type="time" class="form-control" placeholder="event name"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">Place</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="Place"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">Type</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control"/>
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

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-9">
                                <button class="btn btn-material-cyan pull-right">Create event</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
</div>

<%@include file="includes/footer.jsp" %>