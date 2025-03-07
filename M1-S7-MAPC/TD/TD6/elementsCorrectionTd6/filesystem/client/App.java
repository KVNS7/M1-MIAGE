package org.example.tds.td6.filesystem.client;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.example.tds.td6.filesystem.business.api.commands.Command;
import org.example.tds.td6.filesystem.business.api.structure.files.File;
import org.example.tds.td6.filesystem.business.api.structure.identification.FileId;
import org.example.tds.td6.filesystem.business.api.structure.identification.UserId;
import org.example.tds.td6.filesystem.business.impl.commands.FileCreation;
import org.example.tds.td6.filesystem.business.impl.commands.LinkCreation;
import org.example.tds.td6.filesystem.business.impl.commands.DirectoryCreation;
import org.example.tds.td6.filesystem.business.impl.commands.UserCreation;
import org.example.tds.td6.filesystem.business.impl.structure.files.FileSystem;
import org.example.tds.td6.filesystem.business.impl.visitors.FileVisitor;
import org.example.tds.td6.filesystem.business.impl.visitors.Afficheur;
import org.example.tds.td6.filesystem.business.impl.visitors.CompteNombre;
import org.example.tds.td6.filesystem.business.impl.visitors.RechercheType;

// Notes : 
//
// 0.
// Du texte on comprend qu'on va être amenés à utiliser plusieurs patrons :
// - Composite pour la structure où fichier = fichier simple ou lien ou répertoire, avec répertoire
//   composé de fichiers (traitement uniforme des types de fichiers + composition)
// - Factory pour contrôler la création et éviter les "new" créant des états incorrects des données.
//   (rattachement à répertoire parent, gestion des ids uniques, nombre max de fichiers)
//
// 1.
// Ici j'utilise des Command(es) pour représenter les actions sur le système.
// Le principe est simple.
// On crée une commande (une instance d'une des classes implantant une interface)
// en lui passant les paramètres de travail. Ensuite dans un second temps on peut
// l'exécuter en appelant une méthode spécifique. Si la commande agit sur un contexte
// alors il faut lui passer. Cela peut se faire à l'instanciation ou lors de l'exécution.
// Si on n'utilisait pas le patron Command, on appelerait directement les méthodes du contexte.
// C'est à dire qu'au lieu de :
// Command c = new Command1(p1, p2)
// c.execute(contexte)
// ou encore :
// Command c = new Command1(contexte, p1, p2)
// c.execute()
// on aurait directement fait :
// contexte.commande1(p1, p2)
// Vous pouvez regarder le contenu des classes de commande pour comprendre et essayer de faire sans.
//
// 2.
// A plusieurs endroits on fait directement des get() sur des Optionals qu'on sait non vides.
//
// 3.
// Le code n'est pas toujours DRY. C'est un équilibre entre DRY et lisibilité.
//
// 4.
// Une main n'est pas "des tests". Mais pas compliqué de transformer cela. Laissé en exercice.
//
// 5.
// Bien regarder tout constructeur qui n'est pas "public".
// La raison est souvent un patron de création ...
//
// 6.
// Bien analyser la structuration en paquetages. On sépare interfaces et réalisations.
// On essaye de bien respecter SOLID avec des dépendances à des abstractions (interfaces)
// plutôt qu'à des réalisations (classes). Si possible on limite ces dernières aux "new" et 
// on se rappelle de typer les variables par des interfaces.
//
// 7.
// Noter l'utilisation d'identifications de fichiers vs d'utilisateurs. On aurait pu utiliser "Id".
// Mais un identificateur de fichier et d'utilisateur ce n'est pas pareil. Donc on a séparé.
// Ici il n'y a pas de différence en termes de services rendus (API). C'est purement pour le typage.
//
// 8.
// Un très bon exercice est de réaliser le diagramme de classes (avec paquetages) du code.
// Cela permet de mieux l'appréhender (et cela pourrait être demandé à un examen ou un CC).

public class App {
    public static void main(String[] args) {
        //
        // creation d'un système et helper
        //
        FileSystem sys = new FileSystem();
        Function<FileId, Optional<File>> recherche = fid -> sys.getFileFromId(fid);

        //
        // utilisateurs
        //
        Command<Optional<UserId>> cdeBob = new UserCreation(sys);
        Command<Optional<UserId>> cdePatrick = new UserCreation(sys);
        UserId bob = cdeBob.execute().get();
        UserId patrick = cdePatrick.execute().get();

        //
        // répertoires
        //
        final String BOB = "spongebob";
        final String PAT = "patrickstar";
        final String COURS = "cours";
        final String DL = "dernier CM";
        final String FOO = "nom pas important";
        final String BAR = "contenu pas important";
        final String CM1 = "cm1.pdf";
        final String CM2 = "cm2.pdf";
        final String CONTENUCM1 = "contenu cm 1";
        final String CONTENUCM2 = "contenu cm 2";
        //
        Command<Optional<FileId>> cdeBobHome = new DirectoryCreation(sys, BOB, bob, sys.ROOT);
        Command<Optional<FileId>> cdePatrickHome = new DirectoryCreation(sys, PAT, patrick, sys.ROOT);
        FileId bobHome = cdeBobHome.execute().get();
        FileId patrickHome = cdePatrickHome.execute().get();
        //
        Command<Optional<FileId>> cdeCours = new DirectoryCreation(sys, COURS, bobHome);
        FileId cours = cdeCours.execute().get();
        Optional<FileId> coursDoublon = cdeCours.execute();
        
        //
        // fichiers
        //
        Command<Optional<FileId>> creationCM1 = new FileCreation(sys, CM1, cours, CONTENUCM1);
        Command<Optional<FileId>> creationCM2 = new FileCreation(sys, CM2, cours, CONTENUCM2);
        FileId cm1 = creationCM1.execute().get();
        FileId cm2 = creationCM2.execute().get();
        Command<Optional<FileId>> cdePb1 = new FileCreation(sys, FOO, cm1, BAR);
        Optional<FileId> fichierDansFichier = cdePb1.execute();

        //
        // liens
        //
        Command<Optional<FileId>> creationDernierCM = new LinkCreation(sys, DL, bobHome, cm2);
        FileId derniercm = creationDernierCM.execute().get();
        
        //
        // "tests"
        //
        //
        List<FileVisitor> visitors = List.of(
            new Afficheur(),
            new CompteNombre(),
            new RechercheType("pdf"));
        //
        Optional<File> root = recherche.apply(sys.ROOT);
        if (root.isPresent()) {
            visitors.forEach(v -> {
                root.get().accept(v);
                System.out.println(v.value());
            });
        }
    }
}
