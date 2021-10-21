package wcs.cda;

import java.util.List;

public class DFusionSort {
    // Implémenter le fusion sort.
    // Il s'agit d'un algorithme de tri récursif qui fonctionne
    // de la façon suivante :
    //
    // 1. Si je reçois une liste de taille 1, elle est considérée
    //    comme triée
    // 2. Sinon je coupe la liste en deux, m'appelle récursivement
    //    et fusionne les deux listes triées en respectant l'ordre.
    //
    // Exemple : si je veux trier [2, 1]
    //
    // 1. Je la sépare en deux listes [1] [2]
    // 2. Je fusionne les deux
    //
    // La fusion respecte l'ordre:
    //
    // fusion([1, 3], [2, 4]) => [1, 2, 3, 4]
    //
    // Commencer par implémenter la méthode merge (fusion en anglais)
    public static void sort(List<Integer> testArray) {
    }

    public static List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        // TODO
        return null;
    }
}
