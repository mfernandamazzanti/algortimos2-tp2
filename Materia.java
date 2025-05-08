package aed;

import java.util.ArrayList;

public class Materia {

    // Invariante de representación:
    // PlantelDocente != null , estudiantesAnotados != null y equivalentes != null
    // PlantelDocente.size()=4 (hay exactamente 4 cargos docentes) y todos los elementos de PlantelDocente son >=0 (no puede haber una cantidad negativa de docentes)
    // para todo estudiante en estudiantesAnotados de una materia,: la materia debe pertencer a las MateriasIncriptas de cada estudiante
    // estudiantesAnotados no tiene elementos 'null' y no tiene elementos repetidos
    // equivalentes no tiene elementos null, no tiene elementos repetidos y equivalentes.size()>=1 (una materia debe estar asociada a por lo menos una carrera)

    ArrayList<Integer>PlantelDocente; 
    ArrayList<Estudiante>estudiantesAnotados;
    ArrayList<ParCarreraMateria> equivalentes; 

    public Materia(){
        this.PlantelDocente = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            this.PlantelDocente.add(0);
        }
        this.estudiantesAnotados = new ArrayList<>(); 
    }

    public void AgregarDocente(int pos){
        if(pos >=0 && pos < this.PlantelDocente.size()){
            //incrementa en uno la cantidad de docentes en la posicion pos
            this.PlantelDocente.set(pos, this.PlantelDocente.get(pos)+1);
        }
    }
    public void EliminarDocente(int pos){
        if(pos >=0 && pos < this.PlantelDocente.size()){
            //decrementa en uno la cantidad de docentes en la posicion pos
            this.PlantelDocente.set(pos, this.PlantelDocente.get(pos)+1);
        }
    }
    public int[] ObtenerPlantel(){
        int[] plantel = new int[4];
        for(int i = 0; i < plantel.length; i++){
            plantel[i] = this.PlantelDocente.get(i);
        }
        return plantel;
    }
}