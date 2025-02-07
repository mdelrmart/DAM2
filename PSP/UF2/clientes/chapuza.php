<?php
require 'conexionBD.php';

//$verbo=$_SERVER['REQUEST_METHOD'];
//$uri=$_SERVER['PATH_INFO']??'test';

$pathInfo=isset($_SERVER['PATH_INFO'])?trim($_SERVER['PATH_INFO'],'/'):'';
$rutas=$pathInfo==''?[]:explode('/',$pathInfo);

$clave = $rutas[0];
$valor = $rutas[1];

switch ($clave) {
    case 'cliente':
        $sql= "SELECT * FROM clientes WHERE codCliente='$valor'";
        break;
    case 'provincia':
        $sql= "SELECT * FROM provincias WHERE codProvincia='$valor'";
        break;
    default:
        $sql= "";
        break;
}


//$sql= "SELECT * FROM ".$clave." WHERE codCliente='$valor'";

$sqlReady = $con->query($sql);

?>
<html>
<head>

</head>
<body>
<?php
while ($fila = $sqlReady->fetch(PDO::FETCH_ASSOC)) {
    echo $fila["nombre"]."<br>";
}
?>
</body>
</html>