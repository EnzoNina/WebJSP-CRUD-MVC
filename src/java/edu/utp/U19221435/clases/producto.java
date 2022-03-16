package edu.utp.U19221435.clases;
import java.util.Date;
public class producto {
//Atributos
    private String codigoArticulo,seccion,nombreArticulo,paisOrigen,importado;
    private double precio;
    private Date fecha;    
    //Constructores
    public producto(String codigoArticulo, String seccion, String nombreArticulo, String paisOrigen, double precio, Date fecha, String importado) {
            this.codigoArticulo = codigoArticulo;
            this.seccion = seccion;
            this.nombreArticulo = nombreArticulo;
            this.paisOrigen = paisOrigen;
            this.precio = precio;
            this.fecha = fecha;
            this.importado = importado;
        }
    public producto(String seccion, String nombreArticulo, String paisOrigen, double precio, Date fecha, String importado) {
        this.seccion = seccion;
        this.nombreArticulo = nombreArticulo;
        this.paisOrigen = paisOrigen;
        this.precio = precio;
        this.fecha = fecha;
        this.importado = importado;
    }
    //Getter and Setter

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getImportado(){
        return importado;
    }

    public void setImportado(String importado) {
        this.importado = importado;
    }
    //Metodos
    @Override
    public String toString(){        
        return "\nProductos: " +"Cod:"+codigoArticulo+"Nom:"+nombreArticulo+"Pais"+paisOrigen+"Secc:"+seccion+"fecha:"+fecha+"importado:"+importado+"precio:"+precio;
    } 

}
