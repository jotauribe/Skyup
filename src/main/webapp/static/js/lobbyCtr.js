$(document).ready(function () {
    var rowCounter = 0, colCounter = 0;
    $.ajax({
        url: 'WorkspaceCtr',
        dataType: 'json',
        data: {
        	action: "getWorkspaces"
        },
        success: function (data) {
            if (data.length > 0) {
                var row = $("#workspaceContainer > .row").last();
                for (var problem in data) {
                    if (colCounter == (4 * rowCounter)) {
                        $("#workspaceContainer").append("<div class='row'></div>");
                        row = $("#workspaceContainer > .row").last();
                        rowCounter++;
                    }
                    row.append("<div class='col-md-3 col-xs-12'><div class = 'problem' ><h4 class='workSpaceName'>" + data[problem]["name"] + "</h4><p>Created by Jhue</><div class = 'buttoms btn-group' role = 'group'><button type = 'button' class = 'btn btn-default'><span class = 'glyphicon glyphicon-remove'></span></button ></div><div class='labels btn-group' role='group'><span>Problems: <span class='badge'>5</span></span><span>Ideas: <span class='badge'>3</span></span></div></div></div>");
                    colCounter++;
                }
            }
            $('.workSpaceName').click(function (e) {
                e.preventDefault();
                console.log($(this).text());
                var currentWorkspace = $(this).text();
                $.ajax({
                    url: 'WorkspaceCtr',
                    data: {
                    	action: "none",
                        workspace: currentWorkspace
                    },
                    dataType: 'text',
                    success: function (data) {
                    	//alert("2: "+data);
                        console.log("success from lobbyCtr.wsSetter: " + data);
                        location.href = 'workspaces/'+data;
                    }
                });
            });
//            alert(data[0].name);
        }
    });

    $("#btnNewWorkspace").click(function () {
        var name = $("#inputName").val(), ctxName = $("#inputKeyWords").val(), ctxDescription = $("#txtDescription").val();
        if (ctxName != "" && name != "" && ctxDescription != "") {
            $.ajax({
                url: 'WorkspaceCtr',
                type: 'POST',
                data: {
                	action: "create",
                    wName: name,
                    ctxName: ctxName,
                    wDescription: ctxDescription
                },
                dataType: 'text',
                success: function (data) {
                    $('#newWorkspace-modalWindow').modal("toggle");
                    row = $("#workspaceContainer > .row").last();
                    if (colCounter == (4 * rowCounter)) {
                        $("#workspaceContainer").append("<div class='row'></div>");
                        row = $("#workspaceContainer > .row").last();
                        rowCounter++;
                    }
                    row.append("<div class='col-md-3 col-xs-12'><div class = 'problem' ><h4>" + name + "</h4><div class = 'buttoms btn-group' role = 'group'><button type = 'button' class = 'btn btn-default'><span class = 'glyphicon glyphicon-remove'></span></button ></div></div></div>");
                    colCounter++;
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    console.log(xhr.status);
                    console.log(thrownError);
                }
            });
        }
    });

});
