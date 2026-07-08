CineMax - Sistema di Gestione Cinema
=====================================
Autori: Benaoun Tamim - Università degli Studi dell'Insubria - Sede CO

REQUISITI
---------
- Java JDK 23 o superiore

STRUTTURA DEL PROGETTO
-----------------------
- src/        : codice sorgente Java
- bin/        : bytecode compilato (.class)
- data/       : file di dati (proiezioni.csv, utenti.csv)
- doc/        : documentazione (manuali PDF e JavaDoc)

COMPILAZIONE
------------
Dalla cartella radice del progetto eseguire:

    javac -d bin *.java

ESECUZIONE
----------
Dopo la compilazione, eseguire:

    java -cp bin bin.CineMax

CREDENZIALI DI TEST
--------------------
Proiezionista: username: mrossi      password: pass123
Bigliettaio:   username: gferrari    password: pass789