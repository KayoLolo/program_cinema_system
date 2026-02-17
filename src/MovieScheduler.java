import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MovieScheduler {
    private HashMap<String, Slot> schedule = new HashMap<>();


    public void addMovie(String title, Slot slot){
        if (title ==null|| title.trim().isEmpty()){
            throw new IllegalArgumentException("Name can not be null");
        }
        if (slot == null) {
            throw new IllegalArgumentException("Slot need to be renseigned");
        }
        for(Map.Entry<String, Slot> entry : schedule.entrySet()){
            if (entry.getKey().equals(title) && entry.getValue().equals(slot)){
                throw new IllegalArgumentException("Titles can not be the same");
            }
            if (slot.hasTimeConflict(entry.getValue())){
                throw new IllegalArgumentException("Time confilct");
            }
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


    public boolean importMovie(String[] movie){
        if(movie==null){
            throw new IllegalArgumentException("This tab cannot be empty");
        }

        if (movie.length != 4){
            throw new IllegalArgumentException("Something is missing in your tab");
        }

        for (int i = 0; i < movie.length; i++) {
            if (movie[i] == null || movie[i].trim().isEmpty()) {
                throw new IllegalArgumentException("The element is actually" + i + "empty");
            }
        }
        String title = movie[0].trim();
        String time = movie[1].trim();
        String durationStr = movie[2].trim();
        String room = movie[3].trim();

        SimpleDateFormat format = new SimpleDateFormat("HH'h'mm");
        Date startTime;
        try{
            startTime = format.parse(time);
        }catch (ParseException e){
            throw new IllegalArgumentException("Invalid format");
        }

        int duration;
        try {
            duration = Integer.parseInt(durationStr);
            if (duration<=0 || duration >300){
                throw new IllegalArgumentException("duration can not too high or negative");
            }
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("invalid duration");
        }

        Slot slot = new Slot(startTime, duration,room);

        addMovie(title, slot);

        return true;
    }

    public void importMovies(String[][] movies){
        if (movies == null){
            throw new IllegalArgumentException("can not be null");
        }
        HashMap<String, Slot> backup = new HashMap<>(schedule);

        int successCount = 0;
        int errorCount = 0;

        for (int i = 0; i < movies.length; i++) {
            try {
                importMovie(movies[i]);
                successCount++;
            } catch (IllegalArgumentException e) {
                errorCount++;
                schedule.clear();
                schedule.putAll(backup);
                break;
            }
        }

        System.out.println("\n=== Import result ===");
        System.out.println("Succes count : " + successCount);
        System.out.println("Error count : " + errorCount);
    }



}
