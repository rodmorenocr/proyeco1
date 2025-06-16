<?php
/**
 * Template Name: Visualizador de Nóminas Empleado
 */

// 1. INICIO DE SESIÓN Y DATOS BÁSICOS
// -----------------------------------------------------------------------------
if (session_status() == PHP_SESSION_NONE) {
    session_start();
}

if (!isset($_SESSION['portal_empleado_username'])) {
    $url_login = home_url('/tu-pagina-de-login/'); 
    header('Location: ' . $url_login);
    exit;
}
$username_logueado = $_SESSION['portal_empleado_username'];
$dni_empleado_logueado = null;
$nombre_completo_empleado = $username_logueado;

// 2. CONFIGURACIÓN DE RUTAS Y DATOS DE NÓMINAS
// -----------------------------------------------------------------------------
$url_base_nominas_servidor = get_stylesheet_directory_uri() . '/imagenes'; 
$url_nomina_placeholder = get_stylesheet_directory_uri() . '/imagenes_globales/placeholder_documento.png';

$nominas_por_ano_mes = [];
$pdo_conexion = null;
$error_general_nominas = '';

try {
    $db_path = __DIR__ . '/user/auraBoutique.db';

    if (!file_exists($db_path)) {
        throw new Exception("Archivo de base de datos no encontrado en: " . htmlspecialchars($db_path));
    }

    $pdo_conexion = new PDO('sqlite:' . $db_path);
    $pdo_conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Obtener DNI y nombre del usuario
    $sql_get_user_data = "SELECT dni, nombre, apellido FROM Usuario WHERE userName = :username_logueado";
    $stmt_get_user_data = $pdo_conexion->prepare($sql_get_user_data);
    $stmt_get_user_data->bindParam(':username_logueado', $username_logueado, PDO::PARAM_STR);
    $stmt_get_user_data->execute();
    $usuario_data = $stmt_get_user_data->fetch(PDO::FETCH_ASSOC);

    if (!$usuario_data || empty($usuario_data['dni'])) {
        throw new Exception("No se pudo encontrar el DNI para el usuario: " . htmlspecialchars($username_logueado));
    }
    $dni_empleado_logueado = $usuario_data['dni'];
    if (!empty($usuario_data['nombre'])) {
        $nombre_completo_empleado = trim(htmlspecialchars($usuario_data['nombre']) . ' ' . htmlspecialchars($usuario_data['apellido'] ?? ''));
    }

    // Obtener las nóminas
    $sql_nominas = "SELECT anio, mes, archivo FROM Nomina WHERE dni = :dni_empleado ORDER BY anio DESC, mes DESC";
    $stmt_nominas = $pdo_conexion->prepare($sql_nominas);
    $stmt_nominas->bindParam(':dni_empleado', $dni_empleado_logueado, PDO::PARAM_STR);
    $stmt_nominas->execute();
    $resultados_nominas_db = $stmt_nominas->fetchAll(PDO::FETCH_ASSOC);

    if ($resultados_nominas_db) {
        foreach ($resultados_nominas_db as $nomina_db_item) {
            $ano_db = $nomina_db_item['anio'];
            $mes_db = $nomina_db_item['mes'];
            $ruta_archivo_db = $nomina_db_item['archivo']; 

            if (!isset($nominas_por_ano_mes[$ano_db])) {
                $nominas_por_ano_mes[$ano_db] = [];
            }
            
            $url_completa_nomina = rtrim($url_base_nominas_servidor, '/') . '/' . ltrim($ruta_archivo_db, '/');

            $nominas_por_ano_mes[$ano_db][] = [
                'mes_display' => htmlspecialchars(nombre_mes_es((int)$mes_db)),
                'url_completa' => $url_completa_nomina,
                'tipo_archivo' => strtolower(pathinfo($ruta_archivo_db, PATHINFO_EXTENSION))
            ];
        }
    }

} catch (PDOException $e) {
    $error_general_nominas = "Error de base de datos. Por favor, contacte al administrador.";
    error_log("Error PDO Nóminas: " . $e->getMessage());
} catch (Exception $e) {
    $error_general_nominas = "Error del sistema: " . $e->getMessage();
    error_log("Error General Nóminas: " . $e->getMessage());
} finally {
    $pdo_conexion = null;
}

