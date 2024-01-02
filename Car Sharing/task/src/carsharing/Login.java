package carsharing;

class Login extends CarSharingApp {
    // Login() { }
    Login(CarSharingApp otherObject) {
        this.transferData(otherObject);
    }
    public String displayMessage() {
        return "1. Log in as a manager\n2. Log in as a customer\n3. Create a customer\n0. Exit";
    }
    protected CarSharingApp transition() {
        CarSharingApp newMenu = switch (Integer.parseInt(getInput())) {
            case 1 -> new ManagerMenu(this);
            case 2 -> new CustomerList(this);
            case 3 -> new CreateCustomer(this);
            default -> this;
        };
        return newMenu;
    }
    protected void processInput() throws Exception {
        if (Integer.parseInt(getInput()) == 0) {
            running = false;
        }
        if (Integer.parseInt(getInput()) == 2) {
            this.customerArrayList = this.customerDao.findAll();
            if (this.customerArrayList.isEmpty()) {
                throw new Exception("The customer list is empty!");
            }
        }
    }
}