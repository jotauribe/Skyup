$(document).ready(function () {
    var rowCounter = 1, colCounter = 1;
    $.ajax({
        url: '/WorkspaceCtr',
        dataType: 'json',
        data: {
        	action: "getWorkspace"
        },
        success: function(data){
            console.log(data);
            $("#ctxKeywords").text(data["name"]);
            $("#ctxDescription").text(data["description"]);
        }
    });
    $.ajax({
        url: 'ContentCtr',
        dataType: 'json',
        success: function (data) {
            console.log("wsCtr: " + data[0]["name"]);
            if (data.length > 0) {
                var row = $("#problemContainer > .row").last();
                for (var problem in data) {
                    if (colCounter == (4 * rowCounter)) {
                        $("#problemContainer").append("<div class='row'></div>");
                        row = $("#problemContainer > .row").last();
                        rowCounter++;
                    }
                    row.append("<div class='col-md-3 col-xs-12'><div class = 'problem' ><h4>" + data[problem]["keywords"] + "</h4><p>" + data[problem]["description"] + "</p><div class = 'buttoms btn-group' role = 'group'><button type = 'button' class = 'btn btn-default'><span class = 'glyphicon glyphicon-remove'></span></button ></div></div></div>");
                    colCounter++;
                }
                $(".problem > h4").click(function (event) {
                    var problem = $(event.target).text();
                    $.ajax({
                        url: 'http://localhost/skyup/php/problemSetter.php',
                        data: {
                            problem: problem
                        },
                        dataType: 'text',
                        success: function () {
                            location.href = "solutions.html";
                        }
                    });
                });
            }

            console.log("wsCtr: " + data[0]["keywords"]);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(xhr.status);
            console.log(thrownError);
        }
    });

    $("#btnAddProblem").click(function (event) {
        var keywords = $("#inputKeyWords").val();
        var description = $("#txtDescription").val();
        if (keywords != "" && description != "") {
            $.ajax({
                url: 'ContentCtr',
                type: 'POST',
                dataType: 'text',
                data: {
                    keywords: keywords,
                    description: description
                },
                success: function (data) {
                    console.log(data);
                    $('#newProblem-modalWindow').modal("toggle");
                    row = $("#problemContainer > .row").last();
                    if (colCounter == (4 * rowCounter)) {
                        $("#problemContainer").append("<div class='row'></div>");
                        row = $("#problemContainer > .row").last();
                        rowCounter++;
                    }
                    row.append("<div class='col-md-3 col-xs-12'><div class = 'problem' ><h4>" + keywords + "</h4><p>" + description + "</p><div class = 'buttoms btn-group' role = 'group'><button type = 'button' class = 'btn btn-default'><span class = 'glyphicon glyphicon-remove'></span></button ></div></div></div>");
                    colCounter++;
                }
            });
        }
    });
    
    $("#btnEditContext").click(function(){
       //TODO 
    });
});


