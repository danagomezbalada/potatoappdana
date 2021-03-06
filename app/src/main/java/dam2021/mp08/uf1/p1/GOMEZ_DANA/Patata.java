package dam2021.mp08.uf1.p1.GOMEZ_DANA;

public class Patata {
    private String id;
    private String tipus;
    private String descripcio;
    private String sembrar;
    private String recollida;
    private String preu;
    private String audio;
    private String imatge;

    public Patata(String id, String tipus, String descripcio, String sembrar, String recollida, String preu){
        this.id = id;
        this.tipus = tipus;
        this.descripcio = descripcio;
        this.sembrar = sembrar;
        this.recollida = recollida;
        this.preu = preu;

    }
    public Patata(String id, String tipus, String descripcio, String sembrar, String recollida, String preu, String audio, String imatge){
        this.id = id;
        this.tipus = tipus;
        this.descripcio = descripcio;
        this.sembrar = sembrar;
        this.recollida = recollida;
        this.preu = preu;
        this.audio = audio;
        this.imatge = imatge;

    }

    public String getId(){return this.id;}
    public void setId(String id){this.id = id;}
    public String getTipus(){return this.tipus;}
    public void setTipus(String tipus){this.tipus=tipus;}
    public String getDescripcio(){return this.descripcio;}
    public void setDescripcio(String descripcio){this.descripcio=descripcio;}
    public String getSembrar(){return this.sembrar;}
    public void setSembrar(String sembrar){this.sembrar = sembrar;}
    public String getRecollida(){return this.recollida;}
    public void setRecollida(String recollida){this.recollida=recollida;}
    public String getPreu(){return this.preu;}
    public void setPreu(String preu){this.preu = preu;}
    public String getAudio(){return this.audio;}
    public void setAudio(String audio){this.audio = audio;}
    public String getImatge(){return this.imatge;}
    public void setImatge(String imatge){this.imatge = imatge;}

    @Override
    public String toString(){
        return "Patata-> ID: "+this.id +". Tipus: "+this.tipus+". Descripcio: "+this.descripcio+
                ". Epoca de sembrar: "+this.sembrar +". Epoca de recollida: "+this.recollida+
                ". Preu: "+this.preu;
    }

}
