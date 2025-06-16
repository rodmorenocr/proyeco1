<?php
/**
 * Template Name: Registro de Horas Empleado
 */

// -----------------------------------------------------------------------------
// 1. INICIO DE SESIÓN Y VERIFICACIÓN DE ACCESO (Reutilizado de datos.php)
// -----------------------------------------------------------------------------
// ---- INICIO CABECERAS todo anticache para no da error ----
header("Cache-Control: no-store, no-cache, must-revalidate, max-age=0");
header("Cache-Control: post-check=0, pre-check=0", false); // Para compatibilidad HTTP/1.0
header("Pragma: no-cache"); // Para navegadores más antiguos
header("Expires: Tue, 01 Jan 1980 1:00:00 GMT"); // Fecha en el pasado
header("Vary: Cookie, User-Agent"); // Indica a las cachés que el contenido varía según la cookie y el user-agent



if (session_status() == PHP_SESSION_NONE) {
    session_start();
}
// Proteger la página: si no está logueado, redirigir al login
if (!isset($_SESSION['portal_empleado_logged_in']) || $_SESSION['portal_empleado_logged_in'] !== true) {
    $url_login = home_url('home'); // 'home' es la slug de login
    header('Location: ' . $url_login);
    exit;
}

// -----------------------------------------------------------------------------
// 2. OBTENER DATOS DEL USUARIO LOGUEADO (Reutilizado y simplificado de datos.php)
// -----------------------------------------------------------------------------
$datos_usuario_logueado = [
    'dni' => 'N/A',
    'nombre' => 'Empleado',
    'apellido' => '',
    'user_name' => $_SESSION['portal_empleado_username'] ?? 'N/A',
];
$registros_horas = [];
$db_error_usuario = '';
$db_error_registros = '';

if (isset($_SESSION['portal_empleado_username'])) {
    $username_logueado = $_SESSION['portal_empleado_username'];
    $pdo = null;

    try {
       
        $db_path = __DIR__ . '/user/auraBoutique.db'; 

        if (!file_exists($db_path)) {
            throw new Exception("Error: No se encontró la base de datos en la ruta: " . $db_path);
        }

        $pdo = new PDO('sqlite:' . $db_path);
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        // Primero, obtener DNI y nombre del usuario logueado 
        $sql_usuario = "SELECT dni, nombre, apellido FROM Usuario WHERE userName = :username_logueado";
        $stmt_usuario = $pdo->prepare($sql_usuario);
        $stmt_usuario->bindParam(':username_logueado', $username_logueado, PDO::PARAM_STR);
        $stmt_usuario->execute();
        $resultado_usuario = $stmt_usuario->fetch(PDO::FETCH_ASSOC);

        if ($resultado_usuario) {
            $datos_usuario_logueado['dni'] = htmlspecialchars($resultado_usuario['dni'] ?? $datos_usuario_logueado['dni']);
            $datos_usuario_logueado['nombre'] = htmlspecialchars($resultado_usuario['nombre'] ?? $datos_usuario_logueado['nombre']);
            $datos_usuario_logueado['apellido'] = htmlspecialchars($resultado_usuario['apellido'] ?? $datos_usuario_logueado['apellido']);
        } else {
            $db_error_usuario = "No se pudieron encontrar los datos del usuario.";
        }

        // Si tenemos el DNI, buscamos sus registros horarios
        if ($datos_usuario_logueado['dni'] !== 'N/A') {
            //Query 
            $sql_registros = "SELECT fecha, hora_inicio, hora_fin, total_horas
                              FROM RegistroHoras
                              WHERE dni = :dni_empleado
                              ORDER BY fecha DESC, hora_inicio DESC"; // Ordenar por fecha y hora más recientes primero
            
            $stmt_registros = $pdo->prepare($sql_registros);
            $stmt_registros->bindParam(':dni_empleado', $datos_usuario_logueado['dni'], PDO::PARAM_STR);
            $stmt_registros->execute();
            $registros_horas = $stmt_registros->fetchAll(PDO::FETCH_ASSOC);

            if (empty($registros_horas)) {
                $db_error_registros = "No se encontraron registros horarios para este empleado.";
            }
        }

    } catch (PDOException $e) {
        error_log("Error de base de datos (Registro Horas): " . $e->getMessage());
        if (empty($db_error_usuario)) $db_error_usuario = "Error al conectar con la base de datos. (DB)";
        $db_error_registros = "No se pudieron cargar los registros horarios. (DB)";
    } catch (Exception $e) {
        error_log("Error del sistema (Registro Horas): " . $e->getMessage());
        if (empty($db_error_usuario)) $db_error_usuario = "Error del sistema. (SYS)";
        $db_error_registros = "Error del sistema al cargar registros. (SYS)";
    } finally {
        $pdo = null;
    }
} else {
    $db_error_usuario = "No hay sesión de usuario activa.";
}

