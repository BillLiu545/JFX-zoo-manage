import StackQueueTree.*;
import javafx.collections.*;
import javafx.scene.control.Alert.*;
import java.util.*;
import javafx.scene.control.*;

public class Zoo extends BinarySearchTree<Animal>
{
    private class AnimalNameList extends ArrayList<String>
    {
        public AnimalNameList()
        {
            add("Elephant");
            add("Monkey");
            add("Chimpanzee");
            add("Gorilla");
            add("Lion");
            add("Tiger");
            add("Bear");
            add("Giraffe");
            add("Camel");
            add("Zebra");
            add("Hippopotamus");
            add("Rhinoceros");
            add("Panda");
            add("Kangaroo");
            add("Koala");
            add("Ostrich");
            add("Crocodile");
            add("Seal");
            add("Penguin");
            add("Dolphin");
        }
    }
    // Search for a specific animal
    private Animal searchAnimal(String name)
    {
        Animal a = new Animal(name);
        Iterator<Animal> iter = getPostorderIterator();
        while (iter.hasNext())
        {
            Animal other = iter.next();
            if (a.compareTo(other) == 0)
            {
                return other;
            }
        }
        return null;
    }
    // Add a new animal
    public Animal addAnimal(String name)
    {
        // List of names of popular zoo animals. If the name is not in the list, it will not be added
        AnimalNameList animalNames = new AnimalNameList();
        if (!animalNames.contains(name))
        {
            return null;
        }
        // There cannot be duplicates of the same animal
        Animal searched = searchAnimal(name);
        if (searched == null)
        {
            Animal added = new Animal(name);
            add(added);
            return added;
        }
        return searched;
    }
    // Remove an existing animal
    public Animal removeAnimal(String name)
    {
        Animal searched = searchAnimal(name);
        if (searched != null)
        {
            remove(searched);
        }
        return searched;
    }
    // Feed an animal
    public Animal feedAnimal(String name)
    {
        Animal fed = null;
        Animal searched = searchAnimal(name);
        if (searched != null)
        {
            fed = remove(searched);
            fed.feed();
            add(fed);
        }
        return fed;
    }
    // User can enter an animal name to search for a specific animal
    public String enterName()
    {
        String name;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Name");
        dialog.setHeaderText("Enter an animal name: ");
        dialog.setContentText("Name: ");
        Optional<String> optional = dialog.showAndWait();
        if (optional.isPresent())
        {
            name = optional.get();
        }
        else
        {
            return enterName();
        }
        return name;
    }
    // Convert to an observable list
    public ObservableList<Animal> toObservableList()
    {
        ObservableList observable = FXCollections.observableArrayList();
        Iterator<Animal> iter = getPostorderIterator();
        while (iter.hasNext())
        {
            Animal other = iter.next();
            observable.add(other);
        }
        return observable;
    }
    // List all animals (only for this class)
    public void listAllAnimals()
    {
        System.out.println("Animals in Zoo: ");
        Iterator<Animal> iter = getPostorderIterator();
        while (iter.hasNext())
        {
            Animal other = iter.next();
            System.out.println(other.getName());
        }
    }
    // Main method to test data structure operations
    public static void main(String[] args)
    {
        Zoo zoo = new Zoo();
        zoo.addAnimal("Elephant");
        zoo.addAnimal("Elephant");
        zoo.listAllAnimals();
        zoo.addAnimal("Chicken");
        zoo.listAllAnimals();
        zoo.removeAnimal("Elephant");
        zoo.listAllAnimals();
    }
}
