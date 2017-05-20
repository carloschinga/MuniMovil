package pe.gob.munihuacho.munimovil.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by peral on 30/03/2017.
 */

public class Matrimonio implements Parcelable{
    String id;
    String imagen;
    String libro;
    String partidanro;
    String añore;
    String folio;
    String paterno;
    String materno;
    String nombres;
    String apepa;
    String dni;
    String paternodon;
    String maternodon;
    String nombresdon;
    String apepam;
    String dnidon;
    String diainscrip;
    String mesinscrip;
    String añoinscrip;
    String tipo_ins;
    String sexo;
    String fechamat;
    String usuario;
    String fecha;
    String situacion;
    String situ;

    protected  Matrimonio(Parcel in) {
        id = in.readString();
        imagen = in.readString();
        libro = in.readString();
        partidanro = in.readString();
        añore = in.readString();
        folio = in.readString();
        paterno = in.readString();
        materno = in.readString();
        nombres = in.readString();
        apepa = in.readString();
        dni = in.readString();
        paternodon = in.readString();
        maternodon = in.readString();
        nombresdon = in.readString();
        apepam = in.readString();
        dnidon = in.readString();
        diainscrip = in.readString();
        mesinscrip = in.readString();
        añoinscrip = in.readString();
        tipo_ins = in.readString();
        sexo = in.readString();
        fechamat = in.readString();
        usuario = in.readString();
        fecha = in.readString();
        situacion = in.readString();
        situ = in.readString();
    }
public Matrimonio(){}
    public static final Creator<Matrimonio> CREATOR = new Creator<Matrimonio>() {
        @Override
        public Matrimonio createFromParcel(Parcel in) {
            return new Matrimonio(in);
        }

        @Override
        public Matrimonio[] newArray(int size) {
            return new Matrimonio[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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

    public String getApepa() {
        return apepa;
    }

    public void setApepa(String apepa) {
        this.apepa = apepa;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPaternodon() {
        return paternodon;
    }

    public void setPaternodon(String paternodon) {
        this.paternodon = paternodon;
    }

    public String getMaternodon() {
        return maternodon;
    }

    public void setMaternodon(String maternodon) {
        this.maternodon = maternodon;
    }

    public String getNombresdon() {
        return nombresdon;
    }

    public void setNombresdon(String nombresdon) {
        this.nombresdon = nombresdon;
    }

    public String getApepam() {
        return apepam;
    }

    public void setApepam(String apepam) {
        this.apepam = apepam;
    }

    public String getDnidon() {
        return dnidon;
    }

    public void setDnidon(String dnidon) {
        this.dnidon = dnidon;
    }

    public String getDiainscrip() {
        return diainscrip;
    }

    public void setDiainscrip(String diainscrip) {
        this.diainscrip = diainscrip;
    }

    public String getMesinscrip() {
        return mesinscrip;
    }

    public void setMesinscrip(String mesinscrip) {
        this.mesinscrip = mesinscrip;
    }

    public String getAñoinscrip() {
        return añoinscrip;
    }

    public void setAñoinscrip(String añoinscrip) {
        this.añoinscrip = añoinscrip;
    }

    public String getTipo_ins() {
        return tipo_ins;
    }

    public void setTipo_ins(String tipo_ins) {
        this.tipo_ins = tipo_ins;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechamat() {
        return fechamat;
    }

    public void setFechamat(String fechamat) {
        this.fechamat = fechamat;
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

    public String getSitu() {
        return situ;
    }

    public void setSitu(String situ) {
        this.situ = situ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(imagen);
        dest.writeString(libro);
        dest.writeString(partidanro);
        dest.writeString(añore);
        dest.writeString(folio);
        dest.writeString(paterno);
        dest.writeString(materno);
        dest.writeString(nombres);
        dest.writeString(apepa);
        dest.writeString(dni);
        dest.writeString(paternodon);
        dest.writeString(maternodon);
        dest.writeString(nombresdon);
        dest.writeString(apepam);
        dest.writeString(dnidon);
        dest.writeString(diainscrip);
        dest.writeString(mesinscrip);
        dest.writeString(añoinscrip);
        dest.writeString(tipo_ins);
        dest.writeString(sexo);
        dest.writeString(fechamat);
        dest.writeString(usuario);
        dest.writeString(fecha);
        dest.writeString(situacion);
        dest.writeString(situ);
    }
}
