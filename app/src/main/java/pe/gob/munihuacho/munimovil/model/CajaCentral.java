package pe.gob.munihuacho.munimovil.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by peral on 29/03/2017.
 */

public class CajaCentral implements Parcelable{
    public String movimiento;
    public String caja;
    public String liq;
    public String nombre;
    public String observacion;
    public String concepto;
    public String importe;
    public String total;
    public String usuario;
    public String fecha;
    public String hora;

    public CajaCentral(){

    }
    protected CajaCentral(Parcel in) {
        movimiento = in.readString();
        caja = in.readString();
        liq = in.readString();
        nombre = in.readString();
        observacion = in.readString();
        concepto = in.readString();
        importe = in.readString();
        total = in.readString();
        usuario = in.readString();
        fecha = in.readString();
        hora = in.readString();
    }


    public static final Creator<CajaCentral> CREATOR = new Creator<CajaCentral>() {
        @Override
        public CajaCentral createFromParcel(Parcel in) {
            return new CajaCentral(in);
        }

        @Override
        public CajaCentral[] newArray(int size) {
            return new CajaCentral[size];
        }
    };

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public String getLiq() {
        return liq;
    }

    public void setLiq(String liq) {
        this.liq = liq;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movimiento);
        dest.writeString(caja);
        dest.writeString(liq);
        dest.writeString(nombre);
        dest.writeString(observacion);
        dest.writeString(concepto);
        dest.writeString(importe);
        dest.writeString(total);
        dest.writeString(usuario);
        dest.writeString(fecha);
        dest.writeString(hora);
    }
}
