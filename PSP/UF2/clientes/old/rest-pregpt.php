<?php
require 'conexionBD.php';

$verbo=$_SERVER['REQUEST_METHOD'];
$uri=$_SERVER['PATH_INFO']??'test';

$pathInfo=isset($_SERVER['PATH_INFO'])?trim($_SERVER['PATH_INFO'],'/'):'';
$rutas=$pathInfo==''?[]:explode('/',$pathInfo);

function devolverClientes($con){
    $sql='SELECT * FROM clientes';
    $cursor=$con->query($sql);
    $clientes=$cursor->fetchAll(PDO::FETCH_OBJ);
    return $clientes;
}

switch (count($rutas)) {
    case 0:
        echo('No hay datos.');
        break;
    case 1:
        if ($rutas[0]=='clientes') {
            if ($verbo=='GET') {
                $clientes=getClientes($con);
                http_response_code(200);
                echo json_encode($clientes);
            }
        }
        break;
    default:
        break;
}



//$sql= "SELECT * FROM ".$clave." WHERE codCliente='$valor'";

//$sqlReady = $con->query($sql);

?>
<html>
<head>

</head>
<body>

</body>
</html>