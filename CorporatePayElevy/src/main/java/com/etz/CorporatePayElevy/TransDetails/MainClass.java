package com.etz.CorporatePayElevy.TransDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.etz.CorporatePayElevy.DBConnection.DBConnection;
import com.etz.CorporatePayElevy.ObjectModel.AccountDetails;

public class MainClass implements Job  {

	static Connection con = DBConnection.dbConnection();
	
	

	/*
	 * public static void main(String[]args) {
	 * 
	 * System.out.println((getAccDetails()));
	 * 
	 * 
	 * }
	 */
	
	
		
		public static List<AccountDetails> getAccDetails(){
			 try {
				String sql ="select company_id, account_type, count(*) total_transactions, sum(trans_amount) total_amount, date(created) trans_date from cop_fundgate_log where date(created) = date_sub(current_date, interval 1 day) group by company_id, account_type, date(created)";
	
			
	
				
				List<AccountDetails> myList = new ArrayList<>();
  
				 PreparedStatement ps = con.prepareStatement(sql);
				
				  ResultSet rs = ps.executeQuery();
				  if(rs.next()) {
					
					  AccountDetails account = new AccountDetails();
						int company_id = account.setCompanyId(rs.getInt("company_id"));
						String acc_type = account.setAccountType(rs.getString("account_type"));
						int total_transactions=  account.setTotal_transactions(rs.getInt("total_transactions"));
						int total_amount = account.setTotalAmount(rs.getInt("total_amount"));
						java.sql.Date date = rs.getDate("trans_date");
						myList.add(account);
						
						System.out.println(myList);
						
						int threshold_momo = 100;
						int threshold_bank = 20000;
						int threshold_card = 20000;
						String elevy_percentage ="1.5%";
						
						if(acc_type.equalsIgnoreCase("M")) {
							
							int taxable_income = total_amount - threshold_momo;
							int elevy = (int) ((1.5 * taxable_income)/100);
							System.out.println("elevy for m: "+elevy);
							
							String sql2 = "Insert into cpay_customer_details (company_id, account_type, total_transactions,total_amount,taxable_income,elevy,threshold,elevy_percentage,trans_date) values(?,?,?,?,?,?,?,?,?)";
							try {
                               PreparedStatement statement = con.prepareStatement(sql2);
								
                           	statement.setInt(1, company_id);
							statement.setString(2,acc_type);
							statement.setInt(3, total_transactions);
							statement.setInt(4,total_amount);
							statement.setInt(5,taxable_income);
							statement.setInt(6, elevy);
							statement.setInt(7, threshold_momo);
							statement.setString(8, elevy_percentage);
							statement.setDate(9, date);

							statement.executeUpdate();
							}catch (Exception e) {
								System.out.println(e);
							}
							
							
						}
						else if (acc_type.equalsIgnoreCase("B")) {
							
							int taxable_income = total_amount - threshold_bank;
							int elevy = (int) ((1.5 * taxable_income)/100);
							System.out.println("elevy for B: "+elevy);
							
							String sql2 = "Insert into cpay_customer_details (company_id, account_type, total_transactions,total_amount,taxable_income,elevy,threshold,elevy_percentage,trans_date) values(?,?,?,?,?,?,?,?,?)";
							try {
								PreparedStatement statement = con.prepareStatement(sql2);
								
								statement.setInt(1, company_id);
								statement.setString(2,acc_type);
								statement.setInt(3, total_transactions);
								statement.setInt(4,total_amount);
								statement.setInt(5,taxable_income);
								statement.setInt(6, elevy);
								statement.setInt(7, threshold_bank);
								statement.setString(8, elevy_percentage);
								statement.setDate(9, date);

								statement.executeUpdate();
							}catch (Exception e) {
								System.out.println(e);
							}

							
						}
						else if (acc_type.equalsIgnoreCase("E")) {
							
							int taxable_income = total_amount - threshold_card;
							int elevy = (int) ((1.5 * taxable_income)/100);
							System.out.println("elevy for E: "+elevy);
							
							String sql2 = "Insert into cpay_customer_details (company_id, account_type, total_transactions,total_amount,taxable_income,elevy,threshold,elevy_percentage,trans_date) values(?,?,?,?,?,?,?,?,?)";
							try {
								PreparedStatement statement = con.prepareStatement(sql2);
								
								statement.setInt(1, company_id);
								statement.setString(2,acc_type);
								statement.setInt(3, total_transactions);
								statement.setInt(4,total_amount);
								statement.setInt(5,taxable_income);
								statement.setInt(6, elevy);
								statement.setInt(7, threshold_card);
								statement.setString(8, elevy_percentage);
								statement.setDate(9, date);

								statement.executeUpdate();
							}catch (Exception e) {
								System.out.println(e);
							}

							
						}
						
						
						
			 }}	catch (Exception e) {
					System.out.println(e);
				}finally{}
			return null;
			 
		
		}

		@Override
		public void execute(JobExecutionContext context) throws JobExecutionException {
			
			try {

				System.out.println("Scheduler is working");
				getAccDetails();

			} catch (Exception e) {

				e.printStackTrace();
				
			}
			
		}		  			
		}
			
			