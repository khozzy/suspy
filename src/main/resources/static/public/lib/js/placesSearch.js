$(document).ready(function() {
    var bootstrapButton = $.fn.button.noConflict();
    $.fn.bootstrapBtn = bootstrapButton;

    var $btn = $('#showSearchResults');

    $btn.on('click', function () {
        $btn.bootstrapBtn('loading');
        showSearchResults();
        //setTimeout(function () {
            $btn.bootstrapBtn('afterload');
        //}, 3000);

        //$btn.bootstrapBtn('reset');
    });
    var showSearchResults = function ()
    {

        var whatSearched = document.forms["searchForm"]["whatSearched"].value;
        var query = document.forms["searchForm"]["query"].value;


        $.getJSON("http://localhost:8080/service/places", function (places) {
            if (places != "") {
                $('#placesList').html('<h2>List of places:</h2>');
                content='';
                $.each( places.content, function( i, place ) {
                    content += '<p>' + place.id;
                    content += ' ' + place.name;
                    content += ' ' + place.adress.street + ' ' + place.adress.houseNumber;
                    content += ' ' + place.adress.city;
                    content += ' ' + place.capacity;
                    + '</p>';
                });
                $(content).appendTo("#placesList");
            }
            else {
                $('#searchResults').html('<h2>No '+ whatSearched +' found.</h2>');
            }
        });

        return false;
    };

    $("#placesList").DataTable();
});

