import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.xml.crypto.Data;


public class Main {
	String[] month = new String[]{"January", "February", "March", "April", "May", "June"
		, "July", "August", "September", "October", "November" , "December"};
	
	int T ;
	
	class time{
		int month;
		int day;
		long year;
		public time(){
			
		}
		public time(time tm){
			this(tm.month,tm.day,tm.year);
		}
		public time(int month,int day,long year){
			this.month= month;
			this.day= day;
			this.year= year;
		}
		public boolean isbigthanorequal(time tm){
			if(tm.year<year){
				return true;
			}
			if(tm.year==year){
				if(tm.month<month){
					return true;
				}
				if(tm.month==month){
					if(tm.day<=day){
						return true;
					}
					
				}
			}
			return false;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return month+" "+day+ year;
		}
	}
	class timedata{
		public time[] times = new time[2];
	}
	List<timedata> data = new ArrayList<timedata>();
	public int getmonth(String mth){
		for(int i = 0 ;i<month.length;i++){
			if(month[i].equals(mth)){
				return i+1;
			}
		}
		return 0;
	}
	public void getData(){
		Scanner sc= new Scanner(System.in);
		T = sc.nextInt();
		sc.nextLine();
		for(int i = 0; i<T;i++){
			timedata tdata =new timedata();
			time tm = new time();
			time tm2 = new time();
			tm.month= getmonth(sc.next());
			String line = sc.next();
			String[] ls= line.split(",");
			tm.day= Integer.valueOf(ls[0]);
			tm.year= sc.nextLong();
			
			tm2.month= getmonth(sc.next());
			String line2 = sc.next();
			String[] ls2= line2.split(",");
			tm2.day= Integer.valueOf(ls2[0]);
			tm2.year= sc.nextLong();
			
			
			
			tdata.times[0]=tm;
			tdata.times[1] =tm2;
			data.add(tdata);
			
			
		}
		
	}
	public time findbegin(time tm){
		long year = tm.year;
		time temp = new time(2,29,tm.year);
		while(! ( islean(year) && temp.isbigthanorequal(tm) )){
			year++;
			temp = new time(2,29,year);
		}
		return temp;
	}
	public time findend(time tm){
		
		long year = tm.year;
		time temp = new time(2,29,tm.year);
		while(! ( islean(year) && tm.isbigthanorequal(temp))){
			year--;
			temp =new time(2,29,year);
		}
		return temp ;
	}
	public void output(long A[]){
		for(int i = 0 ;i<A.length;i++){
			System.out.println("Case #"+(i+1)+": "+A[i]);
		}
	}
	public boolean islean(long year){
		if(year%4 ==0){
			if(year%100 ==0){
				if(year%400 == 0){
					return true;
				}else{
					return false;
				}
				
			}else{
				return true;
			}
		}
		return false;
	}
	
	public long[] calculate(List<timedata> data){
		long[] A = new long[data.size()];
		int i = 0;
		for(timedata td: data){
			long count = 0;
			long countp=0;
			long count100 =0;
			long count400=0;
			time b = findbegin(td.times[0]);
			time e = findend(td.times[1]);
//			long n  = 0;
//			time temp = new time(td.times[0]);
//			while(temp.isbigthanorequal(td.times[0]) && td.times[1].isbigthanorequal(temp)){
//				
//				if(islean(temp.year))
//				n++;
//				temp.year++;
//			}
			
			if(e.isbigthanorequal(b))
			    countp  = (e.year -b.year)/4+1;
			else{
				A[i++] =0;
				continue;
			}
				
			time b1 = find100b(b);
			time c1 = find100e(e);
			if(c1.isbigthanorequal(b1))
			count100 = (c1.year - b1.year) /100 +1;
			
			
			time n1 = find400b(b);
			time n2 = find400e(e);
			if(n2.isbigthanorequal(n1)){
				count400 = (n2.year-n1.year)/400+1;
			}
			
//			System.out.println("coutp  "+countp+" count100 "+count100+" count400"+count400+" b1 " +b1 + "c1" +c1+" b e "+b+"dd"+e+n1+n2);
			count = countp-count100+count400;
//			if(n == count){
//				System.out.println("true");
//			}else{
//				System.out.println(n);
//			}
			A[i++]= count;
		}
		
		return A;
	}
	
	private time find400b(time time) {
		// TODO Auto-generated method stub
		long year = time.year;
		time temp = new time(time.month,time.day,time.year);
		while(!(temp.isbigthanorequal(time) && (temp.year%400 ==0))){
			temp.year +=1;
		}
		return temp;
	}
	private time find400e(time time) {
		// TODO Auto-generated method stub
		long year = time.year;
		time temp = new time(time.month,time.day,time.year);
		while(!(time.isbigthanorequal(temp) && (temp.year%400 ==0))){
			temp.year -=1;
		}
		return temp;
	}
	private time find100b(time time) {
		// TODO Auto-generated method stub
		long year = time.year;
		time temp = new time(time.month,time.day,time.year);
		while(!(temp.isbigthanorequal(time) && (temp.year%100 ==0))){
			temp.year +=1;
		}
		return temp;
	}
	private time find100e(time time) {
		// TODO Auto-generated method stub
		long year = time.year;
		time temp = new time(time.month,time.day,time.year);
		while(!(time.isbigthanorequal(temp) && (temp.year%100 ==0))){
			temp.year -=1;
		}
		return temp;
	}
	
	public static void main(String[] args) {
		Main ly = new Main();
		ly.getData();
//		System.out.println(ly.data);
//		time tm = ly.findbegin(ly.data.get(0).times[0]);
//		sysout
		ly.output(ly.calculate(ly.data));
	}

}
