package pe.gob.munihuacho.munimovil.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alexisholyoak on 31/05/2017.
 */

public class Resumen implements Parcelable{
    private String contrib;
    private String tributo;
    private String descripcion;
    private Double deuda;
    private Double pagos;
    private Double saldo;
    private Integer secid;

    protected Resumen(Parcel in) {
        contrib = in.readString();
        tributo = in.readString();
        descripcion = in.readString();
    }
    public Resumen(){}

    public static final Creator<Resumen> CREATOR = new Creator<Resumen>() {
        @Override
        public Resumen createFromParcel(Parcel in) {
            return new Resumen(in);
        }

        @Override
        public Resumen[] newArray(int size) {
            return new Resumen[size];
        }
    };

    public String getContrib() {
        return contrib;
    }

    public void setContrib(String contrib) {
        this.contrib = contrib;
    }

    public String getTributo() {
        return tributo;
    }

    public void setTributo(String tributo) {
        this.tributo = tributo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getDeuda() {
        return deuda;
    }

    public void setDeuda(Double deuda) {
        this.deuda = deuda;
    }

    public Double getPagos() {
        return pagos;
    }

    public void setPagos(Double pagos) {
        this.pagos = pagos;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Integer getSecid() {
        return secid;
    }

    public void setSecid(Integer secid) {
        this.secid = secid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contrib);
        dest.writeString(tributo);
        dest.writeString(descripcion);
    }
}
