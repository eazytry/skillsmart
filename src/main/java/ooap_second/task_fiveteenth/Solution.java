package ooap_second.task_fiveteenth;

public class Solution {
    static abstract class LoanApplication {
        // проскорить заявку на ипотеку
        abstract boolean score();
        // получить данные по доходам в формате json
        String getIncomeData() {
            return null;
        }
    }

    // Зарплатный клиент
    static class StaffLoanApplication extends LoanApplication{
        // для него кредит всегда выдаем
        @Override
        boolean score() {
            return true;
        }
    }

    // Клиент с улицы
    static class StreetLoanApplication extends LoanApplication {

        @Override
        boolean score() {
            var incomeData = super.getIncomeData();
            // какие-то действия и анализ со справками по доходу
            return false;
        }
    }
}
