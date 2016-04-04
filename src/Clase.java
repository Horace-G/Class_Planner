
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author horac
 */
public class Clase {
    
    protected String maestro;
    protected String hora;
    protected String nombreClase;
    protected int numeroMatriculados;
    public String alumnos;
    public int aula;

    public Clase(String maestro, String hora, String nombreClase, int aula) {
        this.maestro = maestro;
        this.hora = hora;
        this.nombreClase = nombreClase;
        this.alumnos = "";
        this.aula = aula;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    
    public String getMaestro() {
        return maestro;
    }

    public void setMaestro(String maestro) {
        this.maestro = maestro;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(String alumnos) {
        this.alumnos = alumnos;
    }

    

    public int getNumeroMatriculados() {
        return numeroMatriculados;
    }

    public void setNumeroMatriculados(int numeroMatriculados) {
        this.numeroMatriculados = numeroMatriculados;
    }
    
    public void matricularAlumno(String nombreAlumno){
        String [] alumnosSize = this.alumnos.split(",");
        if(alumnosSize.length == 40){
            JOptionPane.showMessageDialog(null, "Error!, La clase "+getNombreClase()+" a las "+getHora()+" esta llena!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            this.alumnos += nombreAlumno+",";
        }
    }

    public int getAula() {
        return aula;
    }

    public void setAula(int aula) {
        this.aula = aula;
    }
    
    
    
}
