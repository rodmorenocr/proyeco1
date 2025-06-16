<?php
//  Sirve nóminas y fotos de perfil

// --- CONFIGURACIÓN ---
$db_path = __DIR__ . '/user/auraBoutique.db';
$file_base_path = $_SERVER['DOCUMENT_ROOT'] . '/wp-content/uploads/datos_empleados';

// --- LÓGICA DEL SCRIPT ---
$type = $_GET['type'] ?? '';

if ($type === 'profile') {
    // --- LÓGICA PARA FOTOS DE PERFIL ---
    $user = $_GET['user'] ?? '';
    if (empty($user)) {
        header("HTTP/1.0 400 Bad Request");
        exit();
    }
    // Asumimos que la foto de perfil siempre se llama 1.jpg
    $rutaRelativa = '/usuarios/' . $user . '/1.jpg';

} elseif ($type === 'nomina') {
    $dni = $_GET['dni'] ?? '';
    $mes = $_GET['mes'] ?? '';
    $anio = $_GET['anio'] ?? '';

    if (empty($dni) || empty($mes) || empty($anio)) {
        header("HTTP/1.0 400 Bad Request");
        exit();
    }
    
    try {
        $conn = new PDO("sqlite:" . $db_path);
        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $stmt = $conn->prepare("SELECT archivo FROM Nomina WHERE dni = :dni AND mes = :mes AND anio = :anio");
        $stmt->bindParam(':dni', $dni, PDO::PARAM_STR);
        $stmt->bindParam(':mes', $mes, PDO::PARAM_INT);
        $stmt->bindParam(':anio', $anio, PDO::PARAM_INT);
        $stmt->execute();
        $rutaRelativa = $stmt->fetchColumn();

        if ($rutaRelativa === false) {
            header("HTTP/1.0 404 Not Found");
            exit();
        }
    } catch (Exception $e) {
        header("HTTP/1.0 500 Internal Server Error");
        exit();
    }
} else {
    header("HTTP/1.0 400 Bad Request");
    echo "Error: Tipo de archivo no especificado.";
    exit();
}


// --- LÓGICA COMÚN PARA SERVIR EL ARCHIVO ---
$rutaFisicaCompleta = $file_base_path . '/' . ltrim($rutaRelativa, '/');

if (!file_exists($rutaFisicaCompleta)) {
    header("HTTP/1.0 404 Not Found");
    exit();
}

$file_extension = strtolower(pathinfo($rutaFisicaCompleta, PATHINFO_EXTENSION));
$content_type = 'application/octet-stream';

if ($file_extension == 'png') $content_type = 'image/png';
elseif ($file_extension == 'jpg' || $file_extension == 'jpeg') $content_type = 'image/jpeg';

header('Content-Type: ' . $content_type);
header('Content-Length: ' . filesize($rutaFisicaCompleta));
readfile($rutaFisicaCompleta);
exit();
?>