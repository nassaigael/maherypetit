package com.mahery.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Groupe {
    private Administrateur administrateur;
    private List<Utilisateur> membres;
    private List<Publication> publications;

    public Groupe(Administrateur administrateur) {
        this.administrateur = administrateur;
        this.membres = new ArrayList<>();
        this.publications = new ArrayList<>();
        membres.add(administrateur);
    }

    public void ajouterMembre(Utilisateur utilisateur) {
        membres.add(utilisateur);
    }

    public boolean supprimerMembre(Utilisateur cible, Utilisateur acteur) {
        if (acteur instanceof Administrateur && !acteur.equals(cible)) {
            return membres.remove(cible);
        }
        return false;
    }

    public void ajouterPublication(Publication pub) {
        publications.add(pub);
    }

    public boolean supprimerPublication(Publication pub, Utilisateur utilisateur) {
        if (utilisateur instanceof Moderateur || pub.getAuteur().equals(utilisateur)) {
            return publications.remove(pub);
        }
        return false;
    }

    public boolean contientUtilisateur(Utilisateur utilisateur) {
        return membres.contains(utilisateur);
    }

    public List<Publication> rechercherPublications(String motCle) {
        return publications.stream()
                .filter(pub -> pub.getContenu().toLowerCase().contains(motCle.toLowerCase()))
                .collect(Collectors.toList());
    }

    public int nombreTotalPublications() {
        return publications.size();
    }

    public List<Utilisateur> getMembres() {
        return membres;
    }

    public List<Publication> getPublications() {
        return publications;
    }
}
