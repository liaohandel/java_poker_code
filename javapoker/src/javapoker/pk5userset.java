package javapoker;


public class pk5userset extends pk5cardtype {

	public int[] mycardbuff= new int[5];
	public int mypktype;
	public String mypktypelogo,mypokertype; 
	
	public pk5userset(){	
		for(int i=0;i<=4;i++){
			mycardbuff[i] = 0x3f;
		}		
	}
	
	public void set1usercard(int idx,int card){
		mycardbuff[idx] = card;
	}
	
	public int get1usercard(int idx){
		return mycardbuff[idx];
	}
	
	public int getmypktype(){
		//for(int i=0;i<=4;i++){
		//	cardbuff[i]=mycardbuff[i];
		//}
		setcardbuff(mycardbuff);		
		mypktype = getpktype();
		return mypktype;
	}
	
	public String getmypokertype(){
		setcardbuff(mycardbuff);		
		mypktype = getpktype();
		mypokertype = getpokertype(mypktype);
		return mypokertype;
	}
	
}
