package pe.gob.munihuacho.munimovil.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by alexisholyoak on 6/06/2017.
 */

public class Detalle implements Serializable{
    private String id;
    private String tributo;
    private String trides;
    private String aini;
    private String peini;
    private String periodo;
    private Double insoluto;
    private Double sobretasas;
    private Double pagos;
    private Double saldo;
    private Integer secid;

    public Detalle(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTributo() {
        return tributo;
    }

    public void setTributo(String tributo) {
        this.tributo = tributo;
    }

    public String getTrides() {
        return trides;
    }

    public void setTrides(String trides) {
        this.trides = trides;
    }

    public String getAini() {
        return aini;
    }

    public void setAini(String aini) {
        this.aini = aini;
    }

    public String getPeini() {
        return peini;
    }

    public void setPeini(String peini) {
        this.peini = peini;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Double getInsoluto() {
        return insoluto;
    }

    public void setInsoluto(Double insoluto) {
        this.insoluto = insoluto;
    }

    public Double getSobretasas() {
        return sobretasas;
    }

    public void setSobretasas(Double sobretasas) {
        this.sobretasas = sobretasas;
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

}
