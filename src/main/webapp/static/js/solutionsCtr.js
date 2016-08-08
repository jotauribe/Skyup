$(document).ready(function () {
    var rowCounter = 1, colCounter = 1;
    $.ajax({
        url: '/onebrain/WorkspaceCtr',
        dataType: 'json',
        contentType: "Json",
        data: {
        	action: "getWorkspace"
        },
        success: function(data){
            console.log(data["name"]);
            $("#ctxKeywords").text(data["name"]);
            $("#ctxDescription").text(data["description"]);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(xhr.status);
            console.log(thrownError);
        }
    });
    $.ajax({
        url: 'ContentCtr',
        dataType: 'json',
        data: {
        	action: "getSolutions"
        },
        success: function (data) {
            //console.log("solCtr: " + data[0]["iTitle"]);
            if (data.length > 0) {
                var row = $("#ideasContainer > .row").last();
                for (var problem in data) {
                    if (colCounter == (4 * rowCounter)) {
                        $("#ideasContainer").append("<div class='row'></div>");
                        row = $("#ideasContainer > .row").last();
                        rowCounter++;
                    }
                    row.append("<div class='col-md-3 col-xs-12'><div class = 'problem' ><h4>" + data[problem]["title"] + "</h4><p>" + data[problem]["description"] + "</p><div class = 'buttoms btn-group' role = 'group'><button type = 'button' class = 'btn btn-default'><span class = 'glyphicon glyphicon-remove'></span></button ></div></div></div>");
                    colCounter++;
                }
            }

            //console.log("wsCtr: " + data[0]["keywords"]);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(xhr.status);
            console.log(thrownError);
        }
    });

    $("#btnAddIdea").click(function (event) {
        var keywords = $("#inputKeyWords").val();
        var description = $("#txtDescription").val();
        if (keywords != "" && description != "") {
            $.ajax({
                url: 'ContentCtr',
                type: 'POST',
                dataType: 'text',
                data: {
                	action: "createIdea",
                    iTitle: keywords,
                    iDescription: description
                },
                success: function (data) {
                    console.log(data);
                    $('#newIdea-modalWindow').modal("toggle");
                    row = $("#ideasContainer > .row").last();
                    if (colCounter == (4 * rowCounter)) {
                        $("#ideasContainer").append("<div class='row'></div>");
                        row = $("#ideasContainer > .row").last();
                        rowCounter++;
                    }
                    row.append("<div class='col-md-3 col-xs-12'><div class = 'problem' ><h4>" + keywords + "</h4><p>" + description + "</p><div class = 'buttoms btn-group' role = 'group'><button type = 'button' class = 'btn btn-default'><span class = 'glyphicon glyphicon-remove'></span></button ></div></div></div>");
                    colCounter++;
                }
            });
        }
    });
    $("#evaluate").click(function(event){
        location.href = location.href+"/evaluation";
    });
    //Auto-clamp based on available height
});
