package ru.shutov.rateuteacher.enums;

public enum Role {
    ROLE_ADMIN("ADMIN"), ROLE_MODERATOR("MODERATOR");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.name();
    }

    public String getRole() {
        return role;
    }
}
