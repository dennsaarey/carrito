/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlcanciaClases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que simula el funcionamiento de una alcancia, calcula el total de
 * dinero ahorrado y lleva un registro de las denominaciones de las monedas
 * ahorradas
 *
 * @author Javier Monroy Salcedo
 */
public class Alcancia {

    private Propietario propietario;
    private float total;
    private String fechaDeCreacion;
    private int totalDiez;
    private int totalCinco;
    private int totalDos;
    private int totalUno;
    private int totalCincuentaCentavos;

    /**
     * Constructor para crear una nueva alcancia
     *
     * @param propietario
     * @param fechaDeCreacion
     */
    public Alcancia(Propietario propietario, String fechaDeCreacion) {
        this.propietario = propietario;
        this.total = 0;
        this.fechaDeCreacion = fechaDeCreacion;
        this.totalDiez = 0;
        this.totalCinco = 0;
        this.totalDos = 0;
        this.totalUno = 0;
        this.totalCincuentaCentavos = 0;
    }

    /**
     * Constructor vacio para cuando se lean los datos de un archivo
     */
    public Alcancia() {
        propietario = new Propietario("user", " ");
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(String fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public int getTotalDiez() {
        return totalDiez;
    }

    public void setTotalDiez(int totalDiez) {
        this.totalDiez = totalDiez;
    }

    public int getTotalCinco() {
        return totalCinco;
    }

    public void setTotalCinco(int totalCinco) {
        this.totalCinco = totalCinco;
    }

    public int getTotalDos() {
        return totalDos;
    }

    public void setTotalDos(int totalDos) {
        this.totalDos = totalDos;
    }

    public int getTotalUno() {
        return totalUno;
    }

    public void setTotalUno(int totalUno) {
        this.totalUno = totalUno;
    }

    public int getTotalCincuentaCentavos() {
        return totalCincuentaCentavos;
    }

    public void setTotalCincuentaCentavos(int totalCincuentaCentavos) {
        this.totalCincuentaCentavos = totalCincuentaCentavos;
    }

    /**
     * Calcula el total de dinero que se encuentra en la alcancia
     */
    public float calculaTotal() {
        float totalProv = 0;
        totalProv = totalProv + 10 * getTotalDiez();
        totalProv = totalProv + 5 * getTotalCinco();
        totalProv = totalProv + 2 * getTotalDos();
        totalProv = totalProv + getTotalUno();
        totalProv = (float) (totalProv + 0.5 * getTotalCincuentaCentavos());
        return totalProv;
    }

    /**
     * Escribe en un archivo los datos de la alcancia para poder leerlos en caso
     * de tener que cerrar el programa.
     */
    public void leerAlcancia() {
        File archivo = null;
        FileReader mFileReader = null;
        BufferedReader mBufferedReader = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("alcancia.txt");
            mFileReader = new FileReader(archivo);
            mBufferedReader = new BufferedReader(mFileReader);

            // DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            // Lectura del fichero
            String linea;
            String fecha;
            linea = mBufferedReader.readLine();
            propietario.setNombre(linea);
            linea = mBufferedReader.readLine();
            propietario.setApellido(linea);
            linea = mBufferedReader.readLine();
            setFechaDeCreacion(linea);
            linea = mBufferedReader.readLine();
            setTotalDiez(Integer.parseInt(linea));
            linea = mBufferedReader.readLine();
            setTotalCinco(Integer.parseInt(linea));
            linea = mBufferedReader.readLine();
            setTotalDos(Integer.parseInt(linea));
            linea = mBufferedReader.readLine();
            setTotalUno(Integer.parseInt(linea));
            linea = mBufferedReader.readLine();
            setTotalCincuentaCentavos(Integer.parseInt(linea));
            linea = mBufferedReader.readLine();
            setTotal(Float.parseFloat(linea));

            /*  while ((linea = mBufferedReader.readLine()) != null) {
                //System.out.println(linea);
                propietario.setNombre(linea);
                propietario.setApellido(linea);
                setFechaDeCreacion(formatter.parse(linea));
                setTotalDiez(Integer.parseInt(linea));
                setTotalCinco(Integer.parseInt(linea));
                setTotalDos(Integer.parseInt(linea));
                setTotalUno(Integer.parseInt(linea));
                setTotal(Float.parseFloat(linea));

            }*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try {
                if (null != mFileReader) {
                    mFileReader.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void salvarAlcancia() {
        FileWriter fichero = null;
        PrintWriter mPrintWriter = null;

// String fecha = fechaDeCreacion.getDay() + "/" + fechaDeCreacion.getMonth() + "/" + fechaDeCreacion.getYear();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        // String today = formatter.format(fechaDeCreacion);
        try {
            fichero = new FileWriter("alcancia.txt");
            mPrintWriter = new PrintWriter(fichero);

            mPrintWriter.println(propietario.getNombre());
            mPrintWriter.println(propietario.getApellido());
            mPrintWriter.println(fechaDeCreacion);
            mPrintWriter.println(getTotalDiez());
            mPrintWriter.println(getTotalCinco());
            mPrintWriter.println(getTotalDos());
            mPrintWriter.println(getTotalUno());
            mPrintWriter.println(getTotalCincuentaCentavos());
            mPrintWriter.println(getTotal());

            /*   for (int i = 0; i < 10; i++) {
                mPrintWriter.println("Linea " + i);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * Elimina el archivo donde se guardan datos de la alcancia
     */
    public void borrarAlcancia() {
        try {

            File archivo = new File("alcancia.txt");

            boolean estatus = archivo.delete();;

            if (!estatus) {

                System.out.println("Error no se ha podido eliminar el  archivo");

            } else {

                System.out.println("Se ha eliminado el archivo exitosamente");

            }

        } catch (Exception e) {

            System.out.println(e);

        }
    }

}
