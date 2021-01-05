import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Application {
	Connection con;
	PreparedStatement psinsert,psupdateroll,psdelete,psupdatename,psupdateclass,psupdatemarks;
	ResultSet rs;
	Statement st;
	Scanner sc;
	public Application() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/prac2","root","soham");
			psinsert=con.prepareStatement("insert into students values(?,?,?,?)");
			psupdateroll=con.prepareStatement("update students set roll=? where name=?");
			psupdatename=con.prepareStatement("update students set name=? where rollno=?");
			psupdateclass=con.prepareStatement("update students set class=? where rollno=?");
			psupdatemarks=con.prepareStatement("update students set marks=? where rollno=?");
			psdelete=con.prepareStatement("delete from students where rollno=?");
			sc=new Scanner(System.in);
			st=con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub		

		String ans="yes";
		int ch;
		Scanner sc=new Scanner(System.in);
		Application a=new Application();
		do
		{
				System.out.println("\n !.Insert \n 2.Update \n 3.Delete \n 4.Show \n 5.exit ");
				System.out.println("\n Enter your choice!!");
				ch=sc.nextInt();
				switch(ch)
				{
				case 1:
					a.insert();
					break;
				case 2:
					a.update();
					break;
				case 3:
					a.delete();
					break;
				case 4:
					a.show();
					break;
				case 5:
					ans="no";
					break;
				default :
					System.out.println("\n Enter valid choice!!!!!!!");
					break;
				}
		}while(ans.equals("Yes")||ans.equals("yes"));
	}
	
	void insert()
	{
		System.out.println("Enter roll no, name,class(FE/SE/TE/BE),marks");
		try {
			psinsert.setInt(1, sc.nextInt());
			psinsert.setString(2, sc.next());
			psinsert.setString(3, sc.next());
			psinsert.setInt(4, sc.nextInt());
			
			psinsert.execute();
				System.out.println("Record inserted Successfully");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error has occured "+e.getMessage());
		}
	}
	
	void update()
	{
		int roll=0,choice;
		System.out.println("Enter what you want to update ? \n 1.name \n 2.class \n 3.Marks \n 4. Rollno");
		System.out.println("ENter your choice ");
		choice=sc.nextInt();
		if(choice!=4)
		{
			System.out.println("Enter roll no : ");
			roll=sc.nextInt();
		}
		try
		{
		switch(choice)
		{
		case 1:
			System.out.println("ENter new name : ");
			psupdatename.setString(1, sc.next());
			psupdatename.setInt(2, roll);
			psupdatename.execute();
				System.out.println("Saved Succesfully ");
			break;
		case 2:
			System.out.println("ENter new class : ");
			psupdateclass.setString(1, sc.next());
			psupdateclass.setInt(2, roll);
			psupdateclass.execute();
				System.out.println("Saved Succesfully ");
			break;
		case 3:
			System.out.println("ENter new marks : ");
			psupdatemarks.setInt(1, sc.nextInt());
			psupdatemarks.setInt(2, roll);
			psupdatemarks.execute();
				System.out.println("Saved Succesfully ");
			break;
		case 4:
			System.out.println("ENter new rollno : ");
			psupdateroll.setInt(1, sc.nextInt());
			System.out.println("Enter name for which you want to change");
			psupdateroll.setString(2, sc.next());
			psupdateroll.execute();
				System.out.println("Saved Succesfully ");
			break;
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Update error !!"+e.getMessage());
			
		}
		
		
		
	}
	
	void show()
	{
		
		try {
			rs=st.executeQuery("select * from students");
			while(rs.next())
			{
				System.out.println("Roll number : "+rs.getInt("rollno"));
				System.out.println("Name        : "+rs.getString("name"));
				System.out.println("Class       : "+rs.getString("class"));
				System.out.println("Marks       : "+rs.getInt("marks"));
				System.out.println("\n");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void delete()
	{
		System.out.println("Enter roll no ");
		try {
			psdelete.setInt(1, sc.nextInt());
			psdelete.execute();
				System.out.println("Record deleted succesfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
