package com.educational.platform.users;

import org.springframework.security.core.GrantedAuthority;

/**
 * Represents possible roles in the system.
 */
public enum Role implements GrantedAuthority {

    ROLE_ADMIN,
    ROLE_STUDENT,
    ROLE_TEACHER;

    public static Role from(RoleDTO role) {
        switch (role) {
            case ROLE_STUDENT:
                return ROLE_STUDENT;
            case ROLE_TEACHER:
                return ROLE_TEACHER;
        }

        return null;
    }

    public String getAuthority() {
        return name();
    }

    public RoleDTO toDTO() {
        switch (this) {
            case ROLE_STUDENT:
                return RoleDTO.ROLE_STUDENT;
            case ROLE_TEACHER:
                return RoleDTO.ROLE_TEACHER;
        }

        return null;
    }
}
