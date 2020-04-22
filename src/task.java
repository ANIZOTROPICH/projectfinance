import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;



class Table {
	private ArrayList<Record> table;
	
	public ArrayList<Record> get() {
		return this.table;
	}
	
	public Table() {
		this.table = new ArrayList<Record> ();
	}
	
	public void toAddRecord(Record r) {
		this.table.add(r);
	}
	
	public void toRemoveRecord(int i) {
		this.table.remove(i);
	}
	
	public void toRemoveRecord(Record r) {
		this.table.remove(r);
	}
	
	public double sumOfIncomes() {
		double a = 0;
		for (Record r: this.table) {
			if (r.getAmount()>0) {
				a+=r.getAmount();
			}
		}
		return a;
	}
	
	public double sumOfOutcomes() {
		double a = 0;
		for (Record r: this.table) {
			if (r.getAmount()<0) {
				a+=Math.abs(r.getAmount());
			}
		}
		return a;
	}
	
	public double total() {
		return this.sumOfIncomes()-this.sumOfOutcomes();
	}
	
	public double totalAfterDate(String date) throws ParseException {
		double a = 0;
		for (Record r: this.table) {
			if (r.getDate().after(new SimpleDateFormat("y-M-d").parse(date))) {
				a+=r.getAmount();
			}
		}
		return a;
	}
	
	public double totalBeforeDate(String date) throws ParseException {
		double a = 0;
		for (Record r: this.table) { 
			if (r.getDate().before(new SimpleDateFormat("y-M-d").parse(date))) {
				a+=r.getAmount();
			}
		}
		return a;
	}
	

	
	public String toString() {
		return "Incomes: "+this.sumOfIncomes()+"\n"+"Outcomes: "+this.sumOfOutcomes()+"\n"+"Total: "+this.total();
	}
	
}



class Record {
	private double amount;
	private String name;
	private Date date;
	
	public Record(double amount, String name,Date date) {
		if (amount != 0) {
		this.amount = amount;	
		this.name = name;
		this.date = date;
		}
		
		}
	
	
	public double getAmount() {
		return this.amount;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public String getDateForm() {
		return (this.date.getYear()+1900)+"-"+this.date.getMonth()+"-"+this.date.getDate();
	}
	
	public void setAmount(double value) {
		this.amount = value;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public  boolean Equals() {
		return this.amount > 0;
	}
	
	public String toString() {
		return "Name: "+this.name+"\n"+"Amount: "+this.amount+"\n"+"Date: "+(this.date.getYear()+1900)+"-"+this.date.getMonth()+"-"+this.date.getDate();
	}
}


public class task { 


		public static void main(String[] args) throws NumberFormatException, IOException, ParseException {
			 Table tabl = new Table();
			 
			 			  BufferedReader in = new BufferedReader(new FileReader("../Project/111.txt"));
			    String s1;
			    String name = in.readLine() + "\n";
			    
			    while((s1 = in.readLine()) != null){
			        if (!s1.isEmpty()) {name += s1 + "\n";}		        
			        else {	        	
			        	String[] name2 = name.split(" ");
			        	tabl.toAddRecord(new Record(Integer.parseInt(name2[0]),name2[1],new SimpleDateFormat("y-M-d").parse(name2[2]) ));
			        	name = "";
			        }
			    }
			    in.close();
			 
			 
			 
			    tabl.get().sort(new Comparator<Record>() {
					public int compare(Record o1, Record o2) {
						return o1.getDate().compareTo(o2.getDate());
					}
				}); 
			 
			
			 
			 
			 
			
			 int i = 0;
			 
			 for(Record s : tabl.get() ) {
				 System.out.println(tabl.get().get(i).toString());
				 System.out.println(tabl.get().get(i).Equals());
				 System.out.print("\n");
				 i++;
				 
				}
			System.out.println(tabl.toString());
			System.out.println(tabl.totalAfterDate("2016-7-13"));
			System.out.println(tabl.totalBeforeDate("2016-1-9"));
			

			 	
		}
	
	

}
