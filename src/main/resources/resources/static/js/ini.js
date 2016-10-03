var htmlAlignment
var htmlMatrix;

$(function (e) {
    htmlMatrix = $("#matrix").html();
    htmlAlignment = $("#tablaAlineacion").html();
    $(".alert").show().fadeOut();
    $("#botonSubirArchivo").click(function (e) {
        $(".alert").fadeOut();
        waitingDialog.show("Procesando");
        $.ajax({
            url: "/alignment",
            type: "POST",
            data: new FormData($("#subirArchivo")[0]),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
                $(".alert-success").fadeIn().delay(1000).fadeOut();
                waitingDialog.hide();
                table(data);
                console.log(data);
            },
            error: function (err) {
                $(".alert-danger").fadeIn();
                waitingDialog.hide();
                // Handle upload error
                console.log(err)
                // ...

                var tableMatrix = $("#matrix");
                var tablaAlineacion = $("#tablaAlineacion");

                tableMatrix.hide();
                tablaAlineacion.hide();


                tableMatrix.empty();
                tablaAlineacion.empty();

                tablaAlineacion.html(htmlAlignment);
                tableMatrix.html(htmlMatrix);
                tableMatrix.show();
                tablaAlineacion.show();
            }
        });


    });
});

function table(data) {
    var tb = data.matrix;
    var tableMatrix = $("#matrix");
    var tablaAlineacion = $("#tablaAlineacion");

    tableMatrix.hide();
    tablaAlineacion.hide();


    tableMatrix.empty();
    tablaAlineacion.empty();
    var verticalBases = data.verticalBases;
    var horizontalBases = data.horizontalBases;
    tableMatrix.empty();

    var header = "";
    for (i in verticalBases) {
        var verticalBase = verticalBases[i];
        var sClass = "an-" + verticalBase;
        header += "<td class=\" an " + sClass + "\"> " + verticalBase + " </td> ";
    }
    tableMatrix.append("<tr><td>&nbsp;</td><td>&nbsp;</td>" + header + "</tr>")


    for (var i in tb) {
        var row = tb[i];
        var tds = "";
        for (var j in row) {
            var cell = row[j];
            var id = " id=\"" + i + "-" + j + "\"";
            var data_cell = " data-cell=\"" + i + "-" + j + "\"";
            var columnsHeader = "";
            if (i < 1 && j == 0) {
                columnsHeader = "<td>&nbsp;</td><td " + id + " >" + cell + "</td>";
            } else if (i >= 1 && j == 0) {
                var horizontalBase = horizontalBases[i - 1];
                var sClass = "an-" + horizontalBase;
                columnsHeader = "<td class=\" an " + sClass + "\"> " + horizontalBase + "</td><td " + id + " >" + cell + "</td>";
            } else {
                columnsHeader = "<td " + id + " >" + cell + "</td>";
            }

            tds += columnsHeader;
            //console.log(cell)
        }
        tableMatrix.append("<tr>" + tds + "</tr>")


    }

    var alignment = data.alignment;


    var row1 = "";
    var row2 = "";
    var row3 = "";
    var acm = 0;
    for (var x in alignment) {
        var position = alignment[x];

        var bottomBase = position.rowNitrogenousBase;
        var upperBase = position.columnNitrogenousBase;
        var value = position.value;
        var position = (position.row + 1) + "-" + (position.column + 1);
        var data_cell = " data-cell=\"" + position + "\"";

        acm += value;
        row1 += "<td " + data_cell + " class=\" ali ali-" + position + " an an-" + bottomBase + " \"> " + (bottomBase == "NA" ? "-" : bottomBase) + " </td> ";
        row2 += "<td " + data_cell + " class=\" ali ali-" + position + " an an-" + upperBase + "\"> " + (upperBase == "NA" ? "-" : upperBase) + " </td> ";
        row3 += "<td " + data_cell + " class=\" ali ali-" + position + " score \"> " + value + " </td> ";

        $("#" + position).addClass("cell way way-" + position).attr("data-cell", position);

    }

    tablaAlineacion.append("<tr>" + row2 + "<td rowspan='3' class=\" score \">Score " + acm + "</td></tr>");
    tablaAlineacion.append("<tr>" + row1 + "</tr>");
    tablaAlineacion.append("<tr>" + row3 + "</tr>");


    $("td.ali")
        .mouseenter(function () {
            var data_cell = $(this).attr("data-cell");
            $("td.way-" + data_cell).addClass("cell-active");
            $("td.ali-" + data_cell).addClass("al-cell-active");
        })
        .mouseleave(function () {
            $("td.way").removeClass("cell-active");
            $("td.ali").removeClass("al-cell-active");
        });


    $("td.way")
        .mouseenter(function () {
            var data_cell = $(this).attr("data-cell");
            console.log(data_cell)
            $("td.ali-" + data_cell).addClass("al-cell-active");
        })
        .mouseleave(function () {
            $("td.ali").removeClass("al-cell-active");
        });

    tableMatrix.delay(1000).fadeIn();
    tablaAlineacion.delay(1000).fadeIn();


}


/**
 * Module for displaying "Waiting for..." dialog using Bootstrap
 *
 * @author Eugene Maslovich <ehpc@em42.ru>
 */

var waitingDialog = waitingDialog || (function ($) {
        'use strict';

        // Creating modal dialog's DOM
        var $dialog = $(
            '<div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top:15%; overflow-y:visible;">' +
            '<div class="modal-dialog modal-m">' +
            '<div class="modal-content">' +
            '<div class="modal-header"><h3 style="margin:0;"></h3></div>' +
            '<div class="modal-body">' +
            '<div class="progress progress-striped active" style="margin-bottom:0;"><div class="progress-bar" style="width: 100%"></div></div>' +
            '</div>' +
            '</div></div></div>');

        return {
            /**
             * Opens our dialog
             * @param message Custom message
             * @param options Custom options:
             *                  options.dialogSize - bootstrap postfix for dialog size, e.g. "sm", "m";
             *                  options.progressType - bootstrap postfix for progress bar type, e.g. "success", "warning".
             */
            show: function (message, options) {
                // Assigning defaults
                if (typeof options === 'undefined') {
                    options = {};
                }
                if (typeof message === 'undefined') {
                    message = 'Loading';
                }
                var settings = $.extend({
                    dialogSize: 'm',
                    progressType: '',
                    onHide: null // This callback runs after the dialog was hidden
                }, options);

                // Configuring dialog
                $dialog.find('.modal-dialog').attr('class', 'modal-dialog').addClass('modal-' + settings.dialogSize);
                $dialog.find('.progress-bar').attr('class', 'progress-bar');
                if (settings.progressType) {
                    $dialog.find('.progress-bar').addClass('progress-bar-' + settings.progressType);
                }
                $dialog.find('h3').text(message);
                // Adding callbacks
                if (typeof settings.onHide === 'function') {
                    $dialog.off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
                        settings.onHide.call($dialog);
                    });
                }
                // Opening dialog
                $dialog.modal();
            },
            /**
             * Closes dialog
             */
            hide: function () {
                $dialog.modal('hide');
            }
        };

    })(jQuery);