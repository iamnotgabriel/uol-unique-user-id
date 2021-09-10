package org.fatec.uniqueuserid.users;

import static org.junit.jupiter.api.Assertions.*;

public class UserAsserts {

    public static void equalsDTO(UserCreationDTO dto, User user) {
        assertNotNull(user, "User can not be null");
        assertNotNull(dto, "UserCreationDTO can not be null");
        assertEquals(dto.name, user.getName(), "invalid user.name");
        assertEquals(dto.email, user.getEmail(), "invalid user.email");
        assertEquals(dto.phone, user.getPhone(), "invalid user.phone");
        assertEquals(dto.password, user.getPassword(), "invalid user.password");
        assertNotNull(user.getId(), "user.id can not be null");
        assertTrue(user.getId() > 0, "user.id can not be negative");
    }

}
