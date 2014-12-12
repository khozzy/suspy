$(document).ready(function() {
    var bootstrapButton = $.fn.button.noConflict();
    $.fn.bootstrapBtn = bootstrapButton;

    var $btn = $('#showUsersList');

    $btn.on('click', function () {
        $btn.bootstrapBtn('loading');
        showUsersList();
        //setTimeout(function () {
            $btn.bootstrapBtn('afterload');
        //}, 3000);

        //$btn.bootstrapBtn('reset');
    });
    var showPlaces = function ()
    {
        $.getJSON("http://localhost:8080/service/places", function (places) {
            if (places != "") {
                $('#placesList').html('<h2>List of places:</h2>');
                //$('#usersList').append('<h2>List of users:</h2>');
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
                $('#placesList').html('<h2>Users not found.</h2>');
            }
        });

        return false;
    }
    var showUsersList = function () {
        $.getJSON("http://localhost:8080/service/users", function (users) {
            if (users != "") {
                $('#usersList').html('<h2>List of users:</h2>');
                //$('#usersList').append('<h2>List of users:</h2>');
                content='';
                $.each( users.content, function( i, user ) {
                    content += '<p>' + user.id;
                    content += ' ' + user.name;
                    content += ' ' + user.surname;
                    content += ' ' + user.email;
                    content += ' ' + user.address.city;
                    content += ' ' + user.address.street;
                    content += ' ' + user.address.houseNumber;
                    content += ' ' + user.roles;
                    content += ' ' + user.about;
                    content += ' ' + user.teams + '</p>';
                });
                $(content).appendTo("#usersList");
            }
            else {
                $('#usersList').html('<h2>Users not found.</h2>');
            }
        });

        return false;
    }
});

function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds){
            break;
        }
    }
}

