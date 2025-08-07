package com.mahery.Test;

import com.mahery.model.Administrateur;
import com.mahery.model.Commentaire;
import com.mahery.model.Groupe;
import com.mahery.model.Moderateur;
import com.mahery.model.Publication;
import com.mahery.model.UtilisateurStandard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GroupeTest {
    private Groupe groupe;
    private Administrateur admin;
    private UtilisateurStandard user1;
    private Moderateur modo;

    @BeforeEach
    public void setUp() {
        admin = new Administrateur("1", "admin", "admin@mail.com");
        groupe = new Groupe(admin);

        user1 = new UtilisateurStandard("2", "user1", "user1@mail.com");
        modo = new Moderateur("3", "modo", "modo@mail.com");

        groupe.ajouterMembre(user1);
        groupe.ajouterMembre(modo);
    }

    @Test
    public void testCreationPublication() {
        Publication pub = new Publication("Bonjour à tous", user1);
        groupe.ajouterPublication(pub);
        assertTrue(groupe.getPublications().contains(pub));
        assertEquals(1, groupe.nombreTotalPublications());
    }

    @Test
    public void testAjoutCommentaire() {
        Publication pub = new Publication("Un post test", user1);
        groupe.ajouterPublication(pub);
        Commentaire com = new Commentaire("Super !", modo);
        pub.ajouterCommentaire(com);
        assertTrue(pub.getCommentaires().contains(com));
    }

    @Test
    public void testSuppressionPublicationParAuteur() {
        Publication pub = new Publication("Test", user1);
        groupe.ajouterPublication(pub);
        boolean removed = groupe.supprimerPublication(pub, user1);
        assertTrue(removed);
        assertFalse(groupe.getPublications().contains(pub));
    }

    @Test
    public void testSuppressionPublicationParModerateur() {
        Publication pub = new Publication("Post de user1", user1);
        groupe.ajouterPublication(pub);
        boolean removed = groupe.supprimerPublication(pub, modo);
        assertTrue(removed);
        assertFalse(groupe.getPublications().contains(pub));
    }

    @Test
    public void testSuppressionCommentaireParAuteur() {
        Publication pub = new Publication("Post avec commentaire", user1);
        Commentaire com = new Commentaire("Commentaire", user1);
        pub.ajouterCommentaire(com);
        boolean removed = pub.supprimerCommentaire(com, user1);
        assertTrue(removed);
        assertFalse(pub.getCommentaires().contains(com));
    }

    @Test
    public void testSuppressionCommentaireParModerateur() {
        Publication pub = new Publication("Autre post", user1);
        Commentaire com = new Commentaire("Un commentaire", user1);
        pub.ajouterCommentaire(com);
        boolean removed = pub.supprimerCommentaire(com, modo);
        assertTrue(removed);
        assertFalse(pub.getCommentaires().contains(com));
    }

    @Test
    public void testSuppressionUtilisateurParAdmin() {
        boolean removed = groupe.supprimerMembre(user1, admin);
        assertTrue(removed);
        assertFalse(groupe.getMembres().contains(user1));
    }

    @Test
    public void testEchecSuppressionUtilisateurParModerateur() {
        boolean removed = groupe.supprimerMembre(user1, modo);
        assertFalse(removed);
        assertTrue(groupe.getMembres().contains(user1));
    }
}
