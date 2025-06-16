<?php
// ---------------------------------------------------------------------
// INICIO: Configuración para mostrar errores PHP (SOLO PARA DEPURACIÓN)
// ¡Comenta o elimina estas líneas en un entorno de producción!
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);
// FIN: Configuración para mostrar errores PHP
// ---------------------------------------------------------------------

// Establecer el tipo de contenido y charset
header('Content-Type: text/plain; charset=utf-8');

// --- Configuración de la Ruta a la Base de Datos SQLite ---
// Asegúrate de que esta ruta sea correcta y que el servidor web tenga permisos de lectura/escritura.
$db_path = __DIR__ . '/user/auraBoutique.db';
// $db_path = "/home/u370768163/domains/auraboutique.info/public_html/wp-content/themes/divi-child/user/auraBoutique.db";

$conn = null; // Variable para la conexión PDO

// --- Conexión a la Base de Datos ---
try {
    $conn = new PDO("sqlite:" . $db_path);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    // Si falla la conexión, termina y devuelve error.
    echo "FAILURE|Error de conexión a SQLite: " . htmlspecialchars($e->getMessage());
    exit();
}

// --- Manejo de Acciones ---
// Usamos $_POST ya que todas las peticiones desde HttpConectorSimple son POST.
$action = $_POST['action'] ?? '';

// Asegúrate de que la petición sea POST
if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    echo "FAILURE|Método de petición no permitido. Use POST.";
    exit();
}

// Selecciona la función a ejecutar basada en la acción
switch ($action) {
    case 'cargarUsuario':
        cargarUsuarioSimple($conn);
        break;
    case 'modificaUsuario':
        modificaUsuarioSimple($conn);
        break;
    case 'consultaNominaMes':
        consultaNominaMes($conn);
        break;
    case 'insertarHoras':
        insertarHoras($conn);
        break;
    case 'testConnection': // Acción de prueba (opcional)
        echo "SUCCESS|Conexión PHP-SQLite OK.";
        break;
    default:
        echo "FAILURE|Acción POST no válida o no especificada: " . htmlspecialchars($action);
        break;
}

// Cierra la conexión al final (aunque PDO a veces lo maneja, es buena práctica)
$conn = null;

// ---------------------------------------------------------------------
// INICIO: Definiciones de Funciones
// ---------------------------------------------------------------------

/**
 * Carga los datos de un usuario si el login es correcto.
 */
function cargarUsuarioSimple($pdo_conn) {
    $nombreUsuario = $_POST['nombreUsuario'] ?? '';
    $contrasenia = $_POST['contrasenia'] ?? '';

    if (empty($nombreUsuario) || empty($contrasenia)) {
        echo "FAILURE|PHP: Nombre de usuario o contraseña vacíos.";
        return;
    }

    try {
        $stmt = $pdo_conn->prepare(
            "SELECT dni, nombre, apellido, puesto, mail, telefono, direccion, codigoPostal, ciudad, provincia, pais " .
            "FROM Usuario WHERE userName = :username AND password = :password"
        );
        $stmt->bindParam(':username', $nombreUsuario, PDO::PARAM_STR);
        $stmt->bindParam(':password', $contrasenia, PDO::PARAM_STR);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        if ($row) {
            // Construye la respuesta SUCCESS con los datos
            $dataValues = [
                $row['dni'] ?? '', $row['nombre'] ?? '', $row['apellido'] ?? '', $row['puesto'] ?? '', $row['mail'] ?? '',
                $row['telefono'] ?? '0', $row['direccion'] ?? '', $row['codigoPostal'] ?? '0', $row['ciudad'] ?? '',
                $row['provincia'] ?? '', $row['pais'] ?? ''
            ];
            echo "SUCCESS|" . implode("|", $dataValues);
        } else {
            echo "FAILURE|Usuario o contraseña incorrectos.";
        }
    } catch (PDOException $e) {
        echo "FAILURE|Error DB (cargarUsuario): " . htmlspecialchars($e->getMessage());
    }
}

/**
 * Modifica los datos de un usuario basado en su DNI.
 */
