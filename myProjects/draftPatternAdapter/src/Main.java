import java.util.HashMap;
import java.util.Map;

/*
Адаптация нескольких интерфейсов
Practice of implementing adaptors.
*/

public class Main {
    public static Map<String, String> countries = new HashMap<String, String>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {


    }
    /*
    Class adapter form IncomeData to Customer and Contact view.
     */

    public static class IncomeDataAdapter implements Customer, Contact {
        private IncomeData data;

        public IncomeDataAdapter(IncomeData data) {
            this.data = data;
        }


        @Override
        public String getCompanyName() {
            return data.getCompany();
        }

        @Override
        public String getCountryName() {
            return countries.get(data.getCountryCode());
        }

        @Override
        public String getName() {
            return data.getContactLastName() + ", " + data.getContactFirstName();
        }

        @Override
        public String getPhoneNumber() {
            int secondDouble = data.getPhoneNumber() % 100;
            int firstDouble = data.getPhoneNumber() / 100 % 100;

            int secondTriple = data.getPhoneNumber() / 10_000 % 1000;
            int firstTriple = data.getPhoneNumber() / 10_000_000 % 1000;

            String phoneNumber = String.format("+%d(%03d)%03d-%02d-%02d", data.getCountryPhoneCode(), firstTriple, secondTriple, firstDouble, secondDouble);

            return phoneNumber;
        }
    }


    public interface IncomeData {
        String getCountryCode();        //For example: UA

        String getCompany();            //For example: JavaRush Ltd.

        String getContactFirstName();   //For example: Ivan

        String getContactLastName();    //For example: Ivanov

        int getCountryPhoneCode();      //For example: 38

        int getPhoneNumber();           //For example1: 501234567, For example2: 71112233
    }

    public interface Customer {
        String getCompanyName();        //For example: JavaRush Ltd.

        String getCountryName();        //For example: Ukraine
    }

    public interface Contact {
        String getName();               //For example: Ivanov, Ivan

        String getPhoneNumber();        //For example1: +38(050)123-45-67, For example2: +38(007)111-22-33
    }
}