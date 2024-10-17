package controller.employee;

import db.DbConnection;
import model.Employer;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
