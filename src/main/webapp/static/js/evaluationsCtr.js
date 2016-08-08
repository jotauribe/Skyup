$(document).ready(function () {
    $.ajax({
        url: 'ContentCtr',
        dataType: 'json',
        data: {
        	action: "getSolutions"
        },
        success: function (data) {
            console.log("evaluationsCtr: " + data[0]["title"]);
            if (data.length > 0) {
                var tbody = $("#tblEvaluation > tbody");
                for (var row in data) {
                    var tableRow;
                    var iArray = {
                        "0": "Idea",
                        "1": "Time",
                        "2": "Avaliability",
                        "3": "Cost",
                        "4": "Barriers",
                        "5": "Total"

                    };
                    for (var i = 0; i < 6; i++) {
                        tableRow = $("#tblEvaluation > tbody > tr").last();
                        if (i == 0) {
                            tbody.append("<tr class='something'><tr>");
                            tableRow = $("#tblEvaluation > tbody > tr").last();
                            tableRow.append("<td class='" + iArray[i] + "'>" + data[row]["title"] + "</td>");
                        }
                        else {
                            if (i < 5) {
                                tableRow.append("<td class='" + iArray[i] + "'><select class='btn btn-default'><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td>");
                            }
                            else {
                                tableRow.append("<td class='" + iArray[i] + "'>5</td>");
                            }
                        }
                    }

                }
            }
            $("tr.something").remove();
            $("#tblEvaluation td").change(function (event) {
                var parent = ($(event.target)).parent().parent();
                var indicators = (parent.find("select > option:selected")).slice(0, 4);
                var sumatory = 0;
//                console.log(indicators);
                for (var i = 0; i < 4; i++) {
                    sumatory = sumatory + parseInt($(indicators[i]).val());
//                    console.log(sumatory);
                }
                $(parent.find("td.Total").last()).text(sumatory);
//                console.log(sumatory);
                //                        console.log(sumatory);
            });
//            console.log("wsCtr: " + data[0]["keywords"]);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(xhr.status);
            console.log(thrownError);
        }
    });
    $("#Save").click(function (event) {
        ($("tr").slice(1)).each(function () {
            var ideaKeywords = $($(this).find("td.Idea")).text();
//            console.log(ideaKeywords);
            var ideaEvaluation = $($(this).find("td.Total")).text();
//            console.log($($(this).find("td.Total")).text());
            $.ajax({
                url: 'ContentCtr',
                type: 'POST',
                dataType: 'text',
                data: {
                    ideaKeywords: ideaKeywords,
                    ideaEvaluation: ideaEvaluation
                },
                success: function (data) {
                    console.log(data);
                }

            });
        });
//        console.log(tbody);

//        for(;;){
//            
//        }
    });
});


