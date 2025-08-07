package com.mahery.model;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Utilisateur {
    private String id;
    private String nomUtilisateur;
    private String email;
    private LocalDate dateCreation;

    public Utilisateur(String id, String nomUtilisateur, String email) {
        this.id = id;
        this.nomUtilisateur = nomUtilisateur;
        this.email = email;
        this.dateCreation = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || !(obj instanceof Utilisateur))
            return false;

        Utilisateur autre = (Utilisateur) obj;

        return this.id.equals(autre.id) &&
                this.nomUtilisateur.equals(autre.nomUtilisateur) &&
                this.email.equals(autre.email);
    }


    @Override
    public String toString() {
        return nomUtilisateur + " (" + email + ")";
    }
}
