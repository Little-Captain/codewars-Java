package codewars;

public class TenMinWalk {

    public static boolean isValid(char[] walk) {
        if (walk.length != 10) {
            return false;
        }
        int nCount = 0;
        int sCount = 0;
        int wCount = 0;
        int eCount = 0;
        for (char direction : walk) {
            switch (direction) {
                case 'n':
                    ++nCount;
                    break;
                case 's':
                    ++sCount;
                    break;
                case 'w':
                    ++wCount;
                    break;
                case 'e':
                    ++eCount;
                    break;
            }
        }
        return (nCount == sCount) && (wCount == eCount);
    }
}
