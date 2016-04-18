package iart;

import iart.AStar.AStarSearch;
import iart.Greedy.Greedy;

import java.util.ArrayList;

/**
 * Created by Pedro Castro on 02/03/2016.
 */
public class main {

    public static void main(String[] args)
    {
        Hopeless hopeAStar = new Hopeless(10,20,4);

        Hopeless hopeAStar2 = new Hopeless(10,20,4);

        Hopeless hopeDFS = new Hopeless(10,20,4);

        Hopeless hopeBBound = new Hopeless(10,20,4);

        hopeDFS.table = new ArrayList<>(hopeAStar.table);

        hopeBBound.table = new ArrayList<>(hopeAStar.table);

        hopeAStar2.table = new ArrayList<>(hopeAStar.table);

        hopeAStar.print();

        runAstar(hopeAStar2,false);






        //dfs(hopeDFS);

        greedy(hopeBBound);
    }

    static void greedy(Hopeless hope){
        long startTime = System.currentTimeMillis();

        ArrayList<Integer> initialTable = new ArrayList<>(hope.table);

        //A STAR
        Greedy rip = new Greedy(hope);

        ArrayList<Point> bestMoves = rip.getBBoundMoves();

        hope.table = initialTable;

        int points = 0;

            for(Point move : bestMoves)
            {
                points += hope.makePlay(move,new ArrayList<Point>());
            }
        System.out.println("Final ----");
        hope.print();


        System.out.println("Moves = " + bestMoves);
        System.out.println("Greedy Score = " + points);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Greedy Time of Computing = " + totalTime);
    }


    static void runAstar(Hopeless hope, boolean heu){

        long startTime = System.currentTimeMillis();

        ArrayList<Integer> initialTable = new ArrayList<>(hope.table);


        //A STAR
        AStarSearch rip = new AStarSearch(hope, heu);

        ArrayList<Point> bestMoves = rip.getAStarMoves();

        hope.table = initialTable;

        int points = 0;

        if(rip.getBestScore()!= 0)
            for(Point move : bestMoves)
            {
                points += hope.makePlay(move,new ArrayList<Point>());
            }
        System.out.println("Final ----");
        hope.print();


        System.out.println("Moves = " + bestMoves);
        System.out.println("A* Score = " + rip.getBestScore());

        System.out.println("A* Sum Of Points = " + points);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("A* Time of Computing = " + totalTime);
    }

    static void dfs(Hopeless hope){
        long startTime = System.currentTimeMillis();

        DepthFirstSearch rip = new DepthFirstSearch(hope);

        if(rip.bestScore!= 0)
            for(Point move : rip.bestMoves)
            {
                hope.makePlay(move,new ArrayList<Point>());
            }
        System.out.println("Final ----");
        hope.print();

        System.out.println("Moves = " + rip.bestMoves);
        System.out.println("Score = " + rip.bestScore);
        System.out.println("Solutions = " + rip.solutions);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Time of Computing = " + totalTime);
    }
}
