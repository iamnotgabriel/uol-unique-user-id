package org.fatec.uniqueuserid.users.service;

import org.fatec.uniqueuserid.users.SignUp;

import java.util.List;

public class DbscanInput {
    public final int[] keyups;
    public final int[] keydowns;

    public DbscanInput(SignUp signUp) {
        this.keyups = signUp.getKeyUps().stream().mapToInt(i->i).toArray();
        this.keydowns = signUp.getKeyDowns().stream().mapToInt(i->i).toArray();
    }
}
