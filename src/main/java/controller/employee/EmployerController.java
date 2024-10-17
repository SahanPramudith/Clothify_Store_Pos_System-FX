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

    @Override
    public boolean update(Employer employer) {
        boolean isupdate;
        try {
            String sql="Update emploer set EmpTitle=?,EmpName=?,EmpAddress=?,EmpCompany=?,EmpMail=? where EpmploerId=? ";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(sql);
            psTm.setObject(1,employer.getTitel());
            psTm.setObject(2,employer.getName());
            psTm.setObject(3,employer.getAddress());
            psTm.setObject(4,employer.getCompany());
            psTm.setObject(5,employer.getMail());
            psTm.setObject(6,employer.getEmpid());

            isupdate = psTm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("isupdate = " + isupdate);
        return isupdate;

    }

    @Override
    public boolean delete(String id) {
        boolean isdelete;
        try {
            //String sql="delete form item where ItemCode=?"+id;
            isdelete = DbConnection.getInstance().getConnection().createStatement().executeUpdate("delete from emploer where EpmploerId='" + id + "'") > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("isdelete = " + isdelete);
        return isdelete;

    }
}
