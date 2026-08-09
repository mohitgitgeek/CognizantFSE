import java.sql.*;
// Exercises 31–33. Add mysql-connector-j to the classpath and set these settings for your local database.
class JDBCExamples {
  static final String URL="jdbc:mysql://localhost:3306/school", USER="root", PASSWORD="change-me";
  static Connection connect() throws SQLException { return DriverManager.getConnection(URL,USER,PASSWORD); }
  static void listStudents() throws SQLException { try(Connection c=connect();Statement s=c.createStatement();ResultSet r=s.executeQuery("SELECT id,name FROM students")){while(r.next())System.out.println(r.getInt("id")+": "+r.getString("name"));} }
  static void insertStudent(int id,String name)throws SQLException{try(Connection c=connect();PreparedStatement p=c.prepareStatement("INSERT INTO students(id,name) VALUES (?,?)")){p.setInt(1,id);p.setString(2,name);p.executeUpdate();}}
  static void updateStudent(int id,String name)throws SQLException{try(Connection c=connect();PreparedStatement p=c.prepareStatement("UPDATE students SET name=? WHERE id=?")){p.setString(1,name);p.setInt(2,id);p.executeUpdate();}}
  static void transfer(int from,int to,java.math.BigDecimal amount)throws SQLException{try(Connection c=connect()){c.setAutoCommit(false);try(PreparedStatement debit=c.prepareStatement("UPDATE accounts SET balance=balance-? WHERE id=? AND balance>=?");PreparedStatement credit=c.prepareStatement("UPDATE accounts SET balance=balance+? WHERE id=?")){debit.setBigDecimal(1,amount);debit.setInt(2,from);debit.setBigDecimal(3,amount);if(debit.executeUpdate()!=1)throw new SQLException("Insufficient funds or account missing");credit.setBigDecimal(1,amount);credit.setInt(2,to);if(credit.executeUpdate()!=1)throw new SQLException("Destination missing");c.commit();}catch(SQLException e){c.rollback();throw e;}}}
  public static void main(String[]a)throws Exception{Class.forName("com.mysql.cj.jdbc.Driver");listStudents();}
}
