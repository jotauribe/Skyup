$(document).ready(function () {

    $('#loginButton').click(function () {
        event.preventDefault();
        var nick = $('#txtNombre').val();
        var pw = $('#txtPassword').val();
        $.ajax({
            url: 'UserCtr',
            type: 'POST',
            dataType: 'text',
            data: {
            	action: "login",
                nickname: nick,
                password: pw
            },
            success: function (data) {
                console.log("flag from indexCtr.success");
                console.log(data);
                location.href = 'lobby';
            }
        });
    });
});

