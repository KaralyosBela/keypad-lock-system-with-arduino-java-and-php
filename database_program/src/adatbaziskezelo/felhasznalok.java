package adatbaziskezelo;

public class felhasznalok
{

    String kod;
    String telj_nev;
    String belepes;

    public felhasznalok(String telj_nev, String kod, String belepes)
    {
        this.telj_nev = telj_nev;
        this.kod = kod;
        this.belepes = belepes;
    }

    public String getTelj_nev()
    {
        return telj_nev;
    }

    public String getKod()
    {
        return kod;
    }

    public String getBelepes()
    {
        return belepes;
    }
    
    

}
