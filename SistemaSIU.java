package aed;

import java.util.ArrayList;

public class SistemaSIU {

    enum CargoDocente {
        AY2,
        AY1,
        JTP,
        PROF
    }

    // Invariante de representación:
    // estudiantes relaciona cada estudiante (LU) a las materias y a la cantidad de materias a la que está inscripto (adentro de la clase Estudiante)
    // carreras relaciona cada carrera (nombre de la carrera) a las materias pertenecientes a esa carrera (adentro de la clase Carrera)
    // carreras != null y estudiantes != null
    // las claves de carreras son no vacías (las carreras deben tener un nombre no vacío)
    // estudiantes tiene claves acotadas de largo 6 (todas las libretas tienen 6 caracteres)
    // carreras y estudiantes no tienen ninguna clave asociada al valor 'null' (no contienen elementos 'null')
    private Arbolito<Carrera> carreras;
    private Arbolito<Estudiante> estudiantes;

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias) {
        carreras = new Arbolito<>();
        estudiantes = new Arbolito<>();

        for (InfoMateria infoMateria : infoMaterias) {
            Materia materia = new Materia();
            materia.equivalentes = new ArrayList<>(); // Inicializa Equivalentes correctamente como HashSet
            for (ParCarreraMateria par : infoMateria.getParesCarreraMateria()) {
                Carrera car = new Carrera();
                materia.equivalentes.add(par);
                if (carreras.pertenece(par.getCarrera())) {
                    car = carreras.BuscarNodo(par.getCarrera()).getValor();
                } else {
                    carreras.insertar(car, par.getCarrera());
                }
                par.setCarreraObj(car);
                car.getMaterias().insertar(materia, par.getNombreMateria());
            }
        }

        for (String libreta : libretasUniversitarias) {
            Estudiante est = new Estudiante();
            est.setLU(libreta);
            estudiantes.insertar(est, libreta);
        }
    }

    public void inscribir(String estudiante, String carrera, String materia) {
        Estudiante est = estudiantes.BuscarNodo(estudiante).getValor();

        Carrera car = carreras.BuscarNodo(carrera).getValor();
        Materia mat = car.getMaterias().BuscarNodo(materia).getValor();
        mat.estudiantesAnotados.add(est);
        est.incrementarCardinal(); // Incrementar el contador de materias inscriptas
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia) {
        Carrera car = carreras.BuscarNodo(carrera).getValor();
        Materia mat = car.getMaterias().BuscarNodo(materia).getValor();
        switch (cargo) {
            case AY2:
                mat.AgregarDocente(3);
                break;
            case AY1:
                mat.AgregarDocente(2);
                break;
            case JTP:
                mat.AgregarDocente(1);
                break;
            case PROF:
                mat.AgregarDocente(0);
                break;
            default:
                break;
        }
    }

    public int[] plantelDocente(String materia, String carrera) {
        Carrera car = carreras.BuscarNodo(carrera).getValor();
        Materia mat = car.getMaterias().BuscarNodo(materia).getValor();
        return mat.ObtenerPlantel();
    }

    public int inscriptos(String materia, String carrera) {
        Carrera car = carreras.BuscarNodo(carrera).getValor();
        Materia mat = car.getMaterias().BuscarNodo(materia).getValor();
        return mat.estudiantesAnotados.size();
    }

    public boolean excedeCupo(String materia, String carrera) {
        Carrera car = carreras.BuscarNodo(carrera).getValor();
        Materia mat = car.getMaterias().BuscarNodo(materia).getValor();
        float cantidadEstudiantes = mat.estudiantesAnotados.size();
        int[] plantel = mat.ObtenerPlantel();
        if (cantidadEstudiantes == 0) return false;

        if (cantidadEstudiantes / plantel[0] > 250) {
            return true;
        }
        if (cantidadEstudiantes / plantel[1] > 100) {
            return true;
        }
        if (cantidadEstudiantes / plantel[2] > 20) {
            return true;
        }
        if (cantidadEstudiantes / plantel[3] > 30) {
            return true;
        }
        return false;
    }

    public String[] carreras() {
        ArrayList<String> listaCarreras = new ArrayList<>();
        recorrerArbolito(carreras.getRaiz(), "", listaCarreras);
        return listaCarreras.toArray(new String[0]);
    }

    private void recorrerArbolito(Arbolito<Carrera>.Nodo nodo, String claveActual, ArrayList<String> listaCarreras) {
        if (nodo == null) {
            return;
        }
        if (nodo.getValor() != null) {
            listaCarreras.add(claveActual);
        }
        for (Arbolito<Carrera>.Nodo hijo : nodo.getHijos()) {
            recorrerArbolito(hijo, claveActual + hijo.getFlag(), listaCarreras);
        }
    }

    public String[] materias(String carrera) {
        if (!carreras.pertenece(carrera)) {
            return new String[0];
        }

        Carrera car = carreras.BuscarNodo(carrera).getValor();
        ArrayList<String> listaMaterias = new ArrayList<>();
        recorrerArbolitoMaterias(car.getMaterias().getRaiz(), "", listaMaterias);
        return listaMaterias.toArray(new String[0]);
    }

    private void recorrerArbolitoMaterias(Arbolito<Materia>.Nodo nodo, String claveActual, ArrayList<String> listaMaterias) {
        if (nodo == null) {
            return;
        }
        if (nodo.getValor() != null) {
            listaMaterias.add(claveActual);
        }
        for (Arbolito<Materia>.Nodo hijo : nodo.getHijos()) {
            recorrerArbolitoMaterias(hijo, claveActual + hijo.getFlag(), listaMaterias);
        }
    }

    public int materiasInscriptas(String estudiante) {
        Estudiante est = estudiantes.BuscarNodo(estudiante).getValor();
        return est.getCardinal();
    }

    public void cerrarMateria(String materia, String carrera) {
        Carrera car = carreras.BuscarNodo(carrera).getValor();
        Materia mat = car.getMaterias().BuscarNodo(materia).getValor();

        if (mat.equivalentes != null && !mat.equivalentes.isEmpty()) {
            for (ParCarreraMateria par : mat.equivalentes) {
                par.getCarreraObj().cerrarUnaMateria(par.getNombreMateria());
            }
        }

        for (Estudiante est : mat.estudiantesAnotados) {
            est.decrementarCardinal();
        }
    }
}
