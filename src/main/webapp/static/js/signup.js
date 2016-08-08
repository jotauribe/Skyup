$(document).ready(function() {
    $('#signupButton').click(function() {
        var name = $('#txtName').val();
        var lastname = $('#txtLastname').val();
        var nickname = $('#txtNickname').val();
        var password = $('#txtPassword').val();
        var email = $('#txtEmail').val();
        event.preventDefault();
        
        $.ajax({
            url: 'UserCtr',
            dataType: 'text',
            data: {
            	action: "signup",
                name: name,
                email: email,
                password: password
                
            },
            type: 'POST',
            //contentType: 'application/x-www-form-urlencoded',
            success: function(data) {
                //alert(data);
                if (data === 'Error') {
                    alert('Este NickName ya ha sido Usado');
                }
                else {
                    //alert(data);
                }
                location.href = 'lobby';
            }
        });
    });
});

