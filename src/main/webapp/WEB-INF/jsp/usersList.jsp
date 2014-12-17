<%@include file="includes/header.jsp" %>

<div class="row">
    <section id="infoUsersList"></section>
    <button type="button" class="btn btn-primary" data-loading-text="Loading..."
            id="showUsersList" autocomplete="off" data-afterload-text="Loaded">Show users list</button>
    <section id="usersList"></section>
</div>

    <script src="/public/lib/js/usersList.js"></script>

<%@include file="includes/footer.jsp" %>