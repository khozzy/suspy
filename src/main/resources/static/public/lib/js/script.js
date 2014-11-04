$(document).ready(function() {

    var showUsersList = function () {
        $.getJSON("http://localhost:8080/service/users", function (users) {
            if (users != "") {
                $('#usersList').html('<h2>List of users:</h2>');
                $.each( users, function( i, user ) {
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
                    content += '<br/>';
                });
                $(content).appendTo("#usersList");
            }
            else {
                $('#usersList').html('<h2>Users not found.</h2>');
            }
        });

        return false;
    }

    $('#showUsersList').on('click', function () {
        var $btn = $(this).button('loading')
        //sleep(5000);
        showUsersList();
        $btn.button('reset')
    });
});

function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds){
            break;
        }
    }
};

