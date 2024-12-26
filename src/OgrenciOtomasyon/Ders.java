package OgrenciOtomasyon;

public class Ders {
    private String dersAdi;
    private String dersKodu;
    private OgretimUyesi ogretimUyesi;
    private Double not; // Not bilgisi, atanmadıysa null olur.

    public Ders(String dersAdi, String dersKodu, OgretimUyesi ogretimUyesi) {
        this.dersAdi = dersAdi;
        this.dersKodu = dersKodu;
        this.ogretimUyesi = ogretimUyesi;
        this.not = null;
    }

    // Getters and Setters
    public String getDersAdi() {
        return dersAdi;
    }

    public void setDersAdi(String dersAdi) {
        this.dersAdi = dersAdi;
    }

    public String getDersKodu() {
        return dersKodu;
    }

    public void setDersKodu(String dersKodu) {
        this.dersKodu = dersKodu;
    }

    public OgretimUyesi getOgretimUyesi() {
        return ogretimUyesi;
    }

    public void setOgretimUyesi(OgretimUyesi ogretimUyesi) {
        this.ogretimUyesi = ogretimUyesi;
    }

    public Double getNot() {
        return not;
    }

    public void setNot(Double not) {
        this.not = not;
    }

    @Override
    public String toString() {
        String notBilgisi = (not != null) ? String.valueOf(not) : "Henüz not verilmemiş";
        return "Ders Adı: " + dersAdi + 
               ", Ders Kodu: " + dersKodu + 
               ", Öğretim Üyesi: " + ogretimUyesi.getIsim() + " " + ogretimUyesi.getSoyisim() +
               ", Not: " + notBilgisi;
    }
}