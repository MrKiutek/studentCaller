package Java.Server;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;





public class Main {
 

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IOException {

        //Bdd BDD = new Bdd();

		int portEcoute = 10302;
		ServerSocket standardiste;
		Socket socket;

		try {
			standardiste = new ServerSocket(portEcoute);
			while(true) {
				socket = standardiste.accept();
	 			new mySocket(socket);
			}
		}
		catch(IOException exc) {
	 		System.out.println("probleme de connexion");
		}
	}

	}	
