import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MovieScheduler {
    private HashMap<String, Slot> schedule = new HashMap<>();


    public void addMovie(String title, Slot slot){
        if (title ==null|| title.trim().isEmpty()){
            throw new IllegalArgumentException("Name can not be null");
        }
        for(Map.Entry<String, Slot> entry : schedule.entrySet()){
            if (entry.getKey().equals(title) && entry.getValue().equals(slot)){
                throw new IllegalArgumentException("Titles can not be the same");
            }
        }

        if (slot.hasTimeConflict(schedule.get(title))){
            throw new IllegalArgumentException("Slot need to be renseigned");
        }
        schedule.put(title,slot);
    }

    public void removeMovie(String title){
        schedule.remove(title);
    }

    public Slot getMovieSlot(String title) {
        if (title == null || title.isEmpty() ){
            throw new IllegalArgumentException("Wrong");
        }
        return  schedule.get(title);
    }

    public void updateMovieSlot(String title, Slot newSlot){
        if (title == null || title.isEmpty() ){
            throw new IllegalArgumentException("Wrong");
        }
        schedule.replace(title, newSlot);
    }

    public void display(){
        for(Map.Entry<String, Slot> entry : schedule.entrySet()){
            System.out.println("Films:"+entry.getKey());
            entry.getValue().display();
            System.out.println("________");
        }
    }

    public void showStats(){
        int nbFilms = schedule.size();
        int totalDuration=0;


        for(Map.Entry<String, Slot> entry : schedule.entrySet()){
            String title = entry.getKey();
            Slot slot = entry.getValue();
            System.out.println(title+slot.getRoom());
            totalDuration+= entry.getValue().getDuration();
        }

        System.out.println(nbFilms);
        System.out.println(totalDuration);
    }


}
