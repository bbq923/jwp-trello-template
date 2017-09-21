var BOARDS = (function (window){

	 'use strict';

	function init(){

    	$("#modal").modal();
		$(".board-list").on("click", ".board", gotoBoard);
		$(".add-board-btn").on("click", showCreateBoardForm);
		$(".add-board-form .save").on("click", createNewBoard);
		$(".close-moadl").on("click", closeModal);

	}

	function showCreateBoardForm(){

		$('#modal').modal('open');

	}

	function createNewBoard(){

		var boardName = $(".board-name").val();
		var url = $(".add-board-form").attr("action");
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

		if(boardName == ""){
			$(".warning").css("display","block");
			return;
		}


		var data = JSON.stringify({
			"name": boardName
		});

		console.log(header);
		console.log(token);

		$.ajax({
			method: "post",
			url: url,
			data: data,
            beforeSend: function(req) {
                req.setRequestHeader(header, token)
            },
			contentType: "application/json"
		}).done(function(){

            $(".warning").css("display","none");
            var str = Template.board.replace(/\{\{input-value\}\}/gi,boardName);
            $(".board-name").val("");
            $("#modal").modal("close");
            $(".board-list").append(str);

		}).fail(function(){
			alert("failed creating a board");
		});

    }

	function gotoBoard(){

		window.location.href = ("board.html");

	}

	function closeModal(){

        $("#modal").modal("close");

	}


	return {
		"init" : init
	}
})(window);

$(function(){
    BOARDS.init();
});
