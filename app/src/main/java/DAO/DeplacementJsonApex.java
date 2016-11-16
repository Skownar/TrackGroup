package DAO;

/**
 * Created by lafer on 16-11-16.
 *
 * Apex retourne des items supplémentaires en json, il faut donc les traiter
 */


public class DeplacementJsonApex {
    String $ref;
    public DeplacementJsonApex() {

    }
    public DeplacementJsonApex(String $ref) {
        this.$ref = $ref;
    }

    public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        this.$ref = $ref;
    }

    @Override
    public String toString() {
        return $ref;
    }
}
