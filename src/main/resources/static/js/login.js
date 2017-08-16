// $(function () {
//     var button = $("#login-btn");
//     var token = $("meta[name='_csrf']").attr("content");
//     var header = $("meta[name='_csrf_header']").attr("content");
//
//     button.on("click", function(e) {
//         e.preventDefault();
//
//         var url = $(".login-form").attr("action");
//         var email = $("#email").val();
//         var password = $("#password").val();
//
//         var data = JSON.stringify({
//             "email": email,
//             "password": password
//         });
//         console.log(data);
//
//         $.ajax({
//             method: "post",
//             url: url,
//             data: data,
//             beforeSend: function(req) {
//                 req.setRequestHeader(header, token)
//             }
//             // contentType: "application/json"
//         }).done(function(data, status) {
//             location.href = "/";
//         }).fail(function(jQueryXhr, status) {
//             alert("login failed");
//         })
//     })
// });