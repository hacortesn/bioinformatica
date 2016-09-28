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