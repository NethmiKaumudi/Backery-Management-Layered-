package lk.ijse.BackeryManagement.view.tm;

public class PayrollTm {
    private String Nic;
    private String monthYear;
    private Double basicSalary;
    private Double employeeEPF;
    private Double monthlySalary;
    private Double employerEPF;
    private Double employerETF;

    public PayrollTm() {
    }

    public PayrollTm(String nic, String monthYear, Double basicSalary, Double employeeEPF, Double monthlySalary, Double employerEPF, Double employerETF) {
        this. Nic = nic;
        this.monthYear = monthYear;
        this.basicSalary = basicSalary;
        this.employeeEPF = employeeEPF;
        this.monthlySalary = monthlySalary;
        this.employerEPF = employerEPF;
        this.employerETF = employerETF;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public Double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Double getEmployeeEPF() {
        return employeeEPF;
    }

    public void setEmployeeEPF(Double employeeEPF) {
        this.employeeEPF = employeeEPF;
    }

    public Double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public Double getEmployerEPF() {
        return employerEPF;
    }

    public void setEmployerEPF(Double employerEPF) {
        this.employerEPF = employerEPF;
    }

    public Double getEmployerETF() {
        return employerETF;
    }

    public void setEmployerETF(Double employerETF) {
        this.employerETF = employerETF;
    }

    @Override
    public String toString() {
        return "PayrollTm{" +
                "Nic='" + Nic + '\'' +
                ", monthYear='" + monthYear + '\'' +
                ", basicSalary=" + basicSalary +
                ", employeeEPF=" + employeeEPF +
                ", monthlySalary=" + monthlySalary +
                ", employerEPF=" + employerEPF +
                ", employerETF=" + employerETF +
                '}';
    }
}
