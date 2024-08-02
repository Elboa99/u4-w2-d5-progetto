package org.example.catalogo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogoBiblioteca {
    private List<ElementoCatalogo> catalogo;

    public CatalogoBiblioteca() {
        this.catalogo = new ArrayList<>();
    }

    public void aggiungiElemento(ElementoCatalogo elemento) {
        catalogo.add(elemento);
    }

    public void rimuoviElemento(String codiceISBN) {
        catalogo.removeIf(elemento -> elemento.getCodiceISBN().equals(codiceISBN));
    }

    public ElementoCatalogo cercaPerISBN(String codiceISBN) {
        return catalogo.stream()
                .filter(elemento -> elemento.getCodiceISBN().equals(codiceISBN))
                .findFirst()
                .orElse(null);
    }

    public List<ElementoCatalogo> cercaPerAnno(int annoPubblicazione) {
        return catalogo.stream()
                .filter(elemento -> elemento.getAnnoPubblicazione() == annoPubblicazione)
                .collect(Collectors.toList());
    }

    public List<ElementoCatalogo> cercaPerAutore(String autore) {
        return catalogo.stream()
                .filter(elemento -> elemento instanceof Libro && ((Libro) elemento).getAutore().equals(autore))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "CatalogoBiblioteca{" +
                "catalogo=" + catalogo +
                '}';
    }
}