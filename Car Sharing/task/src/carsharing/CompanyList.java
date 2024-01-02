package carsharing;

public class CompanyList extends CarSharingApp {
    CompanyList(CarSharingApp otherObject) {
        this.transferData(otherObject);
    }
    @Override
    String displayMessage() {
       StringBuilder out = new StringBuilder("Choose a company:\n");
       super.companyArrayList.forEach(e -> out.append(e.toString()).append("\n"));
       return out.append("0. Back").toString();
    }
    @Override
    CarSharingApp transition() {
        CarSharingApp newMenu = switch (Integer.parseInt(getInput())) {
            case 0 -> new ManagerMenu(this);
            default -> new CompanyMenu(this);
        };
        return newMenu;
    }
    @Override
    void processInput() {
        int inputInt = Integer.parseInt(getInput());
        if (inputInt > 0) {
            int index = inputInt - 1;
            this.setSelectedCompany(companyArrayList.get(index));
        }
    }
}