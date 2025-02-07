package GardenPuzzleDomain.Interfaces;



/**
 * The ISearchable interface provides a method for searching within an object.
 * Implementing classes should provide the logic for searching based on a specified field and value.
 */
public interface ISearchable {
    public boolean search(String field, Object value);  //parametre alması gerekiyor
}
