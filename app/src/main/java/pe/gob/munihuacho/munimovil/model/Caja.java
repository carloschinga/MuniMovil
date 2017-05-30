package pe.gob.munihuacho.munimovil.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by peral on 29/03/2017.
 */

public class Caja implements Parcelable {
    String id;
    public String libro;
    public String partidanro;
    public String añore;
    public String folio;
    public String paterno;
    public String materno;
    public String nombres;
    public String tipo;
    public String importe;
    public String cantidad;
    public String total;
    public String usuario;
    public String fecha;
    public String situacion;
    public String obs;
    public String solicitante;
    public String formato;
    public String entidad;

    public Caja(){

    }


    protected Caja(Parcel in) {
        id = in.readString();
        libro = in.readString();
        partidanro = in.readString();
        añore = in.readString();
        folio = in.readString();
        paterno = in.readString();
        materno = in.readString();
        nombres = in.readString();
        tipo = in.readString();
        importe = in.readString();
        cantidad = in.readString();
        total = in.readString();
        usuario = in.readString();
        fecha = in.readString();
        situacion = in.readString();
        obs = in.readString();
        solicitante = in.readString();
        formato = in.readString();
        entidad = in.readString();
    }

    public static final Creator<Caja> CREATOR = new Creator<Caja>() {
        @Override
        public Caja createFromParcel(Parcel in) {
            return new Caja(in);
        }

        @Override
        public Caja[] newArray(int size) {
            return new Caja[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getPartidanro() {
        return partidanro;
    }

    public void setPartidanro(String partidanro) {
        this.partidanro = partidanro;
    }

    public String getAñore() {
        return añore;
    }

    public void setAñore(String añore) {
        this.añore = añore;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
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

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(libro);
        dest.writeString(partidanro);
        dest.writeString(añore);
        dest.writeString(folio);
        dest.writeString(paterno);
        dest.writeString(materno);
        dest.writeString(nombres);
        dest.writeString(tipo);
        dest.writeString(importe);
        dest.writeString(cantidad);
        dest.writeString(total);
        dest.writeString(usuario);
        dest.writeString(fecha);
        dest.writeString(situacion);
        dest.writeString(obs);
        dest.writeString(solicitante);
        dest.writeString(formato);
        dest.writeString(entidad);
    }
}
