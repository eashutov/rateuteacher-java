package ru.shutov.rateuteacher.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactDetails {
    // TODO:
    // 1. create new table (new migration)
    // 2. uncomment @Entity
    // 3. add hibernate annotations on fields
    private boolean phoneVisible;
    private boolean officeVisible;
    private boolean emailVisible;
}
