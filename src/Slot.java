import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


public class Slot {
    private Date startTime;
    private int duration;
    private  String room;

    public  Slot(Date startTime, int duration, String room){
        if (startTime== null) {
            throw new IllegalArgumentException("Start time can not be null");
        }
        this.startTime =startTime;
        if (duration<=0){
            throw new ArithmeticException("Duration can not be 0 or negative");
        }
        this.duration = duration;
        if (room==null || room.isEmpty()){
             throw new IllegalArgumentException("Room need to be renseigned");
        }
        this.room = room;
    }

    public int getDuration() {
        return duration;
    }

    public String getRoom(){
        return room;
    }

    public String getStartTime(){
        SimpleDateFormat format = new SimpleDateFormat("HH'h'mm");
        return format.format(startTime);
    }


    //with this we can receive the endTime in a specific format
    public String getEndTime(){
        Date endTime = new Date(startTime.getTime() + ((long)duration*60*1000));
        SimpleDateFormat format = new SimpleDateFormat("HH'h'mm");
        return format.format(endTime);
    }

    public Date getEndTime2(){
        Date endTime = new Date(startTime.getTime() + ((long)duration*60*1000));

        return endTime;
    }

//    public int getEndTime2(){
//        Date endTime = new Date(startTime.getTime() +((long)duration*60*1000));
//        return endTime.getHours();
//    }

    public void display(){
        System.out.println("Start time : "+getStartTime());
        System.out.println("Duration : "+duration);
        System.out.println("Room : "+room);
    }

    public boolean hasTimeConflict(Slot other) {
        if(other == null){
            throw new IllegalArgumentException("error in otherTime");
        }
        if (!this.room.equalsIgnoreCase(other.room)) {
            return false;
        }
        Date firstTimeStart = this.startTime;
        Date firstTimeEnd = this.getEndTime2();
        Date otherTimeStart = other.startTime;
        Date otherTimeEnd = other.getEndTime2();


        if (firstTimeEnd.before(otherTimeStart) || firstTimeStart.after(otherTimeEnd)){
            return false;
        }
        return true;
    }



}
