package org.example.catalogo;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static CatalogoBiblioteca catalogo = new CatalogoBiblioteca();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            try {
                mostraMenu();
                int scelta = scanner.nextInt();
                scanner.nextLine();

                switch (scelta) {
                    case 1:
                        aggiungiElemento();
                        break;
                    case 2:
                        rimuoviElemento();
                        break;
                    case 3:
                        cercaPerAnno();
                        break;
                    case 4:
                        cercaPerAutore();
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Opzione non valida, riprova.");
                }
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void mostraMenu() {
        System.out.println("Scegli un'operazione dal menu:");
        System.out.println("1. Inserisci un nuovo elemento");
        System.out.println("2. Elimina un elemento tramite codice ISBN");
        System.out.println("3. Cerca elementi per anno di pubblicazione");
        System.out.println("4. Cerca elementi per autore");
        System.out.println("5. Uscire");
    }

    private static void aggiungiElemento() {
        try {
            System.out.println("Scegli il tipo di elemento da inserire:");
            System.out.println("1. Libro");
            System.out.println("2. Rivista");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Inserisci il codice ISBN: ");
            String codiceISBN = scanner.nextLine();

            System.out.print("Inserisci il titolo: ");
            String titolo = scanner.nextLine();

            System.out.print("Inserisci l'anno di pubblicazione: ");
            int annoPubblicazione = scanner.nextInt();

            System.out.print("Inserisci il numero di pagine: ");
            int numeroPagine = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    System.out.print("Inserisci l'autore: ");
                    String autore = scanner.nextLine();

                    System.out.print("Inserisci il genere: ");
                    String genere = scanner.nextLine();

                    Libro libro = new Libro(codiceISBN, titolo, annoPubblicazione, numeroPagine, autore, genere);
                    catalogo.aggiungiElemento(libro);
                    System.out.println("Libro inserito correttamente!");
                    break;
                case 2:
                    System.out.println("Seleziona la periodicità (1: SETTIMANALE, 2: MENSILE, 3: SEMESTRALE): ");
                    int periodicitaScelta = scanner.nextInt();
                    scanner.nextLine();

                    Rivista.Periodicita periodicita;
                    switch (periodicitaScelta) {
                        case 1:
                            periodicita = Rivista.Periodicita.SETTIMANALE;
                            break;
                        case 2:
                            periodicita = Rivista.Periodicita.MENSILE;
                            break;
                        case 3:
                            periodicita = Rivista.Periodicita.SEMESTRALE;
                            break;
                        default:
                            System.out.println("Scelta non valida, rivista non inserita.");
                            return;
                    }

                    Rivista rivista = new Rivista(codiceISBN, titolo, annoPubblicazione, numeroPagine, periodicita);
                    catalogo.aggiungiElemento(rivista);
                    System.out.println("Rivista inserita correttamente!");
                    break;
                default:
                    System.out.println("Scelta non valida, elemento non inserito.");
            }
        } catch (Exception e) {
            System.out.println("Errore durante l'inserimento dell'elemento: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void rimuoviElemento() {
        try {
            System.out.print("Inserisci il codice ISBN dell'elemento da eliminare: ");
            String codiceISBN = scanner.nextLine();

            catalogo.rimuoviElemento(codiceISBN);
            System.out.println("Elemento eliminato (se esistente).");
        } catch (Exception e) {
            System.out.println("Errore durante la rimozione dell'elemento: " + e.getMessage());
        }
    }

    private static void cercaPerAnno() {
        try {
            System.out.print("Inserisci l'anno di pubblicazione da cercare: ");
            int annoPubblicazione = scanner.nextInt();
            scanner.nextLine();

            List<ElementoCatalogo> risultati = catalogo.cercaPerAnno(annoPubblicazione);
            if (risultati.isEmpty()) {
                System.out.println("Nessun elemento trovato per l'anno di pubblicazione: " + annoPubblicazione);
            } else {
                System.out.println("Elementi trovati:");
                for (ElementoCatalogo elemento : risultati) {
                    System.out.println(elemento);
                }
            }
        } catch (Exception e) {
            System.out.println("Errore durante la ricerca per anno di pubblicazione: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void cercaPerAutore() {
        try {
            System.out.print("Inserisci il nome dell'autore da cercare: ");
            String autore = scanner.nextLine();

            List<ElementoCatalogo> risultati = catalogo.cercaPerAutore(autore);
            if (risultati.isEmpty()) {
                System.out.println("Nessun elemento trovato per l'autore: " + autore);
            } else {
                System.out.println("Elementi trovati:");
                for (ElementoCatalogo elemento : risultati) {
                    System.out.println(elemento);
                }
            }
        } catch (Exception e) {
            System.out.println("Errore durante la ricerca per autore: " + e.getMessage());
        }
    }
}

