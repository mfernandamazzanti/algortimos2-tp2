package aed;

public class InfoMateria{

    // Invariante de representación: 
    // paresCarreraMateria.length>=1 (toda materia está asociada a por lo menos una carrera), paresCarreraMateria no contiene elementos 'null' y no contiene elementos repetidos

    private ParCarreraMateria[] paresCarreraMateria;

    public InfoMateria(ParCarreraMateria[] paresCarreraMateria){
        this.paresCarreraMateria = paresCarreraMateria;
    }

    public ParCarreraMateria[] getParesCarreraMateria() {
        return this.paresCarreraMateria;
    }
}