function modificaUsuarioSimple($pdo_conn) {
    $dni = $_POST['dni'] ?? '';
    if (empty($dni)) {
        echo "FAILURE|PHP: DNI no proporcionado para modificar.";
        return;
    }

    // Recoge todos los datos (usa ?? para evitar errores si no llegan)
    $params = [
        ':nombre' => $_POST['nombre'] ?? '',
        ':apellido' => $_POST['apellido'] ?? '',
        ':mail' => $_POST['mail'] ?? '',
        ':puesto' => $_POST['puesto'] ?? '',
        ':telefono' => $_POST['telefono'] ?? '0',
        ':direccion' => $_POST['direccion'] ?? '',
        ':codigoPostal' => $_POST['codigoPostal'] ?? '0',
        ':ciudad' => $_POST['ciudad'] ?? '',
        ':provincia' => $_POST['provincia'] ?? '',
        ':pais' => $_POST['pais'] ?? '',
        ':dni' => $dni
    ];

    try {
        $stmt = $pdo_conn->prepare(
            "UPDATE Usuario SET nombre = :nombre, apellido = :apellido, mail = :mail, " .
            "puesto = :puesto, telefono = :telefono, direccion = :direccion, " .
            "codigoPostal = :codigoPostal, ciudad = :ciudad, provincia = :provincia, pais = :pais " .
            "WHERE dni = :dni"
        );

        $executed = $stmt->execute($params);

        if ($executed) {
            // rowCount puede no ser fiable en todas las BBDD/drivers, pero lo intentamos
            $count = $stmt->rowCount();
            if ($count > 0) {
                 echo "SUCCESS|Datos modificados correctamente.";
            } else {
                 echo "SUCCESS|Datos guardados (o no hubo cambios)."; // Puede que el DNI no exista o los datos fueran iguales
            }
        } else {
            echo "FAILURE|Error al ejecutar la modificación.";
        }
    } catch (PDOException $e) {
        echo "FAILURE|Error DB (modificaUsuario): " . htmlspecialchars($e->getMessage());
    }
}

/**
 * Consulta la ruta de una nómina.
 */
function consultaNominaMes($pdo_conn) {
    $dni = $_POST['dni'] ?? '';
    $mes = $_POST['mes'] ?? '';
    $anio = $_POST['anio'] ?? '';

    if (empty($dni) || empty($mes) || empty($anio)) {
        echo "FAILURE|PHP: Faltan DNI, mes o año.";
        return;
    }

    try {
        $stmt = $pdo_conn->prepare("SELECT archivo FROM Nomina WHERE dni = :dni AND mes = :mes AND anio = :anio");
        $stmt->bindParam(':dni', $dni, PDO::PARAM_STR);
        $stmt->bindParam(':mes', $mes, PDO::PARAM_INT);
        $stmt->bindParam(':anio', $anio, PDO::PARAM_INT);
        $stmt->execute();

        // fetchColumn() devuelve la ruta si la encuentra, o FALSE si no encuentra nada.
        $rutaRelativaNomina = $stmt->fetchColumn();

        // ESTA ES LA COMPROBACIÓN CRÍTICA
        if ($rutaRelativaNomina !== false) {
            // Si se encontró la nómina, construimos la URL completa.
            $baseUrl = "https://auraboutique.info/wp-content/uploads/datos_empleados/";
            $urlCompleta = $baseUrl . ltrim($rutaRelativaNomina, '/');
            
            // Y enviamos la respuesta de éxito con la URL.
            echo "SUCCESS|" . htmlspecialchars($urlCompleta);
        } else {
            // Si fetchColumn() devolvió FALSE, significa que no hay nómina para esa fecha.
            // Enviamos una respuesta de fallo clara.
            echo "FAILURE|Nómina no encontrada para la fecha especificada.";
        }
    } catch (PDOException $e) {
        echo "FAILURE|Error DB (consultaNominaMes): " . htmlspecialchars($e->getMessage());
    }
}

/**
 * Inserta un registro de horas trabajadas.
 */
function insertarHoras($pdo_conn) {
    $dni = $_POST['dni'] ?? '';
    $fecha = $_POST['fecha'] ?? '';
    $horaInicio = $_POST['horaInicio'] ?? '';
    $horaFin = $_POST['horaFin'] ?? '';
    $horas = $_POST['horas'] ?? '';

    if (empty($dni) || empty($fecha) || empty($horaInicio) || empty($horaFin) || empty($horas)) {
        echo "FAILURE|PHP: Faltan datos para insertar horas.";
        return;
    }

    try {
        $stmt = $pdo_conn->prepare(
            "INSERT INTO RegistroHoras (dni, fecha, hora_inicio, hora_fin, total_horas) " .
            "VALUES (:dni, :fecha, :hora_inicio, :hora_fin, :total_horas)"
        );
        $params = [
            ':dni' => $dni,
            ':fecha' => $fecha,
            ':hora_inicio' => $horaInicio,
            ':hora_fin' => $horaFin,
            ':total_horas' => $horas
        ];

        if ($stmt->execute($params)) {
            echo "SUCCESS|Registro de horas insertado.";
        } else {
            echo "FAILURE|Error al insertar el registro.";
        }
    } catch (PDOException $e) {
        // Podrías chequear si es un error de clave duplicada si fecha+dni es PK/UNIQUE
        echo "FAILURE|Error DB (insertarHoras): " . htmlspecialchars($e->getMessage());
    }
}

?>
        
        
        
        