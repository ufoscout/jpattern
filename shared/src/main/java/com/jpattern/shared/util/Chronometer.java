package com.jpattern.shared.util;

import java.io.Serializable;



/**
 * Classe di utilita' che realizza un cronometro per misurare
 * intervalli temporali espressi in millisecondi. Basata sull'orologio
 * di sistema (vedere <code>System.currentTimeMillis()</code>).
 * <p>
 * La precisione delle misure di tempo effettuate dipende dunque
 * dall'accuratezza con la quale e' realizzato il <i>timer</i> di
 * sistema Java: esperimenti pratici rivelano che in ambiente Windows
 * la sensibilita' di <code>System.currentTimeMillis()</code> e' di
 * 10 ms, mentre su stazioni SUN Sparc, ad esempio, e' di 1 ms.
 * <p>
 * Corretta anche in situazioni <i>multi-threading</i>.
 * <p>
 *
 * @author    <em>Marco Cimatti</em>
 * @version   1.0
 */
public final class Chronometer implements Serializable {

	private static final long serialVersionUID = 1L;

/** Accumulatore contenente il numero dei millisecondi trascorsi. */
  private long contatore;

  /** Istante temporale dell'ultimo avvio del cronometro. */
  private long avviato_a;

  /** Variabile di stato che indica se il cronometro sta avanzando oppure no. */
  private boolean avanzando;


  /**
   * Costruttore: resetta il cronometro invocando il metodo d'istanza
   * <code>azzera()</code>. Non avvia il conteggio; per fare cio' usare
   * i metodi <code>avanza()</code> ed <code>avanzaDaCapo()</code>.
   *
   * @see   #reset()
   * @see   #start()
   * @see   #restart()
   */
  public Chronometer() { reset(); }

  /** Metodo per (fermare ed) azzerare del cronometro. */
  public final void reset() {
    synchronized (this) {
      contatore = 0;
      avanzando = false;
    }
  }

  /**
   * Metodo che fa (ri)partire il conteggio. Non azzera il
   * cronometro, ma fa procedere la misura del tempo partendo dal
   * valore immagazzinato nell'accumulatore.
   * <p>
   * Il cronometro puo' essere fermato mediante <code>ferma()</code>.
   *
   * @see   #pause()
   */
  public void start() {
    synchronized (this) {
      avviato_a = System.currentTimeMillis();
      avanzando = true;
    }
  }

  /**
   * Metodo che blocca l'avanzamento del cronometro. Usare
   * <code>avanza()</code> per far ripartire il conteggio,
   * <code>avanzaDaCapo()</code> per azzerare il tutto prima di
   * dare inizio al conteggio.
   *
   * @see   #start()
   * @see   #restart()
   */
  public void pause() {
    synchronized (this) {
      contatore += System.currentTimeMillis() - avviato_a;
      avanzando = false;
    }
  }

  /** Azzera il cronometro e ne fa partire il conteggio. */
  public void restart() {
    reset();
    start();
  }

  /**
   * Lettura del conteggio corrente effettuato dal cronometro.
   * Chiamate successive a questo metodo riportano valori diversi
   * nel caso in cui il cronometro stia avanzando.
   *
   * @return   il numero totale di millisecondi contati dall'istanza.
   */
  public long read() {
    synchronized (this) {
      return avanzando ? contatore + System.currentTimeMillis() - avviato_a : contatore;
    }
  }

  /**
   * Conversione in stringa del conteggio corrente. La lettura del
   * valore viene effettuata mediante il metodo <code>leggi()</code>.
   *
   * @return   una stringa rappresentante il numero di millisecondi
   *           contati dall'istanza in questione.
   * @see      #read()
   */
  public String toString() {
    return "" + read();
  }
  
}
