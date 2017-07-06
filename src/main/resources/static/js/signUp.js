/**
 * Created by NEXT on 2017. 7. 6..
 */
$(function() {
    var button = $("#signup-button");

    button.on("click", function(e) {
        e.preventDefault();

        var url = $(".signup-form").attr("action");
        console.log(url);
        var userId = $("#user_id").val();
        var email = $("#email").val();
        var password = $("#password").val();

        var data = JSON.stringify({
            "userId": userId,
            "email": email,
            "password": password
        });
        console.log(data);

        $.ajax({
            method: "post",
            url: url,
            data: data,
            contentType: "application/json"
        }).done(function(data, status) {
            location.href = "/";
        }).fail(function(jQueryXhr, status) {
            alert("signup failed");
        })
    })
});