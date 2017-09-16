/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klassen;

import java.util.Date;

/**
 *
 * @author irish
 */
class FahrzeugGrundausstattung {

    private Integer achsen = 0;
    private Integer raeder = 0;
    private Integer aktuelleKm = 0;
    private Integer letztesServiceKM = 0;
    private Date letztesServiceDatum;
    private Date naechstesServiceDatum;
    private Double preis = 0.0;
    private String kennzeichen;

    private Integer nutzlast = 0;
    private Integer Sitzplaetze = 0;
    private Integer km1 = 0;
    private Integer km2 = 0;
    private Integer inspInterv = 0;
    private Integer inspKm = 0;
    private Date inspDat;
    private Date mietbeginn;
    private Date vorrRueckgabe;
    private Integer kmRueckgabe;
    private Date update;
    private boolean geloescht;
    private String bauart = "";
    private String marke = "";
    private double mietePTag;
    private Boolean zustand;

    /**
     * @return the achsen
     */
    public int getAchsen() {
        return achsen;
    }

    /**
     * @param achsen the achsen to set
     */
    public void setAchsen(int achsen) {
        this.achsen = achsen;
    }

    /**
     * @return the raeder
     */
    public int getRaeder() {
        return raeder;
    }

    /**
     * @param raeder the raeder to set
     */
    public void setRaeder(int raeder) {
        this.raeder = raeder;
    }

    /**
     * @return the aktuelleKm
     */
    public int getAktuelleKm() {
        return aktuelleKm;
    }

    /**
     * @param aktuelleKm the aktuelleKm to set
     */
    public void setAktuelleKm(int aktuelleKm) {
        this.aktuelleKm = aktuelleKm;
    }

    /**
     * @return the letztesServiceKM
     */
    public Integer getLetztesServiceKM() {
        return letztesServiceKM;
    }

    /**
     * @param letztesServiceKM the letztesServiceKM to set
     */
    public void setLetztesServiceKM(Integer letztesServiceKM) {
        this.letztesServiceKM = letztesServiceKM;
    }

    /**
     * @return the letztesServiceDatum
     */
    public Date getLetztesServiceDatum() {
        return letztesServiceDatum;
    }

    /**
     * @param letztesServiceDatum the letztesServiceDatum to set
     */
    public void setLetztesServiceDatum(Date letztesServiceDatum) {
        this.letztesServiceDatum = letztesServiceDatum;
    }

    /**
     * @return the naechstesServiceDatum
     */
    public Date getNaechstesServiceDatum() {
        return naechstesServiceDatum;
    }

    /**
     * @param naechstesServiceDatum the naechstesServiceDatum to set
     */
    public void setNaechstesServiceDatum(Date naechstesServiceDatum) {
        this.naechstesServiceDatum = naechstesServiceDatum;
    }

    /**
     * @return the preis
     */
    public Double getPreis() {
        return preis;
    }

    /**
     * @param preis the preis to set
     */
    public void setPreis(Double preis) {
        this.preis = preis;
    }

    /**
     * @return the kennzeichen
     */
    public String getKennzeichen() {
        return kennzeichen;
    }

    /**
     * @param kennzeichen the kennzeichen to set
     */
    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    /**
     * @return the nutzlast
     */
    public Integer getNutzlast() {
        return nutzlast;
    }

    /**
     * @param nutzlast the nutzlast to set
     */
    public void setNutzlast(Integer nutzlast) {
        this.nutzlast = nutzlast;
    }

    /**
     * @return the Sitzplaetze
     */
    public Integer getSitzplaetze() {
        return Sitzplaetze;
    }

    /**
     * @param Sitzplaetze the Sitzplaetze to set
     */
    public void setSitzplaetze(Integer Sitzplaetze) {
        this.Sitzplaetze = Sitzplaetze;
    }

    /**
     * @return the km1
     */
    public Integer getKm1() {
        return km1;
    }

    /**
     * @param km1 the km1 to set
     */
    public void setKm1(Integer km1) {
        this.km1 = km1;
    }

    /**
     * @return the km2
     */
    public int getKm2() {
        return km2;
    }

    /**
     * @param km2 the km2 to set
     */
    public void setKm2(int km2) {
        this.km2 = km2;
    }

    /**
     * @return the inspInterv
     */
    public Integer getInspInterv() {
        return inspInterv;
    }

    /**
     * @param inspInterv the inspInterv to set
     */
    public void setInspInterv(Integer inspInterv) {
        this.inspInterv = inspInterv;
    }

    /**
     * @return the inspKm
     */
    public Integer getInspKm() {
        return inspKm;
    }

    /**
     * @param inspKm the inspKm to set
     */
    public void setInspKm(Integer inspKm) {
        if (inspKm != null)
        this.inspKm = inspKm;
        else 
            this.inspKm = 0;
    }

    /**
     * @return the inspDat
     */
    public Date getInspDat() {
        return inspDat;
    }

    /**
     * @param inspDat the inspDat to set
     */
    public void setInspDat(Date inspDat) {
        this.inspDat = inspDat;
    }

    /**
     * @return the mietbeginn
     */
    public Date getMietbeginn() {
        return mietbeginn;
    }

    /**
     * @param mietbeginn the mietbeginn to set
     */
    public void setMietbeginn(Date mietbeginn) {
        this.mietbeginn = mietbeginn;
    }

    /**
     * @return the vorrRueckgabe
     */
    public Date getVorrRueckgabe() {
        return vorrRueckgabe;
    }

    /**
     * @param vorrRueckgabe the vorrRueckgabe to set
     */
    public void setVorrRueckgabe(Date vorrRueckgabe) {
        this.vorrRueckgabe = vorrRueckgabe;
    }

    /**
     * @return the update
     */
    public Date getUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(Date update) {
        this.update = update;
    }

    /**
     * @return the geloescht
     */
    public boolean isGeloescht() {
        return geloescht;
    }

    /**
     * @param geloescht the geloescht to set
     */
    public void setGeloescht(boolean geloescht) {
        this.geloescht = geloescht;
    }

    /**
     * @return the bauart
     */
    public String getBauart() {
        return bauart;
    }

    /**
     * @param bauart the bauart to set
     */
    public void setBauart(String bauart) {
        this.bauart = bauart;
    }

    /**
     * @return the marke
     */
    public String getMarke() {
        return marke;
    }

    /**
     * @param marke the marke to set
     */
    public void setMarke(String marke) {
        this.marke = marke;
    }

    /**
     * @return the mietePTag
     */
    public double getMietePTag() {
        return mietePTag;
    }

    /**
     * @param mietePTag the mietePTag to set
     */
    public void setMietePTag(double mietePTag) {
        this.mietePTag = mietePTag;
    }

    /**
     * @return the zustand
     */
    public Boolean getZustand() {
        return zustand;
    }

    /**
     * @param zustand the zustand to set
     */
    public void setZustand(Boolean zustand) {
        this.zustand = zustand;
    }

    /**
     * @return the kmRueckgabe
     */
    public Integer getKmRueckgabe() {
        return kmRueckgabe;
    }

    /**
     * @param kmRueckgabe the kmRueckgabe to set
     */
    public void setKmRueckgabe(Integer kmRueckgabe) {
        this.kmRueckgabe = kmRueckgabe;
    }

}
