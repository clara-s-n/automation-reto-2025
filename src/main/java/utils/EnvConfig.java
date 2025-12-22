package utils;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Clase para manejar la configuración desde el archivo .env
 * Proporciona acceso centralizado a las variables de entorno
 */
public class EnvConfig {

    private static Dotenv dotenv;

    static {
        try {
            // Cargar el archivo .env desde la raíz del proyecto
            dotenv = Dotenv.configure()
                    .directory(".")
                    .ignoreIfMalformed()
                    .ignoreIfMissing()
                    .load();
        } catch (Exception e) {
            System.err.println("Error al cargar archivo .env: " + e.getMessage());
            dotenv = null;
        }
    }

    /**
     * Obtiene la ruta del driver de Edge desde el .env
     * 
     * @return Ruta del msedgedriver.exe
     */
    public static String getEdgeDriverPath() {
        if (dotenv != null) {
            return dotenv.get("WEBDRIVER_EDGE_PATH", "C:\\msedgedriver.exe");
        }
        return "C:\\msedgedriver.exe";
    }

    /**
     * Obtiene la ruta donde se guardarán las capturas de pantalla
     * 
     * @return Ruta del directorio de screenshots
     */
    public static String getScreenshotsPath() {
        if (dotenv != null) {
            return dotenv.get("SCREENSHOTS_PATH", "C:\\screenshots_tests");
        }
        return "C:\\screenshots_tests";
    }

    /**
     * Obtiene cualquier variable del archivo .env
     * 
     * @param key          Nombre de la variable
     * @param defaultValue Valor por defecto si no existe
     * @return Valor de la variable o el valor por defecto
     */
    public static String get(String key, String defaultValue) {
        if (dotenv != null) {
            return dotenv.get(key, defaultValue);
        }
        return defaultValue;
    }

    /**
     * Obtiene cualquier variable del archivo .env
     * 
     * @param key Nombre de la variable
     * @return Valor de la variable o null si no existe
     */
    public static String get(String key) {
        if (dotenv != null) {
            return dotenv.get(key);
        }
        return null;
    }
}
