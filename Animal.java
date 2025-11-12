
public class Animal implements Comparable<Animal>
{
    private String name;
    private boolean fed;
    
    // Initialize the animal with the name, and it is unfed
    public Animal(String name)
    {
        this.name = name;
        this.fed = false;
    }
    
    // retrieve name for animal
    public String getName()
    {
        return name;
    }
    
    // Check if animal is fed
    public boolean isFed()
    {
        return fed;
    }
    
    // Feed the animal - set the fed boolean to true
    public void feed()
    {
        fed = true;
    }
    
    // Compare 2 animals' names (required for search in the Zoo binary search tree)
    public int compareTo(Animal other)
    {
        if (this.name.equals(other.name))
        {
            return 0;
        }
        return 1;
    }
    
}
