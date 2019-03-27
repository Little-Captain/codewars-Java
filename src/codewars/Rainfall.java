package codewars;

public class Rainfall {

    public static double mean(String town, String strng) {
        try {
            double[] rains = rains(town, strng);
            if (rains.length == 12) {
                return mean(rains);
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static double variance(String town, String strng) {
        try {
            double[] rains = rains(town, strng);
            if (rains.length == 12) {
                return variance(rains);
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static double mean(double[] datas) {
        double sum = 0;
        for (double data : datas) {
            sum += data;
        }
        return sum / datas.length;
    }

    private static double variance(double[] datas) {
        double mean = mean(datas);
        double sum = 0;
        for (double data : datas) {
            double difference = data - mean;
            sum += difference * difference;
        }
        return sum / datas.length;
    }

    private static double[] rains(String town, String strng) throws Exception {
        String[] rainfallOfTowns = strng.split("\n");
        double[] rains = new double[12];
        boolean isFind = false;
        for (String rainfall : rainfallOfTowns) {
            String[] tmp = rainfall.split(":");
            if (tmp.length == 2 && tmp[0].equals(town)) {
                String[] datas = tmp[1].split(",");
                if (datas.length == 12) {
                    isFind = true;
                    for (int i = 0; i < 12; ++i) {
                        String dataString = datas[i];
                        rains[i] = Double.parseDouble(dataString.split(" ")[1]);
                    }
                }
            }
        }
        return isFind ? rains : new double[]{};
    }
}
