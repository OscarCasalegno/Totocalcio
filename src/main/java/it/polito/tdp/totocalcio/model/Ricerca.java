package it.polito.tdp.totocalcio.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {

    private Pronostico pronostico;
    private int N;

    private List<Risultato> soluzione;

    public List<Risultato> cerca(Pronostico pronostico) {
        this.pronostico = pronostico;
        this.N = pronostico.size();
        this.soluzione = new ArrayList<>();
        List<RisultatoPartita> parziale = new ArrayList<>();
        int livello = 0;

        this.ricorsiva(parziale, livello);

        return this.soluzione;

    }

    private void ricorsiva(List<RisultatoPartita> parziale, Integer livello) {

        // caso terminale
        if (livello == N) {
            // questa soluzione parziale è una soluzione completa
            soluzione.add(new Risultato(parziale));
        } else {
            // caso generale
            PronosticoPartita pp = this.pronostico.get(livello);
            // pp sono i sotto-problemi da provare

            for (RisultatoPartita ris : pp.getRisultati()) {
                // provo a mettere ris nella posizione livello della soluzione parziale

                // costruzione soluzione parziale (sottoproblema)
                parziale.add(ris);
                // chiamata ricorsiva
                this.ricorsiva(parziale, livello + 1);
                // backtracking
                parziale.remove(parziale.size() - 1);
            }

        }

    }

}
/*
 * Livello = il numero di partita che sto considerando; le partite da 0 a
 * livello-1 sono già state decise; la partita di indice livello la devo
 * decidere io; le partite da livello+1 in poi le decideranno le procedure
 * ricorsive sottostanti;
 * 
 * Soluzione parziale: un elenco di RisultatoPartita di lunghezza pari al
 * livello.
 * 
 * Soluzione totale: ho N risultati.
 * 
 * Codizione di terminazione: livello==N.
 * 
 * Generazione delle soluzioni del livello: proviamo tutti i pronostici definiti
 * per quel livello.
 */