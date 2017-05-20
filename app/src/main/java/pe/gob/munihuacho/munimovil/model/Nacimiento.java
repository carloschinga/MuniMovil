package pe.gob.munihuacho.munimovil.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by peral on 30/03/2017.
 */

public class Nacimiento  implements Parcelable {
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
    String fechanac;
    String sexo;
    String diainscrip;
    String mesinscrip;
    String añoinscrip;
    String tip_naci;
    String padre;
    String madre;
    String usuario;
    String fecha;
    String situacion;
    String situ;
    String numres;
    String naci_reconocimiento;
    String naci_nueva_acta;
    String fecha_inscripcion;

    public Nacimiento(Parcel in) {
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
        fechanac = in.readString();
        sexo = in.readString();
        diainscrip = in.readString();
        mesinscrip = in.readString();
        añoinscrip = in.readString();
        tip_naci = in.readString();
        padre = in.readString();
        madre = in.readString();
        usuario = in.readString();
        fecha = in.readString();
        situacion = in.readString();
        situ = in.readString();
        numres = in.readString();
        naci_reconocimiento = in.readString();
        naci_nueva_acta = in.readString();
        fecha_inscripcion = in.readString();
    }
    public Nacimiento(){

    }

    public static final Creator<Nacimiento> CREATOR = new Creator<Nacimiento>() {
        @Override
        public Nacimiento createFromParcel(Parcel in) {
            return new Nacimiento(in);
        }

        @Override
        public Nacimiento[] newArray(int size) {
            return new Nacimiento[size];
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

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public String getTip_naci() {
        return tip_naci;
    }

    public void setTip_naci(String tip_naci) {
        this.tip_naci = tip_naci;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public String getMadre() {
        return madre;
    }

    public void setMadre(String madre) {
        this.madre = madre;
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

    public String getNumres() {
        return numres;
    }

    public void setNumres(String numres) {
        this.numres = numres;
    }

    public String getNaci_reconocimiento() {
        return naci_reconocimiento;
    }

    public void setNaci_reconocimiento(String naci_reconocimiento) {
        this.naci_reconocimiento = naci_reconocimiento;
    }

    public String getNaci_nueva_acta() {
        return naci_nueva_acta;
    }

    public void setNaci_nueva_acta(String naci_nueva_acta) {
        this.naci_nueva_acta = naci_nueva_acta;
    }

    public String getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(String fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
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
        dest.writeString(fechanac);
        dest.writeString(sexo);
        dest.writeString(diainscrip);
        dest.writeString(mesinscrip);
        dest.writeString(añoinscrip);
        dest.writeString(tip_naci);
        dest.writeString(padre);
        dest.writeString(madre);
        dest.writeString(usuario);
        dest.writeString(fecha);
        dest.writeString(situacion);
        dest.writeString(situ);
        dest.writeString(numres);
        dest.writeString(naci_reconocimiento);
        dest.writeString(naci_nueva_acta);
        dest.writeString(fecha_inscripcion);
    }
}
