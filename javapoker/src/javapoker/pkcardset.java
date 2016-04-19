package javapoker;

import java.util.Random;

public class pkcardset {
/* 2014/04/15 handel pkcardset v1.0 
 * 54 card add 2 joker card small_joker=0x0e,big_joker = 0x1e
 * 2014/04/15 handel v1.1 updat the card emty err!
 *  	
 */
	
	public int[] card_set = // total 54 card 0..53 add 2 joker
	{ 0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08,0x09,0x0a,0x0b,0x0c,0x0d,
	  0x11,0x12,0x13,0x14,0x15,0x16,0x17,0x18,0x19,0x1a,0x1b,0x1c,0x1d,
	  0x21,0x22,0x23,0x24,0x25,0x26,0x27,0x28,0x29,0x2a,0x2b,0x2c,0x2d,
	  0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,0x3a,0x3b,0x3c,0x3d,
	  0x0e,0x1e
	};	

	public int[][] cardtype={
		{ 0,51}, //0 standy 52 card mode
		{ 0,53}, //1 2 joker 54 card mode
		{ 0,12}, //2 13 card of RH mode
		{13,25}, //3 13 card of RD mode
		{26,38}, //4 13 card of BF mode
		{39,51}, //5 13 card of BH mode
		{52,53}  //6 2 card of joker mode
	};

	Random ran = new Random();
	public int[] cardsetflag = new int[54];	
	public int setcardtype;
	public int[] getcardbuff= new int[13];
	
	public pkcardset(){	
		clrcardset(0);//define to 52 card mode
	}	

	public pkcardset(int settype){
		if(settype > 6)settype=0;
		setcardtype = settype;		
		clrcardset(setcardtype);		
	}		
	
	public void clrcardset(int setcardtype){
		for(int i=0;i<=53;i++)cardsetflag[i]=0x00;

		for(int i=cardtype[setcardtype][0];
				i<=cardtype[setcardtype][1];i++)cardsetflag[i]=0x01;		
	}
	
	public int get1card(){
		rand1card(0);
		return getcardbuff[0];
	}
	
	public void get5card(){
		for(int i=0;i<= 4; i++){
			rand1card(i);
		}		
	}

	public void get13card(){
		for(int i=0;i<=12;i++){
			rand1card(i);
		}	
	}
	
	private void rand1card(int idx){
		int randcode,loop ;
		
		// the cardset emty check 
		loop = 0;
		for(int i=0;i<=53;i++){
			loop = loop + cardsetflag[i];
		}
		if(loop == 0){
			getcardbuff[idx] = 0x3f;
			return;
		}
				
		randcode = ran.nextInt(365)+1;
		loop=0;
		while(randcode>0){
			loop++;	
			if(loop>51)loop=0;					
			randcode -= cardsetflag[loop];
		}
		cardsetflag[loop]=0;		
		getcardbuff[idx] = card_set[loop];

		//cardbuff[cardindex]=card_set[ ran.nextInt(51)+1];		
		//return;
	}
	
    public int countCardSet(){
    	int cntset;
    	cntset = 0;
    	
    	for(int i=0;i<= 53;i++){
    		if(cardsetflag[i]>0)cntset++;
    	}
    	
    	return cntset;
    }
	
    public void easeCardFromSet(int card){  	
    	for(int i=0;i<=53;i++){
    		if(card_set[i] == card ){
    			cardsetflag[i]=0;
    			break;
    		}
    	}
    }
    
    public boolean checkCardinSet(int card){	
    	for(int i=0;i<=53;i++){
    		if(card_set[i] == card ){
    			if(cardsetflag[i]==1){
    				return true;
    			}else{
    				return false;
    			}
    		}
    	}
    	
    	return false;
    }
	
	
}
