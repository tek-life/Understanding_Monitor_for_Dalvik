class Reader_Writer{
	public static void main(String[] vargs){
	Shared s= new Shared();
	Writer_thread the_writer_thread=new Writer_thread(s);
	Reader_thread the_reader_thread=new Reader_thread(s);
	the_writer_thread.start();
	the_reader_thread.start();
	}
}
class Shared{
	private char ch='\000';
	private boolean readable = false;
	synchronized void set_ch(char ch){		
		while(readable)
			try{
				this.wait();
			}catch(InterruptedException e){}
			
		this.ch=ch;
		readable=true;
		notify();
	}
	synchronized char get_ch(){
		while(!readable)
			try{
					this.wait();
				}catch(InterruptedException e){}
			
		readable = false;
		notify();
		return ch;
	}
}
class Reader_thread extends Thread{
	private Shared shared_object;
	Reader_thread(Shared shared_object)
	{
		super("Reader");
		this.shared_object=shared_object;
	}
	public void run()
	{
		char ch;
		do{
		try{
			Thread.sleep((int)(Math.random() * 4000));
		}catch(InterruptedException e){}
		ch=shared_object.get_ch();
		System.out.println(ch+" "+getName());
		}while(ch!='Z');
	}
}
class Writer_thread extends Thread{
	private Shared shared_object;
	Writer_thread(Shared shared_object)
	{
		super("Writer");
		this.shared_object=shared_object;
	}
	public void run()
	{
		for(char ch='A'; ch<='Z';ch++)
		{
			try{
				Thread.sleep((int)(Math.random()*4000));
			}catch(InterruptedException e){}
			shared_object.set_ch(ch);
			System.out.println(ch+" "+getName());
		}
	}

}
