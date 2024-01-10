import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Прайсы

id(8) lable(30) price(8) quantity(4)

Пример содержимого файла:
19847   Шорты пляжные синие           159.00  12
198479  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            try (BufferedReader bufferedConsole = new BufferedReader(new InputStreamReader(System.in))
            ) {
                int ID = 0;
                String fileName = bufferedConsole.readLine();
                StringBuilder completeLineFromArgs = new StringBuilder();

                for (int i = 1; i < args.length; i++) {
                    completeLineFromArgs.append(args[i] + " ");
                }

                Pattern patternForID = Pattern.compile("\\d{1,8}", Pattern.MULTILINE);
                Matcher matcherForID = patternForID.matcher(completeLineFromArgs);
                matcherForID.find();

                Pattern patternForLable = Pattern.compile("([\\p{sc=Cyrillic}|A-z]+\\s[\\p{sc=Cyrillic}|A-z|\\s]+){1,30}");
                Matcher matcherForLable = patternForLable.matcher(completeLineFromArgs);
                matcherForLable.find();

                Pattern patternForPrice = Pattern.compile("(?<=[А-я|\\s])[0-9|\\.]{1,8}");
                Matcher matcherForPrice = patternForPrice.matcher(completeLineFromArgs);
                matcherForPrice.find();

                Pattern patternForQuantity = Pattern.compile("\\d{1,4} $");
                Matcher matcherForQuantity = patternForQuantity.matcher(completeLineFromArgs);
                matcherForQuantity.find();

                List<String> bufferOfInfo = new ArrayList<>();

                try (BufferedReader buffFileReader = new BufferedReader(new FileReader(fileName))
                ) {
                    switch (args[0]) {
                        case "-d" -> {
                            delete(buffFileReader, patternForID, matcherForID, bufferOfInfo, fileName);
                        }
                        case "-u" -> {
                            update(buffFileReader, patternForID, matcherForID, bufferOfInfo, matcherForLable, matcherForPrice, matcherForQuantity, fileName);
                        }
                        case "-c" -> {
                            create(args);
                        }
                    }
                }
            }
        }
    }

    private static void delete(BufferedReader buffFileReader, Pattern patternForID, Matcher matcherForID, List<String> bufferOfInfo, String fileName) throws IOException {
        while (buffFileReader.ready()) {
            String lineFromFile = buffFileReader.readLine();
            Matcher matcherForIDFromFile = patternForID.matcher(lineFromFile);
            matcherForIDFromFile.find();
            try {
                if (!matcherForIDFromFile.group().equalsIgnoreCase(matcherForID.group())) {
                    bufferOfInfo.add(lineFromFile);
                }
            } catch (IllegalStateException e) {
                System.out.println("This ID is not existe");
            }
        }
        try (FileWriter fileWriter = new FileWriter(fileName)
        ) {
            bufferOfInfo.forEach(item -> {
                try {
                    fileWriter.write(String.format("%s%n", item));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void update(BufferedReader buffFileReader, Pattern patternForID, Matcher matcherForID, List<String> bufferOfInfo, Matcher matcherForLable, Matcher matcherForPrice, Matcher matcherForQuantity, String fileName) throws IOException {
        int ID;
        while (buffFileReader.ready()) {
            String lineFromFile = buffFileReader.readLine();

            Matcher matcherForIDFromFile = patternForID.matcher(lineFromFile);
            matcherForIDFromFile.find();

            ID = Integer.parseInt(matcherForIDFromFile.group());

            try {
                if (matcherForIDFromFile.group().equalsIgnoreCase(matcherForID.group())) {
                    bufferOfInfo.add(String.format("%-8d%-30s%-8.2f%-4s", ID, matcherForLable.group(), Double.parseDouble(matcherForPrice.group()), matcherForQuantity.group()));
                } else {
                    bufferOfInfo.add(lineFromFile);
                }
            } catch (IllegalStateException e) {
                System.out.println("This ID is not existe");
            }
        }
        try (FileWriter fileWriter = new FileWriter(fileName)
        ) {
            bufferOfInfo.forEach(item -> {
                try {
                    fileWriter.write(String.format("%s%n", item));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
    // create functionality in the process.
    // its works, but not as smooth.

    public static void create(String[] args) throws IOException {
        try (BufferedReader bufferedConsole = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String fileName = bufferedConsole.readLine();
            int ID = 0;
            StringBuilder completeLineFromArgs = new StringBuilder();

            for (int i = 1; i < args.length; i++) {
                completeLineFromArgs.append(args[i] + " ");
            }

            try (BufferedReader buffFileReader = new BufferedReader(new FileReader(fileName))
            ) {
                Pattern patternForID = Pattern.compile("\\d{1,8}");
                String lastString = "";

                Pattern patternForLable = Pattern.compile("\\D{1,8}\\D{1,30}\\D");
                Matcher matcherForLable = patternForLable.matcher(completeLineFromArgs);
                matcherForLable.find();

                Pattern patternForPrice = Pattern.compile("(?<=[А-я|\\s])[0-9|\\.]{1,8}");
                Matcher matcherForPrice = patternForPrice.matcher(completeLineFromArgs);
                matcherForPrice.find();

                Pattern patternForQuantity = Pattern.compile("\\d{1,4} $");
                Matcher matcherForQuantity = patternForQuantity.matcher(completeLineFromArgs);
                matcherForQuantity.find();


                while (buffFileReader.ready()) {
                    lastString = buffFileReader.readLine();
                    Matcher matcherForID = patternForID.matcher(lastString);
                    matcherForID.find();
                    ID = Math.max(ID, Integer.parseInt(matcherForID.group()));
                }


                if (args[0].equalsIgnoreCase("-c")) {
                    try (FileWriter fileWriter = new FileWriter(fileName, true)
                    ) {
                        fileWriter.write(String.format("%n%-8d%-30s%-8.2f%-4s", ++ID, matcherForLable.group(), Double.parseDouble(matcherForPrice.group()), matcherForQuantity.group()));
                    }
                }
            }
        }
    }
}