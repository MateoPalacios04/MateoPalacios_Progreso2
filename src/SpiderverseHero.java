public class SpiderverseHero {
    private int codigo;
    private String nombre;
    private String poderEspecial;
    private String universo;
    private int nivelExperiencia;

    public SpiderverseHero(int codigo, String nombre, String poderEspecial, String universo, int nivelExperiencia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.poderEspecial = poderEspecial;
        this.universo = universo;
        this.nivelExperiencia = nivelExperiencia;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getPoderEspecial() { return poderEspecial; }
    public String getUniverso() { return universo; }
    public int getNivelExperiencia() { return nivelExperiencia; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPoderEspecial(String poderEspecial) { this.poderEspecial = poderEspecial; }
    public void setUniverso(String universo) { this.universo = universo; }
    public void setNivelExperiencia(int nivelExperiencia) { this.nivelExperiencia = nivelExperiencia; }
}
