package controller.employee;

import javafx.collections.ObservableList;
import model.Employer;

public interface EmployerService {
    boolean add(Employer employer);

    ObservableList<Employer> getall();
}
