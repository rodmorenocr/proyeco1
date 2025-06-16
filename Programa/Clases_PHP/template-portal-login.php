<?php
/**
 * Template Name: Portal Login Empleado Template
 * Plantilla de página personalizada para el login del portal de empleados.
 */



// ---- INICIO CABECERAS anti-cache como siemre----
header("Cache-Control: no-store, no-cache, must-revalidate, max-age=0");
header("Cache-Control: post-check=0, pre-check=0", false); // Para compatibilidad HTTP/1.0
header("Pragma: no-cache"); // Para navegadores más antiguos
header("Expires: Tue, 01 Jan 1980 1:00:00 GMT"); // Fecha en el pasado
header("Vary: Cookie, User-Agent"); // Indica a las cachés que el contenido varía según la cookie y el user-agent

if (session_status() == PHP_SESSION_NONE) {
    session_start();
}

// Variable para guardar mensajes de error para mostrar en el formulario
$login_error_message = null;
if (isset($_SESSION['login_error'])) {
    $login_error_message = $_SESSION['login_error'];
    unset($_SESSION['login_error']); 
}


// Procesar solo si se envió el formulario (método POST y botón presionado)
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['portal_login_submit'])) {

    $username = isset($_POST['portal_username']) ? trim($_POST['portal_username']) : '';
    $password_ingresada = isset($_POST['portal_password']) ? trim($_POST['portal_password']) : '';

    if (empty($username) || empty($password_ingresada)) {
        $_SESSION['login_error'] = 'Usuario y contraseña son obligatorios.';
    } else {
        $db_path = __DIR__ .'/user/auraBoutique.db'; 
        $pdo = null;
        $password_guardada_plaintext = null; //Nada de seguridad, aqui podemos usar un hash ,mas adelante

        try { // Añadido bloque try-catch para manejo de errores de BD
            $dir = 'sqlite:'.$db_path;
            $pdo = new PDO($dir); // Eliminado PDO::ATTR_PERSISTENT da error nullpointer
            $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $sql = "SELECT password FROM Usuario WHERE userName = :username"; //consulta actual
            $stmt = $pdo->prepare($sql);
            $stmt->bindParam(':username', $username, PDO::PARAM_STR);
            $stmt->execute();
            $result = $stmt->fetch(PDO::FETCH_ASSOC);

            if ($result) {
                $password_guardada_plaintext = $result['password']; // O $hash_guardado = $result['password_hash'];
            }

        } catch (PDOException $e) {

            $_SESSION['login_error'] = 'Error conectando con el sistema. Intente más tarde.';
            // Para no seguir con la lógica de login si hay error de BD
            if (!headers_sent()) {
                header('Location: ' . $_SERVER['REQUEST_URI']);
                exit;
            } else {
                $login_error_message = $_SESSION['login_error'];
                // No continuar con la lógica de login si la BD falla
                goto display_form; // Salta al final del bloque PHP para mostrar el formulario con el error
            }
        }

        if ($password_guardada_plaintext !== null && $password_ingresada === $password_guardada_plaintext) {

            if (session_status() == PHP_SESSION_NONE) { // Doble chequeo, aunque debería estar iniciada
                session_start();
            }
            session_regenerate_id(true);
            $_SESSION['portal_empleado_logged_in'] = true;
            $_SESSION['portal_empleado_username'] = $username;
            $url_destino_completa = home_url('/menu/'); 
            
            if (!headers_sent()) {
                 header('Location: ' . $url_destino_completa);
                 exit;
            } else {
                 $_SESSION['login_error'] = "Error: No se pudo redirigir (headers sent). Logueado pero no redirigido.";
                 header('Location: ' . $_SERVER['REQUEST_URI']);
                 exit;
            }

        } else {

            $_SESSION['login_error'] = 'Usuario o contraseña incorrectos.';
        }
    } 

    if (!headers_sent()) {
        header('Location: ' . $_SERVER['REQUEST_URI']);
        exit;
    } else {
    }

}

display_form: 

// ---- FIN: CÓDIGO DE PROCESAMIENTO DEL FORMULARIO ----

// Cargar la cabecera de WordPress/Divi
get_header();

?>

<div id="main-content">
    <div class="container">
        <div id="content-area" class="clearfix">
            <div id="left-area">
                <article>
                    <div class="entry-content">

                        <div class="portal-login-container" style="max-width: 450px; margin: 40px auto; padding: 30px; border: 1px solid #eee; background: #fff; box-shadow: 0 0 10px rgba(0,0,0,0.05);">
                          <h2 style="text-align: center;">Acceso Portal Empleado</h2> <?php // Título actualizado ?>

                          <form method="post" action="<?php echo esc_url($_SERVER['REQUEST_URI']); ?>" class="portal-login-form"> <?php // action="" es lo mismo que la URL actual, pero esto es más explícito ?>

                            <?php if ($login_error_message): ?>
                              <div class="portal-login-error-message" style="color: #D8000C; background-color: #FFD2D2; border: 1px solid #D8000C; padding: 10px; margin-bottom: 15px; border-radius: 4px; text-align: center;">
                                <?php echo esc_html($login_error_message); ?>
                              </div>
                            <?php endif; ?>

                            <p style="margin-bottom: 15px;">
                              <label for="portal_username" style="display: block; font-weight: bold; margin-bottom: 5px;">Nombre de Usuario:</label>
                              <input type="text" id="portal_username" name="portal_username" required style="width: 100%; padding: 10px; border: 1px solid #ccc; box-sizing: border-box;" value="<?php echo isset($_POST['portal_username']) ? esc_attr($_POST['portal_username']) : ''; ?>"> <?php // Para rellenar el username si hay error ?>
                            </p>

                            <p style="margin-bottom: 15px;">
                              <label for="portal_password" style="display: block; font-weight: bold; margin-bottom: 5px;">Contraseña:</label>
                              <input type="password" id="portal_password" name="portal_password" required style="width: 100%; padding: 10px; border: 1px solid #ccc; box-sizing: border-box;">
                            </p>

                            <p>
                              <button type="submit" name="portal_login_submit" style="width: 100%; padding: 12px; background-color: #007bff; color: white; border: none; cursor: pointer; font-size: 16px;">Iniciar Sesión</button>
                            </p>
                          </form>
                        </div>

                    </div> </article> </div> <?php //get_sidebar(); ?>

        </div> </div> </div> <?php
// Cargar el pie de página de WordPress/Divi
get_footer();
?>