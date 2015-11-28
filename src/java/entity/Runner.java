
package entity;

import javax.persistence.Persistence;

/**
 *
 * @author CosticaTeodor
 */
public class Runner {
    public static void main(String[] args) {
        Persistence.createEntityManagerFactory("PU-Local");
    }
}
