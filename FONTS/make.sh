#!/bin/bash

javac --release 11 Domini/*.java ; 
javac --release 11 Presentacio/*.java ; 
javac --release 11 Persistencia/*.java ; 
javac --release 11 ControladorDomini/*.java ;
javac --release 11 ControladorsDeDomini/*.java ;
javac --release 11 ControladorsDePresentacio/*.java ;
javac --release 11 ControladorPresentacio/*.java ;
javac --release 11 ControladorPersistencia/*.java ;
mv Domini/*.class ../EXE/CLASS/Domini;
mv Presentacio/*.class ../EXE/CLASS/Presentacio;
mv Persistencia/*.class ../EXE/CLASS/Persistencia;
mv ControladorDomini/*.class ../EXE/CLASS/ControladorDomini;
mv ControladorPersistencia/*.class ../EXE/CLASS/ControladorPersistencia;
mv ControladorPresentacio/*.class ../EXE/CLASS/ControladorPresentacio;
mv ControladorsDePresentacio/*.class ../EXE/CLASS/ControladorsDePresentacio;
mv ControladorsDeDomini/*.class ../EXE/CLASS/ControladorsDeDomini;

