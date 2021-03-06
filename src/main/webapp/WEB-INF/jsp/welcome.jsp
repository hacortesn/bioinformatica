<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:url value="/WEB-INF/jsp/images/ud_logo.jpg" var="logoImg"/>

<html lang="en">
<head>
    <title>Alineación</title>
    <script src="/static/js/jquery.min.js"></script>


    <script src="/static/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <script src="/static/js/ini.js"></script>

    <link rel="stylesheet" href="/static/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="/static/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/estilo.css">

</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <h1><p class="text-center">Bio informática</p></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <ul class="nav nav-pills">
                <li class="active"><a data-toggle="pill" href="#alinear">Alinear</a></li>
                <li><a data-toggle="pill" href="#info">Info</a></li>
                <li><a data-toggle="pill" href="#acerca">Acerca</a></li>
            </ul>
        </div>
    </div>
</div>


<div class="tab-content">
    <div id="alinear" class="tab-pane fade in active container-fluid">

        <div class="row">
            <div class="col-md-5 col-md-offset-1">
                <h2><p class="text-center">Formato FASTA</p></h2>
                <p class="text-justified">
                    es un formato de fichero informático basado en texto, utilizado para representar secuencias bien de
                    ácidos nucleicos, bien de péptido, y en el que los pares de bases o los aminoácidos se representan
                    usando códigos de una única letra. El formato también permite incluir nombres de secuencias y
                    comentarios que preceden a las secuencias en sí.
                    <a class="btn btn-primary" href="https://es.wikipedia.org/wiki/Formato_FASTA"
                       target="_blank" role="link">Más información</a>
                </p>
            </div>
            <div class="col-md-4">
                <h2><p class="text-center">Ejemplo de contenido de Formato FASTA</p></h2>
            <pre>&gt;gi|5524211|gb|AAD44166.1| cytochrome b [<a
                    href="https://es.wikipedia.org/wiki/Elephas_maximus_maximus" target="_blank"
                    title="Elephas maximus maximus">Elephas maximus maximus</a>]
LCLYTHIGRNIYYGSYLYSETWNTGIMLLLITMATAFMGYVLPWGQMSFWGATVITNLFSAIPYIGTNLV
EWIWGGFSVDKATLNRFFAFHFILPFTMVALAGVHLTFLHETGSNNPLGLTSDSDKIPFHPYYTIKDFLG
LLILILLLLLLALLSPDMLGDPDNHMPADPLNTPLHIKPEWYFLFAYAILRSVPNKLGGVLALFLSIVIL
GLMPFLHTSKHRSMMLRPLSQALFWTLTMDLLTLTWIGSQPVEYPYTIIGQMASILYFSIILAFLPIAGX
IENY
                </pre>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <form id="subirArchivo">
                    <div class="form-group">
                        <label for="archivoFasta">Fasta file:</label>
                        <input type="file" id="archivoFasta" name="archivoFasta">
                    </div>

                    <button id="botonSubirArchivo" type="button" class="btn btn-default">Submit</button>
                </form>
            </div>


        </div>
        <div class="row">
            <div class="alert alert-success">
                El archivo ha sido procesado
            </div>
            <div class="alert alert-danger">
                Hay un error procesando el archivo
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

                <h3>Alineación</h3>
                <div class="table-responsive">

                    <table id="tablaAlineacion" class="table table-bordered">
                        <tr>
                            <td>Sin Alineamiento</td>
                        </tr>
                    </table>

                </div>

            </div>

        </div>
        <div class="row">
            <div class="col-md-12">
                <h3>Matriz</h3>
                <div class="table-responsive">

                    <table id="matrix" class="table table-bordered">
                        <tr>
                            <td>Sin Matriz</td>
                        </tr>
                    </table>

                </div>
            </div>
        </div>


    </div>
    <div id="info" class="tab-pane fade container-fluid">
        <div class="row">
            <div class="col-md-3">
                <h2><p class="text-center">Alineamiento</p></h2>
                <p class="text-justified">
                    Un alineamiento de secuencias en bioinformática es una forma de representar y comparar dos o más
                    secuencias o cadenas de ADN, ARN, o estructuras primarias proteicas para resaltar sus zonas de
                    similitud, que podrían indicar relaciones funcionales o evolutivas entre los genes o proteínas
                    consultados. Las secuencias alineadas se escriben con las letras (representando aminoácidos o
                    nucleótidos) en filas de una matriz en las que, si es necesario, se insertan espacios para que las
                    zonas
                    con idéntica o similar estructura se alineen.
                    <a class="btn btn-primary" href="https://es.wikipedia.org/wiki/Alineamiento_de_secuencias"
                       target="_blank" role="link">Más información</a>
                </p>
            </div>
            <div class="col-md-3">
                <h2><p class="text-center">Algoritmo Needleman-Wunsch</p></h2>
                <p class="text-justified">
                    El algoritmo de Needleman-Wunsch sirve para realizar alineamientos globales de dos secuencias. Se
                    suele
                    utilizar en el ámbito de la bioinformática para alinear secuencias de proteínas o de ácidos
                    nucleicos.
                    Fue propuesto por primera vez en 1970, por Saul Needleman y Christian Wunsch. Se trata de un ejemplo
                    típico de programación dinámica. El algoritmo funciona del mismo modo independientemente de la
                    complejidad o longitud de las secuencias y garantiza la obtención del mejor alineamiento.
                    <a class="btn btn-primary" href="https://es.wikipedia.org/wiki/Alineamiento_de_secuencias"
                       target="_blank" role="lin">Más información</a>
                </p>
            </div>
        </div>
    </div>
    <div id="acerca" class="tab-pane fade container-fluid">
        <div class="row">

            <div class="col-md-12">
                <h3 class="text-center">Realizado por:</h3>
                <p class="text-center">
                    Hernán Alfonso Cortés Navarro<br/>
                    Cesar Martínez<br/>
                    Angelica<br/>
                    <br/>
                    <br/>
                </p>
                <p class="text-center">
                    Bionformática<br/>
                    Facultad Tecnológica<br/>
                    Universidad Francisco José de Caldas<br/>
                    Bogotá D.C<br/>
                    2016
                    <img width="100px" src="/static/images/ud_logo.jpg" class="img-responsive center-block"
                         alt="Responsive image">
                </p>
            </div>
        </div>
    </div>
</div>
<div class="modal"><!-- Place at bottom of page --></div>


</body>

</html>

