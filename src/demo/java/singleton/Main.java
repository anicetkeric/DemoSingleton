package demo.java.singleton;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        Account cb1 = new Account(123456789);
        cb1.deposerArgent(100);
        cb1.retirerArgent(80);
        
        Account cb2 = new Account(987654321);
        cb2.retirerArgent(10);
        
        String s = Logging.getInstance().afficherLog();
        System.out.println(s);
	}

}
