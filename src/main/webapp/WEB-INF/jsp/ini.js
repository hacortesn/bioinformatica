$(function (e) {
    $("#botonSubirArchivo").click(function (e) {
        console.log("sdfgsdfgsdfgsdfg")

        $.ajax({
            url: "http://localhost:8080/alignment",
            type: "POST",
            data: new FormData($("#subirArchivo")[0]),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
                table(data)
                console.log(data)
            },
            error: function (err) {
                // Handle upload error
                console.log(err)
                // ...
            }
        });


    });
});

function table(data) {
    var tb = data.matrix;
    var table = $("#tablita");
    var sequence1 = data.sequence1;
    var sequence2 = data.sequence2;
    table.empty();

    var header = "";
    for (i in sequence1) {
        header += "<td>" + sequence1[i] + "</td>";
    }
    table.append("<tr><td>&nbsp;</td><td>&nbsp;</td>" + header + "</tr>")


    for (var i in tb) {
        var row = tb[i];
        var tds = "";
        for (var j in row) {
            var cell = row[j];
            var id = "id=\"" + i + "," + j + "\"";
            var columnsHeader = "";
            if (i < 1 && j == 0) {
                columnsHeader = "<td>&nbsp;</td><td " + id + " >" + cell + "</td>";
            } else if (i >= 1 && j == 0) {
                columnsHeader = "<td>" + sequence2[i - 1] + "</td><td " + id + " >" + cell + "</td>";
            } else {
                columnsHeader = "<td " + id + " >" + cell + "</td>";
            }

            tds += columnsHeader;
            //console.log(cell)
        }
        table.append("<tr>" + tds + "</tr>")

    }
}