// Cargar la cabecera de WordPress
if (function_exists('get_header')) {
    get_header(); 
}
?>

<style>
   
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
        color: #333;
        margin: 0;
        padding: 20px;
    }
    .registro-horas-container {
        max-width: 900px;
        margin: 20px auto;
        padding: 30px;
        background-color: #e8e8e8;
        border-radius: 8px;
    }
    .registro-horas-container h1 {
        font-size: 28px;
        color: #444;
        margin-bottom: 15px;
        font-weight: normal;
    }
    .info-usuario {
        background-color: #fff;
        padding: 15px;
        border-radius: 6px;
        margin-bottom: 25px;
        border: 1px solid #ddd;
    }
    .info-usuario p {
        margin: 5px 0;
        font-size: 16px;
    }
    .info-usuario strong {
        color: #555;
    }

    .tabla-registros-horas {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
        background-color: #fff;
        box-shadow: 0 1px 3px rgba(0,0,0,0.1);
    }
    .tabla-registros-horas th,
    .tabla-registros-horas td {
        border: 1px solid #ddd;
        padding: 10px 12px;
        text-align: left;
        font-size: 14px;
    }
    .tabla-registros-horas th {
        background-color: #f7f7f7;
        font-weight: bold;
        color: #555;
    }
    .tabla-registros-horas tr:nth-child(even) {
        background-color: #fdfdfd;
    }
    .no-registros {
        text-align: center;
        padding: 20px;
        background-color: #fff8e1;
        border: 1px solid #ffe5b1;
        color: #795548;
        border-radius: 4px;
    }
    .error-message {
        color: red;
        text-align: center;
        padding: 10px;
        background-color: #ffebee;
        border: 1px solid #ffcdd2;
        border-radius: 4px;
        margin-bottom: 20px;
    }
</style>

<div class="registro-horas-container">
    <h1>Registro de Horas del Empleado</h1>

    <?php if (!empty($db_error_usuario)): ?>
        <p class="error-message"><?php echo $db_error_usuario; ?></p>
    <?php endif; ?>

    <?php if ($datos_usuario_logueado['dni'] !== 'N/A'): ?>
        <div class="info-usuario">
            <p><strong>Empleado:</strong> <?php echo htmlspecialchars($datos_usuario_logueado['nombre'] . ' ' . $datos_usuario_logueado['apellido']); ?></p>
            <p><strong>DNI:</strong> <?php echo htmlspecialchars($datos_usuario_logueado['dni']); ?></p>
            <p><strong>Usuario del Sistema:</strong> <?php echo htmlspecialchars($datos_usuario_logueado['user_name']); ?></p>
        </div>

        <h2>Detalle de Jornadas</h2>

        <?php if (!empty($db_error_registros) && empty($registros_horas)): ?>
            <p class="error-message"><?php echo $db_error_registros; ?></p>
        <?php elseif (empty($registros_horas)): ?>
            <p class="no-registros">No hay registros de horas disponibles para mostrar.</p>
        <?php else: ?>
            <div style="overflow-x:auto;"> <table class="tabla-registros-horas">
                    <thead>
                        <tr>
                            <th>Fecha</th>
                            <th>Hora Inicio</th>
                            <th>Hora Fin</th>
                            <th>Total Horas Trabajadas</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php foreach ($registros_horas as $registro): ?>
                            <tr>
                                <td><?php echo htmlspecialchars($registro['fecha'] ? date("d-m-Y", strtotime($registro['fecha'])) : 'N/A'); ?></td>
                                <td><?php echo htmlspecialchars($registro['hora_inicio'] ?? 'N/A'); ?></td>
                                <td><?php echo htmlspecialchars($registro['hora_fin'] ?? 'N/A'); ?></td>
                                <td><?php echo htmlspecialchars($registro['total_horas'] ?? 'N/A'); ?></td>
                            </tr>
                        <?php endforeach; ?>
                    </tbody>
                </table>
            </div>
        <?php endif; ?>

    <?php elseif(empty($db_error_usuario)): // Si no hay DNI y tampoco hubo error de usuario, es que no está logueado o falta el username en sesión ?>
        <p class="error-message">No se ha podido identificar al usuario. Por favor, inicie sesión de nuevo.</p>
    <?php endif; ?>

</div>

<?php
// Cargar el pie de página de WordPress
if (function_exists('get_footer')) {
    get_footer();
}
?>