package pe.gob.munihuacho.munimovil.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by peral on 3/04/2017.
 */

public class Papeleta implements Parcelable{
    private String fechaInfraccion;
    private Date fech_noti;
    private String nume_pape;
    private String codi_inte;
    private double tota_pago;
    private String plac_dttr;
    private String desc_esta;
    private String dni;
    SimpleDateFormat sdf1 = new SimpleDateFormat();
/*
    public Papeleta(Date fech_noti, String nume_pape, String codi_inte, double tota_pago, String plac_dttr, String desc_esta, String dni) {
        this.fech_noti = fech_noti;
        this.nume_pape = nume_pape;
        this.codi_inte = codi_inte;
        this.tota_pago = tota_pago;
        this.plac_dttr = plac_dttr;
        this.desc_esta = desc_esta;
        this.dni = dni;

        this.fechaInfraccion = sdf1.format(fech_noti);
    }
    */
    public Papeleta(Parcel in) {
        fechaInfraccion=in.readString();
        nume_pape = in.readString();
        codi_inte = in.readString();
        tota_pago = in.readDouble();
        plac_dttr = in.readString();
        desc_esta = in.readString();
        dni = in.readString();
    }

    public Papeleta() {
        sdf1.applyPattern("dd/MM/yyyy");
    }

    public static final Creator<Papeleta> CREATOR = new Creator<Papeleta>() {
        @Override
        public Papeleta createFromParcel(Parcel in) {
            return new Papeleta(in);
        }

        @Override
        public Papeleta[] newArray(int size) {
            return new Papeleta[size];
        }
    };

    public String getFechaInfraccion() {
        return fechaInfraccion;
    }

    public void setFechaInfraccion(String fechaInfraccion) {
        this.fechaInfraccion = fechaInfraccion;
    }

    public Date getFech_noti() {
        return fech_noti;
    }

    public void setFech_noti(Date fech_noti) {
        this.fech_noti = fech_noti;

        this.fechaInfraccion = sdf1.format(fech_noti);

    }

    public String getNume_pape() {
        return nume_pape;
    }

    public void setNume_pape(String nume_pape) {
        this.nume_pape = nume_pape;
    }

    public String getCodi_inte() {
        return codi_inte;
    }

    public void setCodi_inte(String codi_inte) {
        this.codi_inte = codi_inte;
    }

    public double getTota_pago() {
        return tota_pago;
    }

    public void setTota_pago(double tota_pago) {
        this.tota_pago = tota_pago;
    }

    public String getPlac_dttr() {
        return plac_dttr;
    }

    public void setPlac_dttr(String plac_dttr) {
        this.plac_dttr = plac_dttr;
    }

    public String getDesc_esta() {
        return desc_esta;
    }

    public void setDesc_esta(String desc_esta) {
        this.desc_esta = desc_esta;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fechaInfraccion);
        dest.writeString(nume_pape);
        dest.writeString(codi_inte);
        dest.writeDouble(tota_pago);
        dest.writeString(plac_dttr);
        dest.writeString(desc_esta);
        dest.writeString(dni);
    }
}
