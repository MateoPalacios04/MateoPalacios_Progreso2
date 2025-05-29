import java.util.LinkedList;

public class SpiderverseHeroManager {
    private LinkedList<SpiderverseHero> heroes = new LinkedList<>();

    public boolean agregarHeroe(SpiderverseHero heroe) {
        for (SpiderverseHero h : heroes) {
            if (h.getCodigo() == heroe.getCodigo()) {
                return false; // Código duplicado
            }
        }
        heroes.addFirst(heroe);
        return true;
    }

    public LinkedList<SpiderverseHero> getHeroes() {
        return heroes;
    }

    public SpiderverseHero buscarPorCodigo(int codigo) {
        for (SpiderverseHero h : heroes) {
            if (h.getCodigo() == codigo) {
                return h;
            }
        }
        return null;
    }

    public LinkedList<SpiderverseHero> filtrarYOrdenar(String poderExcluido) {
        LinkedList<SpiderverseHero> nuevaLista = new LinkedList<>();
        for (SpiderverseHero h : heroes) {
            if (!h.getPoderEspecial().equals(poderExcluido)) {
                nuevaLista.add(h);
            }
        }

        // Ordenamiento burbuja por nivel de experiencia
        for (int i = 0; i < nuevaLista.size(); i++) {
            for (int j = 0; j < nuevaLista.size() - i - 1; j++) {
                if (nuevaLista.get(j).getNivelExperiencia() > nuevaLista.get(j + 1).getNivelExperiencia()) {
                    SpiderverseHero temp = nuevaLista.get(j);
                    nuevaLista.set(j, nuevaLista.get(j + 1));
                    nuevaLista.set(j + 1, temp);
                }
            }
        }
        return nuevaLista;
    }
}
