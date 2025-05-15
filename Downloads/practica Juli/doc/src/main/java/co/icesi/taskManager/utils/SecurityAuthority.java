package co.icesi.taskManager.utils;


import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import co.icesi.taskManager.model.Permission;

/**
 * Implementación personalizada de GrantedAuthority de Spring Security.
 * Representa un permiso del sistema basado en la entidad Permission.
 */
@AllArgsConstructor // Genera automáticamente un constructor con el campo 'permission'
public class SecurityAuthority implements GrantedAuthority {

    private final Permission permission;

    /**
     * Devuelve el nombre del permiso como cadena.
     * Spring Security usa este valor para hacer validaciones como:
     * hasAuthority("MANAGE_USERS") o hasRole("ADMIN")
     */
    @Override
    public String getAuthority() {
        return permission.getName(); // Devuelve algo como "MANAGE_PRODUCTS"
    }
}
