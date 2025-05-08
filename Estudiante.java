package aed;

public class Estudiante {

    // Invariante de representación: 
    // MateriasInscriptas relaciona cada materia (nombre de la materia) a la que está inscripto el estudiante con su plantel docente, con los estudiantes anotados en esa materia y con los nombres que tiene esa materia en otras carreras (todo eso adentro de la clase Materia)
    // para toda materia en MateriasInscriptas de un estudiante: el estudiante debe pertenecer a los estudiantesAnotados de cada materia
    // todas las materias en MateriasInscriptas deben pertenecer a las materias de una Carrera
    // MateriasInscriptas != null
    // LU siempre tiene largo 6 (todas las libretas tienen 6 caracteres)
    // cardinal >= 0 (un estudiante no puede estar inscripto en una cantidad negativa de materias) y cardinal=MateriasInscriptas.cardinal() 
    // las claves de MateriasInscriptas son no vacías (las materias deben tener un nombre no vacío) y ninguna clave de MateriasInscriptas está asociada al valor 'null'

    private Arbolito<Materia> MateriasInscriptas;
    private String LU;
    private int cardinal; 

    public Estudiante() {
        this.MateriasInscriptas = new Arbolito<>();
        this.cardinal = 0;
    }

    public Arbolito<Materia> getMateriasInscriptas() {
        return MateriasInscriptas;
    }

    public void setMateriasInscriptas(Arbolito<Materia> materiasInscriptas) {
        this.MateriasInscriptas = materiasInscriptas;
    }

    public String getLU() {
        return LU;
    }

    public void setLU(String LU) {
        this.LU = LU;
    }

    public int getCardinal() {
        return cardinal;
    }

    public void setCardinal(int cardinal) {
        if (cardinal >= 0) {
            this.cardinal = cardinal;
        } else {
            throw new IllegalArgumentException("Cardinal no puede ser negativo.");
        }
    }

    public void incrementarCardinal() {
        this.cardinal++;
    }
    public void decrementarCardinal() {
        this.cardinal--;
    }
}
