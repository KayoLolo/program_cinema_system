public class Test {
    public static void main(String[] args){
        MovieScheduler movieScheduler = new MovieScheduler();

        System.out.println("===  ===");
        movieScheduler.importMovies(MovieSlotTestData.getValidMovieSchedule());
        System.out.println("=== ===");
        movieScheduler.importMovies(MovieSlotTestData.getProblematicMovieSchedule());
        System.out.println("=== ===");
        movieScheduler.importMovies(MovieSlotTestData.getDuplicateMovieSchedule());
        System.out.println("=== ===");
        movieScheduler.importMovies(MovieSlotTestData.getConflictingSchedule());
        System.out.println("=== ===");
        movieScheduler.importMovies(MovieSlotTestData.getInvalidMovieSchedule());
    }
}
