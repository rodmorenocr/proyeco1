<?php
/**
 * Template Name: datospersonales
 
 */
// ---- Problemas de cache al aceder sincronamente, solucioon propuesta nborramos todo el cache y cookies ya no se guarda nada----
header("Cache-Control: no-store, no-cache, must-revalidate, max-age=0");
header("Cache-Control: post-check=0, pre-check=0", false);
header("Pragma: no-cache");
header("Expires: Tue, 01 Jan 1980 1:00:00 GMT");
header("Vary: Cookie, User-Agent");


if (session_status() == PHP_SESSION_NONE) {
    session_start();
}


// Proteger la página: si no está logueado, redirigir al login
if (!isset($_SESSION['portal_empleado_logged_in']) || $_SESSION['portal_empleado_logged_in'] !== true) {
    $url_login = home_url('home');
    header('Location: ' . $url_login);
    exit;
}

// -----------------------------------------------------------------------------
// 2. OBTENER DATOS DEL USUARIO DESDE SQLITE (ACTUALIZADO)
// -----------------------------------------------------------------------------
$url_base_imagenes_perfil = get_stylesheet_directory_uri() . '/imagenes/';

$datos_usuario = [
    // Valores por defecto o placeholders
    'dni' => 'N/A',
    'nombre' => 'Nombre',
    'apellido' => 'Apellido',
    'mail' => 'email@ejemplo.com',
    'puesto' => 'Puesto',
    'telefono' => 'N/A',
    'direccion' => 'N/A',
    'codigo_postal' => 'N/A',
    'ciudad' => 'N/A',
    'provincia' => 'N/A',
    'pais' => 'N/A',
    'user_name' => $_SESSION['portal_empleado_username'] ?? 'N/A', // Guardamos el userName también si es necesario mostrarlo
    'imagen_perfil_principal' => $imagen_defecto, // Ruta a una imagen por defecto
    'imagenes_perfil_secundarias' => [ // Dejamos esto como ejemplo, ya que no hay columnas claras para múltiples imágenes
        'ruta/a/imagen/defecto_pequena1.jpg',
        'ruta/a/imagen/defecto_pequena2.jpg',
        'ruta/a/imagen/defecto_pequena3.jpg',
    ]
];

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

        $sql = "SELECT dni, nombre, apellido, mail, puesto, telefono, direccion, codigoPostal, ciudad, provincia, pais, userName, foto
                FROM Usuario
                WHERE userName = :username_logueado"; // Usamos userName para la búsqueda

        $stmt = $pdo->prepare($sql);
        $stmt->bindParam(':username_logueado', $username_logueado, PDO::PARAM_STR);
        $stmt->execute();
        $resultado = $stmt->fetch(PDO::FETCH_ASSOC);

        if ($resultado) {
            $datos_usuario['dni'] = htmlspecialchars($resultado['dni'] ?? $datos_usuario['dni']);
            $datos_usuario['nombre'] = htmlspecialchars($resultado['nombre'] ?? $datos_usuario['nombre']);
            $datos_usuario['apellido'] = htmlspecialchars($resultado['apellido'] ?? $datos_usuario['apellido']);
            $datos_usuario['mail'] = htmlspecialchars($resultado['mail'] ?? $datos_usuario['mail']);
            $datos_usuario['puesto'] = htmlspecialchars($resultado['puesto'] ?? $datos_usuario['puesto']);
            $datos_usuario['telefono'] = htmlspecialchars($resultado['telefono'] ?? $datos_usuario['telefono']);
            $datos_usuario['direccion'] = htmlspecialchars($resultado['direccion'] ?? $datos_usuario['direccion']);
            $datos_usuario['codigo_postal'] = htmlspecialchars($resultado['codigoPostal'] ?? $datos_usuario['codigo_postal']); 
            $datos_usuario['ciudad'] = htmlspecialchars($resultado['ciudad'] ?? $datos_usuario['ciudad']);
            $datos_usuario['provincia'] = htmlspecialchars($resultado['provincia'] ?? $datos_usuario['provincia']);
            $datos_usuario['pais'] = htmlspecialchars($resultado['pais'] ?? $datos_usuario['pais']);
            $datos_usuario['user_name'] = htmlspecialchars($resultado['userName'] ?? $datos_usuario['user_name']); 

            if (!empty($resultado['foto']) && $resultado['foto'] !== 'NULL') { // SQLite podría devolver 'NULL' como string y esto da lugar a una excepcion NullPointer que tratamos en el IDE
                $datos_usuario['imagen_perfil_principal'] = htmlspecialchars($resultado['foto']);
               

            }
        }

    } catch (PDOException $e) {
        error_log("Error de base de datos: " . $e->getMessage());
        $db_error = "No se pudieron cargar los datos personales en este momento. (DB)";
    } catch (Exception $e) {
        error_log("Error del sistema: " . $e->getMessage());
        $db_error = "Error del sistema al cargar datos. (SYS)";
    } finally {
        $pdo = null;
    }
}

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
    .datos-personales-container {
        max-width: 900px;
        margin: 20px auto;
        padding: 30px;
        background-color: #e8e8e8;
        border-radius: 8px;
     
    }
    .datos-personales-container h1 {
        font-size: 28px;
        color: #444;
        margin-bottom: 30px;
        font-weight: normal;
    }
    .form-grid {
        display: grid;
        grid-template-columns: repeat(2, 1fr); 
        gap: 20px 40px; 
        align-items: start; 
    }
    .form-column-left {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 20px 30px; 
        grid-column: 1 / 2; 
    }
    
    .form-group {
        display: flex;
        flex-direction: column; 
    }
    .form-group.full-width {
        grid-column: 1 / -1; 
    }
    .form-group label {
        font-size: 13px;
        color: #555;
        margin-bottom: 6px;
        display: block;
    }
    .form-group input[type="text"],
    .form-group input[type="email"],
    .form-group input[type="tel"] {
        width: 100%;
        padding: 10px 12px;
        border: 1px solid #ccc;
        background-color: #fff;  
        border-radius: 4px;
        box-sizing: border-box;
        font-size: 14px;
    }
    
    .profile-images-section {
        grid-column: 2 / 3; 
        display: flex;
        flex-direction: column;
        align-items: center; 
        gap: 15px;
        padding-left: 20px; 
    }
    .main-profile-image {
        width: 150px; 
        height: 150px;
        border-radius: 8px;
        object-fit: cover; 
        border: 3px solid #fff;
        box-shadow: 0 2px 5px rgba(0,0,0,0.15);
    }
    .thumbnail-images {
        display: flex;
        gap: 10px;
        justify-content: center;
    }
    .thumbnail-profile-image {
        width: 60px; 
        height: 60px;
        border-radius: 6px;
        object-fit: cover;
        border: 2px solid #fff;
        box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        cursor: pointer; 
    }
    .form-actions {
        grid-column: 1 / -1; 
        margin-top: 30px;
        display: flex;
        gap: 15px;
    }
    .form-actions button {
        padding: 12px 25px;
        border: none;
        border-radius: 4px;
        font-size: 15px;
        cursor: pointer;
        font-weight: bold;
    }
    .form-actions .btn-guardar {
        background-color: #555; 
        color: white;
    }
    .form-actions .btn-cancelar {
        background-color: #ccc; 
        color: #444;
    }

    
    @media (max-width: 768px) {
        .form-grid {
            grid-template-columns: 1fr;
        }
        .profile-images-section {
            grid-row: 1; 
            margin-bottom: 20px;
        }
        .form-column-left {
            grid-column: 1 / -1; 
        }
    }
