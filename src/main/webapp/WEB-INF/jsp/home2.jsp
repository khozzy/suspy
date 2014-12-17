<%@include file="includes/header.jsp" %>
<script src="/public/lib/js/datatables.min.js"></script>
<div class="row">
    <a href="#home">home</a>
    <a href="#friends">friends</a>
    <a href="#error">error</a>
    <div class="row " ng-view="">
    </div>
</div>
<%@include file="includes/footer.jsp" %>