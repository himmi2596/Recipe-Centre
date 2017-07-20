public class Recipe 
{
    String title;
    String imageUrl;
    String rid;

    // used in view favourites
    public Recipe(String title, String imageUrl, String rid)
    {
        this.title = title;
        this.imageUrl = imageUrl;
        this.rid = rid;
    }
    // used in view offline
    public Recipe(String title,String rid)
    {
        this.title=title;
        this.rid=rid;
    }
    
}
