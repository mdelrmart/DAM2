<?php
$numero = isset($_GET['numero']) ? $_GET['numero'] : 'missingnumber';
$pattern = '/^\d+$/';
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tabla de Multiplicar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg">
                <div class="card-body">
                    <h2 class="text-center mb-4">Generador de Tablas</h2>
                    <form action="indexgpt.php" method="get" class="mb-3">
                        <div class="mb-3">
                            <label class="form-label">Introduce un número:</label>
                            <input type="text" name="numero" class="form-control" placeholder="Escribe un número..." pattern="\d" title="Introduce un número entero" required>
                        </div>
                        <button class="btn btn-primary w-100" type="submit">Generar</button>
                    </form>
                    <?php
                    if ($numero == 'missingnumber') {
                        die;
                    } elseif (!preg_match($pattern, $numero)) {
                        echo '<div class="alert alert-danger text-center">No has introducido un número válido.</div>';
                        die;
                    }
                    ?>
                    <h3 class="text-center">Tabla del <?=$numero?></h3>
                    <table class="table table-striped text-center">
                        <thead class="table-dark">
                        <tr>
                            <th>Operación</th>
                            <th>Resultado</th>
                        </tr>
                        </thead>
                        <tbody>
                        <?php for ($i = 1; $i <= 10; $i++): ?>
                            <tr>
                                <td><?= "$numero x $i" ?></td>
                                <td><?= $numero * $i ?></td>
                            </tr>
                        <?php endfor; ?>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>