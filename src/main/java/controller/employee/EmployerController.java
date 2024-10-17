package controller.employee;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployerController implements EmployerService{
    @Override
    public boolean add(Employer employer) {
        boolean isAdd;
        try {
            String sql="Insert Into Emploer values (?,?,?,?,?,?)";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(sql);
            psTm.setObject(1,employer.getEmpid());
            psTm.setObject(2,employer.getTitel());
            psTm.setObject(3,employer.getName());
            psTm.setObject(4,employer.getAddress());
            psTm.setObject(5,employer.getCompany());
            psTm.setObject(6,employer.getMail());

            isAdd = psTm.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isAdd;
    }

    @Override
    public ObservableList<Employer> getall() {

        ObservableList<Employer> getallEployers = FXCollections.observableArrayList();

        try {
            String sql="select * from emploer";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(sql);
            ResultSet resultSet = psTm.executeQuery();

            while (resultSet.next()){
                Employer employer = new Employer(
                        resultSet.getString("EpmploerId"),
                        resultSet.getString("EmpTitle"),
                        resultSet.getString("EmpName"),
                        resultSet.getString("EmpAddress"),
                        resultSet.getString("EmpCompany"),
                        resultSet.getString("EmpMail")
                );

                getallEployers.add(employer);
                System.out.println("employer = " + employer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getallEployers;
    }
}
