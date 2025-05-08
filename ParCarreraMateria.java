package aed;

public class ParCarreraMateria {

    // Invariante de representación: 
    // carrera es no vacío (una carrera debe tener un nombre no vacío)
    // carreraObj != null
    // carreraObj.nombre = carrera
    // carreraObj.materias.pertence(nombreMateria)==true
    // nombreMateria es no vacío (una materia debe tener un nombre no vacío)

    private String carrera;
    private Carrera carreraObj;
    private String nombreMateria;

    public ParCarreraMateria(String carrera, String nombreMateria) {
        if (carrera == null || carrera.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la carrera no puede ser vacío.");
        }
        if (nombreMateria == null || nombreMateria.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la materia no puede ser vacío.");
        }
        this.carrera = carrera;
        this.nombreMateria = nombreMateria;
    }

    public String getCarrera() {
        return this.carrera;
    }

    public void setCarrera(String carrera) {
        if (carrera != null && !carrera.isEmpty()) {
            this.carrera = carrera;
        } else {
            throw new IllegalArgumentException("El nombre de la carrera no puede ser vacío.");
        }
    }

    public Carrera getCarreraObj() {
        return this.carreraObj;
    }

    public void setCarreraObj(Carrera carreraObj) {
        this.carreraObj = carreraObj;
    }

    public String getNombreMateria() {
        return this.nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        if (nombreMateria != null && !nombreMateria.isEmpty()) {
            this.nombreMateria = nombreMateria;
        } else {
            throw new IllegalArgumentException("El nombre de la materia no puede ser vacío.");
        }
    }
}
