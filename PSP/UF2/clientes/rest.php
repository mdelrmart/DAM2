<?php
require 'conexionBD.php'; // Ensure this file is correctly configured

$verbo = $_SERVER['REQUEST_METHOD'];
$uri = $_SERVER['PATH_INFO'] ?? 'test';

$pathInfo = isset($_SERVER['PATH_INFO']) ? trim($_SERVER['PATH_INFO'], '/') : '';
$rutas = $pathInfo == '' ? [] : explode('/', $pathInfo);

function devolverClientes($con) {
    $sql = 'SELECT * FROM clientes';
    $cursor = $con->query($sql);
    $clientes = $cursor->fetchAll(PDO::FETCH_OBJ);
    return $clientes;
}

switch (count($rutas)) {
    case 0:
        echo json_encode(["error" => "No hay datos."]);
        http_response_code(400);
        break;
    case 1:
        if ($rutas[0] == 'clientes' && $verbo == 'GET') {
            $clientes = devolverClientes($con); // FIXED FUNCTION CALL
            http_response_code(200);
            echo json_encode($clientes);
        }
        break;
    default:
        echo json_encode(["error" => "Ruta no válida."]);
        http_response_code(404);
        break;
}
?>
