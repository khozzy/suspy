<%@include file="includes/header.jsp" %>

<div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">New event</h3>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="newEventForm" class="form-horizontal" role="form" method="post">
                        <div class="form-group">
                            <div class="col-sm-2 control-label">
                                <label class="col-sm-2 control-label">Name</label>
                            </div>
                            <div class="col-sm-10">
                                <input class="form-control" placeholder="event name"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-2 control-label">
                                <label class="col-sm-2 control-label">Details</label>
                            </div>
                            <div class="col-sm-10">
                                <textarea class="form-control" placeholder="Details"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-2 control-label">
                                <label class="col-sm-2 control-label">Date</label>
                            </div>
                            <div class="col-sm-10">
                                <input type="date" class="form-control" placeholder="event name"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-2 control-label">
                                <label class="col-sm-2 control-label">Time</label>
                            </div>
                            <div class="col-sm-10">
                                <input type="time" class="form-control" placeholder="event name"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-2 control-label">
                                <label class="col-sm-2 control-label">Place</label>
                            </div>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" placeholder="Place"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-2 control-label">
                                <label class="col-sm-2 control-label">Type</label>
                            </div>
                            <div class="col-sm-10">
                                <input type="text" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-2 control-label">
                                <label class="col-sm-2 control-label">Img</label>
                            </div>
                            <div class="col-sm-10">
                                <span class="btn btn-default btn-file">
                                    <input type="file">
                                </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-12">
                                <button class="btn btn-info pull-right">Add</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
</div>

<%@include file="includes/footer.jsp" %>