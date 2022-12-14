package gunnuuu.calendar;

import java.text.ParseException;
import java.util.Scanner;

public class Prompt {
	
	public void printMenu() {
		System.out.println("+----------------------+");
		System.out.println("| 1. 일정 등록");           
		System.out.println("| 2. 일정 검색");         
		System.out.println("| 3. 달력 보기");
		System.out.println("| h. 도움말 q. 종료");
		System.out.println("+----------------------+");
	}
	
	public int parseDay(String week) {
		switch(week) {
		case "su":
			return 0;
		case "mo":
			return 1;
		case "tu":
			return 2;
		case "we":
			return 3;
		case "th":
			return 4;
		case "fr":
			return 5;
		case "sa":
			return 6;
		default:
			return 0;		
		}
	}

	
	public void runPrompt() throws ParseException {	
		printMenu();
		//숫자를 입력받아 해당하는 달의 최대 일수를 출력하는 프로그램
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();		
	
		while(true) {
			System.out.println("명령 (1, 2, 3, h, q)");
			String cmd = scanner.next();
			if(cmd.equals("1")) 
				cmdRegister(scanner,cal);
			else if(cmd.equals("2")) 
				cmdSearch(scanner,cal);
			else if(cmd.equals("3")) 
				cmdCal(scanner,cal);
			else if(cmd.equals("h")) 
				printMenu();
			else if(cmd.equals("q")) 
				break;
		}
			
		System.out.println("Thank you. Bye~");
		scanner.close();
	}

	private void cmdCal(Scanner s, Calendar c) {
		int year = 0;
		int month = 0;
		System.out.print("YEAR> ");
		year = s.nextInt();
		
		System.out.println("달을 입력하시오");
		System.out.print("MONTH> ");
		month = s.nextInt();
		
		if(month>12 || month<1) {
			System.out.println("잘못된 입력입니다.");
			return;
		}
		c.printCalendar(year, month);
		System.out.println();		
	}

	private void cmdSearch(Scanner s,Calendar c) {
		System.out.println("[일정검색]");
		System.out.println("날짜를 입력하세요(yyyy-mm-dd)");
		String date = s.next();
		PlanItem plan;
		plan = c.searchPlan(date);
		if(plan != null)
			System.out.println(plan.detail);
		else
			System.out.println("일정이 없습니다.");
	}

	private void cmdRegister(Scanner s,Calendar c) throws ParseException {
		System.out.println("[새 일정등록]");
		System.out.println("날짜를 입력하세요(yyyy-mm-dd)");
		String date = s.next();
		String text = "";
	    s.nextLine(); //ignore one newline
	    System.out.println("일정을 입력해 주세요.");
	    text = s.nextLine();
	    c.registerPlan(date, text);
	}

	public static void main(String[] args) throws ParseException {
		Prompt p = new Prompt();
		p.runPrompt();
	}
}
