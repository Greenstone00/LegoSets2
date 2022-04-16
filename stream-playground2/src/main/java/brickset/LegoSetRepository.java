package brickset;

import repository.Repository;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }



        public static void main(String[] args) {

            var f1 = new LegoSetRepository();
            System.out.println("Van olyan lego szet melynek neve tartalmaz 'x'-et: " + f1.nameContainsX() + "\n");

            var f2 = new LegoSetRepository();
            System.out.println("Az összes különböző 'theme'-k száma a brickset.jsno fileból: " + f1.numberOfAllKindsOfThemes() + "\n");

            var f3 = new LegoSetRepository();
            System.out.println("Az összes szetben megtalálható legó darabok száma: " + f3.numberOfLegoPieces() + " db" + "\n");

            var f4 = new LegoSetRepository();
            System.out.println("Lego szettek téma alapján rendszerezve: " + f4.numberOfSetsGroupedByTheme() + "\n");

            var f5 = new LegoSetRepository();
            System.out.println("A Creator típúsú szettek nevei: " + f5.numberOfTagsPerNames() + "\n");
        }

    /**
     * Első feladat:
     * Weather one of the sets's name  contains the letter 'x'.
     * @return true / false
     */

        public boolean nameContainsX() {
            return getAll().stream().anyMatch(sets -> sets.getName().contains("x"));
        }

    /**
     * Második feladat:
     * Gives us the number of distinct tags of all the lego sets used.
     * @return the number of the distinct tags
     */

        public long numberOfAllKindsOfThemes() {
            return getAll().stream().flatMap(themes -> themes.getTheme().lines()).distinct().count();
        }
    /**
     * Harmadik feladat:
     * Gives back the number of all lego pieces of all the sets
     * @return The number of pieces
     */

        public long numberOfLegoPieces() {
            return getAll().stream().map(pieces -> pieces.getPieces()).filter(Objects::nonNull).reduce(0, Integer::sum);
        }
    /**
     * Negyedik feladat:
     * Vissza adja témánként a lego szettek számát.
     * @return number of lego sets grouped by themes
     */

    public Map<String, Long> numberOfSetsGroupedByTheme() {
        Map<String, Long> numoflegos =
                getAll().stream().collect(groupingBy(LegoSet::getTheme, counting()));
        return numoflegos;
    }

    /**
     * Ötödik feladat:
     * Vissza adja a "Creator" theme-jű lego szettek nevét.
     * @return lego sets with theme "Creator"
     */

    public Map<String, Long> numberOfTagsPerNames(){
        Map<String, Long> tagspernames =
                getAll().stream().filter(theme -> theme.getTheme().equals("Creator")).collect(groupingBy(LegoSet::getName, counting()));
        return tagspernames;
    }

}


