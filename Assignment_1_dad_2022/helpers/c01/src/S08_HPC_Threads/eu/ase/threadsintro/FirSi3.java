package eu.ase.threadsintro;

class ResurseComune {
	int a = 0, b = 0;
	
	synchronized void metoda(String nume) {
		Thread t = Thread.currentThread();
		System.out.println(nume + " a=" +a+" b="+b);
		a++;
			try {
				//if(t.getName().compareTo("f2")==0) t.sleep((int)(Math.random()*1000)*2);//blocked thread
				t.sleep((int)(Math.random()*1000));//blocked thread
			} catch(InterruptedException e) {e.printStackTrace();}
		b++; //este atomic?
		/*
		//data fiind sincronizarea => infometare
		//fara synchronized => nu exista infometare
		while (true) {
			System.out.println(nume + " a=" +a+" b="+b);
			try {t.sleep(2000);} catch(InterruptedException ie) {ie.printStackTrace();}
		}
		*/
	}
}

class FirSincronizatA2 extends Thread {
	
	ResurseComune rc;
	
	public FirSincronizatA2(String nume, ResurseComune x){
		super(nume);
		this.rc = x;
	}
		
	public void run(){
		for(int i = 0; i < 3; i++){
			rc.metoda(this.getName());			
		}
		
		System.out.println("GATA! " + this.getName());
	}
}

class ProgMainSiA2 {
	public static void main(String[] args) {
	
		ResurseComune rcd = new ResurseComune();
		
		FirSincronizatA2 f1 = new FirSincronizatA2("f1", rcd);//new Thread
		FirSincronizatA2 f2 = new FirSincronizatA2("f2", rcd);//new Thread
		//FirSincronizatA2 f3 = new FirSincronizatA2("f3", rcd);//new Thread
	
		f1.start();//runnable thread
		f2.start();//runnable thread
		//f3.start();//runnable thread
		
		System.out.println("End main()");
		
	}
}
