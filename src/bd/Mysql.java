package bd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class Mysql {
	
	public static void connection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void ConnectionToMysql(String NomCandidat){
		connection();
		String host ="jdbc:mysql://localhost/ppe inscription sportif";
		String username="root";
		String password = "";
		try {
			Connection connect = DriverManager.getConnection(host, username, password);
			System.out.println("Works");
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement("insert into candidat (NomCandidat) values (?)");
			statement.setString(1, NomCandidat);
			statement.executeUpdate();
			statement.close();
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		ConnectionToMysql("Léa");
	}
	/*
	// Supprime personne d'une équipe
 
public void EnleverPersonneEquipe(String mailp, String nome)
{
	
	try 
	{

		query = "call SupprimePersonneEquipe(?,?)";
		prepare = connec.prepareStatement(query);
		prepare.setString(1,mailp);
		prepare.setString(2,nome);
	
		prepare.executeQuery();
	} 
	catch (SQLException e) 
	{
		e.printStackTrace();
	}
}*/

/*
// Supprime candidat d'une competition

public void EnleverCandiCompet(Candidat candidat, Competition competition) {
	
	try 
	{ 
		
		if(candidat instanceof Personne)
		{
			query = "call retirerPersonneCompetition(?,?)";
			prepare = connec.prepareStatement(query);
			prepare.setString(1, ((Personne) candidat).getMail());
			prepare.setString(2,competition.getNom());
		}
		else
		{
			query = "call retirerEquipeCompetition(?,?)";
			prepare = connec.prepareStatement(query);
			prepare.setString(1, candidat.getNom());
			prepare.setString(2,competition.getNom());
		}
		prepare.executeQuery();
	} 
	catch (SQLException e) 
	{
		e.printStackTrace();
	}
}*/
	
}
