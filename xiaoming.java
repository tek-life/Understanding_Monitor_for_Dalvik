class Person{
	public String Name;
	public double Length;
};
class Person_thread extends Thread{
	private Person ming;
	Person_thread(Person p, String name)
	{
		super(name);
		this.ming=p;
	}
	public void run(){
		for(int i=1;i<1000;i++){
			if(getName().equals("Yao Ming"))
			{
				synchronized(ming){
				ming.Name="Yao Ming";
				try{
					Thread.sleep((int)Math.random()*1000);
				}catch(InterruptedException e){
					
				}
				ming.Length=2.26;
				System.out.println(ming.Name+" "+ming.Length+" M");
				}
			}
			else{
				synchronized(ming){
				ming.Name="Guo Jingming";
				try{
					Thread.sleep((int)Math.random()*1000);
				}catch(InterruptedException e){
					
				}
				ming.Length=1.40;
				System.out.println(ming.Name+" "+ming.Length+" M");
				}
			}
		}
		
	}
	
}
class XiaoMing{
	static public void main(String args[]){
		Person xiaoming=new Person();
		Person_thread ming_A=new Person_thread(xiaoming,"Yao Ming");
		Person_thread ming_B=new Person_thread(xiaoming,"Guo Jingming");
		ming_A.start();
		ming_B.start();
	}
	
}
