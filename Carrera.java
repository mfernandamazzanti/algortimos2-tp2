package aed;

public class Carrera {

    // Invariante de representación: 
    // materias != null
    // materias relaciona cada materia (nombre de la materia) de una carrera con su plantel docente, con los estudiantes anotados en esa materia y con los nombres que tiene esa materia en otras carreras (todo eso adentro de la clase Materia)
    // las claves de materias son no vacías (las materias deben tener un nombre no vacío)
    // ninguna clave de materias está asociada al valor 'null' (no contiene elementos 'null')
    // nombre no puede ser vacío (una carrera tiene que tener un nombre no vacio)
    
    private Arbolito<Materia> materias;
    private String nombre;

    public Carrera() {
        this.materias = new Arbolito<>();
    }


    public Arbolito<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(Arbolito<Materia> materias) {
        if (materias != null) {
            this.materias = materias;
        } else {
            throw new IllegalArgumentException("Materias no puede ser null.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("Nombre no puede ser vacío.");
        }
    }

    public void cerrarUnaMateria(String nombreMateria) {
        if (materias.pertenece(nombreMateria)) {
            materias.eliminar(nombreMateria);
        }
    }
}
