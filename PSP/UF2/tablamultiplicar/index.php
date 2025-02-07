<?php
    //$cabecera = isset($_GET['cabecera'])?$_GET['cabecera']:'Desconocido';

    $numero = isset($_GET['numero']) ? $_GET['numero'] : 1;

    $pattern = '/^\d+$/';
?>

<html lang="es">
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <title>DAM2</title>
    </head>
    <body>
    <form action="index.php" method="get">
        <label>Introduce el número:</label>
        <input name="numero"/>
        <button class="btn btn-primary" type="submit">Enviar</button>
    </form>
        <?php
            if (!preg_match($pattern, $numero)) {
                echo "<h1>No has introducido un número válido.</h1>";
                die;
            }
        ?>
        <h1>Tabla del <?=$numero?></h1>
        <?php
            for ($i = 1; $i <= 10; $i++) {
                echo $numero. " x " . $i . " = ";
                echo $i * $numero;
                echo "<br>";
            }
        ?>
    </body>
</html>