</style>

<div class="datos-personales-container">
    <h1>Datos personales</h1>

    <?php if (isset($db_error)): ?>
        <p style="color: red; text-align: center;"><?php echo $db_error; ?></p>
    <?php endif; ?>

    <form id="form-datos-personales" method="POST" action="">
        <?php
        ?>
        <div class="form-grid">
            <div class="form-column-left">
                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" name="nombre" value="<?php echo $datos_usuario['nombre']; ?>">
                </div>
                <div class="form-group">
                    <label for="apellido">Apellido</label>
                    <input type="text" id="apellido" name="apellido" value="<?php echo $datos_usuario['apellido']; ?>">
                </div>
                <div class="form-group full-width">
                    <label for="mail">Mail</label>
                    <input type="email" id="mail" name="mail" value="<?php echo $datos_usuario['mail']; ?>">
                </div>
                <div class="form-group full-width">
                    <label for="puesto">Puesto</label>
                    <input type="text" id="puesto" name="puesto" value="<?php echo $datos_usuario['puesto']; ?>">
                </div>
                <div class="form-group full-width">
                    <label for="telefono">Teléfono</label>
                    <input type="tel" id="telefono" name="telefono" value="<?php echo $datos_usuario['telefono']; ?>">
                </div>
                <div class="form-group full-width">
                    <label for="direccion">Dirección</label>
                    <input type="text" id="direccion" name="direccion" value="<?php echo $datos_usuario['direccion']; ?>">
                </div>
                <div class="form-group">
                    <label for="codigo_postal">Código Postal</label>
                    <input type="text" id="codigo_postal" name="codigo_postal" value="<?php echo $datos_usuario['codigo_postal']; ?>">
                </div>
                <div class="form-group">
                    <label for="ciudad">Ciudad</label>
                    <input type="text" id="ciudad" name="ciudad" value="<?php echo $datos_usuario['ciudad']; ?>">
                </div>
                <div class="form-group">
                    <label for="provincia">Provincia</label>
                    <input type="text" id="provincia" name="provincia" value="<?php echo $datos_usuario['provincia']; ?>">
                </div>
                <div class="form-group">
                    <label for="pais">País</label>
                    <input type="text" id="pais" name="pais" value="<?php echo $datos_usuario['pais']; ?>">
                </div>
            </div>

            <div class="profile-images-section">
                <img src="<?php echo $datos_usuario['Imagen de perfil principal']; ?>" alt="Imagen de perfil principal" class="main-profile-image">
                <div class="thumbnail-images">
                    <?php foreach ($datos_usuario['imagenes_perfil_secundarias'] as $thumb_url): ?>
                        <img src="<?php echo $thumb_url; ?>" alt="Miniatura de perfil" class="thumbnail-profile-image">
                    <?php endforeach; ?>
                </div>
                </div>

            <div class="form-actions">
                <button type="submit" name="guardar_datos" class="btn-guardar">Guardar</button>
                <button type="button" name="cancelar_datos" class="btn-cancelar" onclick="window.history.back();">Cancelar</button>
                <?php 
                      ?>'"
                ?>
            </div>
        </div>
    </form>
</div>

<?php
// Cargar el pie de página de WordPress del tema divi usando en toda la pagina web
if (function_exists('get_footer')) {
    get_footer();
}
?>