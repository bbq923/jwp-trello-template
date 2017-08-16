/**
 * Created by NEXT on 2017. 7. 6..
 */
$(function() {
    var button = $("#signup-button");
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    button.on("click", function(e) {
        e.preventDefault();

        var url = $(".signup-form").attr("action");
        console.log(url);
        var userId = $("#user_id").val();
        var email = $("#email").val();
        var rawPassword = $("#password").val();

        var data = JSON.stringify({
            "userId": userId,
            "email": email,
            "rawPassword": rawPassword
        });
        console.log(data);

        $.ajax({
            method: "post",
            url: url,
            data: data,
            beforeSend: function(req) {
                req.setRequestHeader(header, token)
            },
            contentType: "application/json"
        }).done(function(data, status) {
            location.href = "/";
        }).fail(function(jQueryXhr, status) {
            alert("signup failed");
        })
    })
});