function nombre_mes_es($numero_mes) {
    $meses = ["", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
    return $meses[$numero_mes] ?? "Mes " . $numero_mes;
}

if (function_exists('get_header')) {
    get_header(); 
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mis Nóminas - <?php echo htmlspecialchars($nombre_completo_empleado); ?></title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; color: #333; }
        .contenedor-nominas-principal { max-width: 1200px; margin: 25px auto; padding: 25px; background-color: #fff; border-radius: 8px; box-shadow: 0 2px 15px rgba(0,0,0,0.1); }
        .contenedor-nominas-principal h1 { font-size: 28px; color: #333; margin-top: 0; margin-bottom: 15px; border-bottom: 2px solid #eee; padding-bottom: 10px; font-weight: 500; }
        .info-empleado { font-size: 1.1em; margin-bottom: 25px; color: #555; }
        .layout-nominas-flex { display: flex; gap: 30px; }
        .panel-seleccion-nomina-sidebar { flex: 0 0 300px; background-color: #f9f9f9; padding: 20px; border-radius: 6px; height: fit-content; border: 1px solid #e0e0e0; }
        #buscar-nomina-input { width: calc(100% - 24px); padding: 10px 12px; margin-bottom: 18px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; font-size: 14px; }
        .arbol-directorios-nominas { max-height: 60vh; overflow-y: auto; }
        .arbol-directorios-nominas ul { list-style-type: none; padding-left: 0; margin: 0; }
        .arbol-directorios-nominas li { padding: 0; }
        .arbol-directorios-nominas .ano-nomina-titulo,
        .arbol-directorios-nominas .nomina-item-seleccionable { cursor: pointer; padding: 9px 12px; border-radius: 4px; display: block; margin-bottom: 4px; transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out; font-size: 15px; border: 1px solid transparent; }
        .arbol-directorios-nominas .ano-nomina-titulo { font-weight: bold; background-color: #e9ecef; border-color: #dee2e6; }
        .arbol-directorios-nominas .ano-nomina-titulo:hover { background-color: #ced4da; }
        .arbol-directorios-nominas .nomina-item-seleccionable { background-color: #fff; border-color: #eee; }
        .arbol-directorios-nominas .nomina-item-seleccionable:hover { background-color: #e2f1ff; border-color: #b8d6f0; }
        .arbol-directorios-nominas .nomina-item-seleccionable.activo { background-color: #007bff; color: white; border-color: #0056b3; }
        .arbol-directorios-nominas .ano-nomina-titulo::before { content: '📁 '; margin-right: 7px; }
        .arbol-directorios-nominas .meses-del-ano-lista { padding-left: 25px; display: none; border-left: 2px solid #e9ecef; margin-left: 10px; margin-top: 5px; }
        .arbol-directorios-nominas .meses-del-ano-lista.visible { display: block; }
        .arbol-directorios-nominas .nomina-item-seleccionable::before { content: '📄 '; margin-right: 7px; }
        .panel-visualizador-nomina-principal { flex-grow: 1; background-color: #e9ecef; padding: 20px; border: 1px solid #dee2e6; border-radius: 6px; display: flex; justify-content: center; align-items: center; min-height: 60vh; text-align: center; }
        #iframe-nomina-seleccionada-vista,
        #imagen-nomina-seleccionada-vista { max-width: 100%; max-height: 80vh; object-fit: contain; border: 1px solid #ccc; box-shadow: 0 0 10px rgba(0,0,0,0.1); display: none; }
        #iframe-nomina-seleccionada-vista { width: 100%; height: 80vh; border: none; }
        .mensaje-nominas { padding: 20px; background-color: #f8f9fa; border: 1px solid #dee2e6; border-radius: 4px; text-align: center; color: #495057; font-size: 1.1em; }
        .boton-descargar { display: block; width: calc(100% - 24px); text-align: center; padding: 12px 15px; margin-top: 20px; background-color: #28a745; color: white; text-decoration: none; border-radius: 4px; font-size: 16px; border: none; cursor: pointer; transition: background-color 0.2s; }
        .boton-descargar:hover { background-color: #218838; }
        .boton-descargar:disabled { background-color: #ccc; cursor: not-allowed; }
        @media (max-width: 800px) {
            .layout-nominas-flex { flex-direction: column; }
            .panel-seleccion-nomina-sidebar { flex: 0 0 auto; width: 100%; box-sizing: border-box; margin-bottom: 20px; }
        }
    </style>
</head>
<body>

<div class="contenedor-nominas-principal">
    <h1>Recibo de Nóminas</h1>
    <p class="info-empleado">Empleado: <strong><?php echo $nombre_completo_empleado; ?></strong> (DNI: <?php echo htmlspecialchars($dni_empleado_logueado ?? 'N/A'); ?>)</p>

    <?php if (!empty($error_general_nominas)): ?>
        <p class="mensaje-nominas" style="color:red; background-color: #ffebee; border-color: #ffcdd2;">
            <?php echo htmlspecialchars($error_general_nominas); ?>
        </p>
    <?php endif; ?>

    <div class="layout-nominas-flex">
        <div class="panel-seleccion-nomina-sidebar">
            <input type="text" id="buscar-nomina-input" placeholder="Buscar por mes o año...">
            <div id="arbol-nominas-id" class="arbol-directorios-nominas">
                <?php if (empty($nominas_por_ano_mes) && empty($error_general_nominas)): ?>
                    <p class="mensaje-nominas">No tiene nóminas disponibles en este momento.</p>
                <?php elseif (!empty($nominas_por_ano_mes)): ?>
                    <ul>
                        <?php foreach ($nominas_por_ano_mes as $ano_actual => $meses_nominas_actuales): ?>
                            <li>
                                <span class="ano-nomina-titulo" data-ano="<?php echo $ano_actual; ?>">Año <?php echo $ano_actual; ?></span>
                                <ul class="meses-del-ano-lista" id="meses-<?php echo $ano_actual; ?>">
                                    <?php foreach ($meses_nominas_actuales as $detalle_nomina): ?>
                                        <li>
                                            <span class="nomina-item-seleccionable"
                                                  data-url-nomina="<?php echo htmlspecialchars($detalle_nomina['url_completa']); ?>"
                                                  data-tipo-archivo="<?php echo htmlspecialchars($detalle_nomina['tipo_archivo']); ?>">
                                                Nómina <?php echo $detalle_nomina['mes_display']; ?>
                                            </span>
                                        </li>
                                    <?php endforeach; ?>
                                </ul>
                            </li>
                        <?php endforeach; ?>
                    </ul>
                <?php endif; ?>
            </div>
            <a href="#" id="boton-descargar-id" class="boton-descargar" download disabled>Descargar Nómina</a>
        </div>

        <div class="panel-visualizador-nomina-principal">
            <img id="imagen-nomina-seleccionada-vista" src="<?php echo htmlspecialchars($url_nomina_placeholder); ?>" alt="Visualizador de nómina" style="display: <?php echo (strpos($url_nomina_placeholder, '.pdf') === false && !empty($url_nomina_placeholder)) ? 'block' : 'none'; ?>;">
            <iframe id="iframe-nomina-seleccionada-vista" src="<?php echo (strpos($url_nomina_placeholder, '.pdf') !== false && !empty($url_nomina_placeholder)) ? htmlspecialchars($url_nomina_placeholder) : ''; ?>" style="display: <?php echo (strpos($url_nomina_placeholder, '.pdf') !== false && !empty($url_nomina_placeholder)) ? 'block' : 'none'; ?>;"></iframe>
            <p id="mensaje-visualizador-placeholder" class="mensaje-nominas" style="display:block;">
                Seleccione una nómina de la lista para verla aquí.
            </p>
        </div>
    </div>
</div>

<script>
document.addEventListener('DOMContentLoaded', function() {
    const arbolNominasContainer = document.getElementById('arbol-nominas-id');
    if (!arbolNominasContainer) return;

    const elementosAnos = arbolNominasContainer.querySelectorAll('.ano-nomina-titulo');
    const elementosNominasItems = arbolNominasContainer.querySelectorAll('.nomina-item-seleccionable');
    
    const imgVista = document.getElementById('imagen-nomina-seleccionada-vista');
    const iframeVista = document.getElementById('iframe-nomina-seleccionada-vista');
    const placeholderMsg = document.getElementById('mensaje-visualizador-placeholder');
    
    const inputBusqueda = document.getElementById('buscar-nomina-input');
    const botonDescargar = document.getElementById('boton-descargar-id');
    let itemActivo = null;

    function mostrarElementoVisualizador(tipo) {
        imgVista.style.display = (tipo === 'imagen') ? 'block' : 'none';
        iframeVista.style.display = (tipo === 'pdf') ? 'block' : 'none';
        placeholderMsg.style.display = (tipo === 'placeholder' || tipo === 'ninguno') ? 'block' : 'none';
    }

    // Inicializar visualizador con placeholder o mensaje
    const placeholderUrlGlobal = '<?php echo htmlspecialchars(empty($url_nomina_placeholder) ? "" : $url_nomina_placeholder); ?>';
    if (placeholderUrlGlobal) {
        const extensionPlaceholder = placeholderUrlGlobal.split('.').pop().toLowerCase();
        if (['jpg', 'jpeg', 'png', 'gif', 'webp'].includes(extensionPlaceholder)) {
            mostrarElementoVisualizador('imagen'); // imgVista.src ya está seteado por PHP
        } else if (extensionPlaceholder === 'pdf') {
             mostrarElementoVisualizador('pdf'); // iframeVista.src ya está seteado por PHP
        } else {
            mostrarElementoVisualizador('placeholder');
        }
    } else {
        mostrarElementoVisualizador('placeholder');
    }


    elementosAnos.forEach(anoTituloEl => {
        anoTituloEl.addEventListener('click', function() {
            const mesesUl = document.getElementById('meses-' + this.dataset.ano);
            if (mesesUl) {
                mesesUl.classList.toggle('visible');
            }
        });
    });

    elementosNominasItems.forEach(nominaItemEl => {
        nominaItemEl.addEventListener('click', function() {
            const urlNomina = this.dataset.urlNomina;
            const tipoArchivo = this.dataset.tipoArchivo.toLowerCase();

            if (itemActivo) itemActivo.classList.remove('activo');
            this.classList.add('activo');
            itemActivo = this;
            
            botonDescargar.disabled = false;
            botonDescargar.setAttribute('href', urlNomina);
            botonDescargar.setAttribute('download', `nomina_${this.textContent.trim().replace(/\s+/g, '_')}.${tipoArchivo}`);


            if (['jpg', 'jpeg', 'png', 'gif', 'webp'].includes(tipoArchivo)) {
                imgVista.src = urlNomina;
                imgVista.alt = "Nómina " + this.textContent.trim();
                mostrarElementoVisualizador('imagen');
            } else if (tipoArchivo === 'pdf') {
                iframeVista.src = urlNomina;
                mostrarElementoVisualizador('pdf');
            } else {
                mostrarElementoVisualizador('placeholder');
                placeholderMsg.textContent = `El archivo (${tipoArchivo.toUpperCase()}) no se puede visualizar directamente. Intente descargarlo.`;
            }
        });
    });

    if (inputBusqueda) {
        inputBusqueda.addEventListener('keyup', function() {
            const textoBusqueda = this.value.toLowerCase().trim();
            elementosNominasItems.forEach(nominaItemEl => {
                const textoNomina = nominaItemEl.textContent.toLowerCase();
                const liParent = nominaItemEl.parentElement;
                if (textoNomina.includes(textoBusqueda)) {
                    liParent.style.display = '';
                } else {
                    liParent.style.display = 'none';
                }
            });

            elementosAnos.forEach(anoTituloEl => {
                const ulMeses = document.getElementById('meses-' + anoTituloEl.dataset.ano);
                if (ulMeses) {
                    const itemsVisibles = Array.from(ulMeses.children).some(li => li.style.display !== 'none');
                    anoTituloEl.parentElement.style.display = itemsVisibles ? '' : 'none';
                     if (textoBusqueda && itemsVisibles) {
                        ulMeses.classList.add('visible');
                    } else if (!textoBusqueda) {
                        ulMeses.classList.remove('visible');
                    }
                }
            });
        });
    }
});
</script>
</body>
</html>
<?php
if (function_exists('get_footer')) {
    get_footer();
}
?>
?>