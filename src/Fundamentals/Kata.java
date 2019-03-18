package Fundamentals;

public class Kata {

    public static int opposite(int number) {
        return -number;
    }

    public static String declareWinner(Fighter fighter1, Fighter fighter2, String firstAttacker) {
        int f1WinCount = (fighter2.health - 1) / fighter1.damagePerAttack + 1;
        int f2WinCount = (fighter1.health - 1) / fighter2.damagePerAttack + 1;
        return (firstAttacker == fighter1.name) ?
                (f1WinCount <= f2WinCount ? fighter1.name : fighter2.name) :
                (f2WinCount <= f1WinCount ? fighter2.name : fighter1.name);
    }


}
