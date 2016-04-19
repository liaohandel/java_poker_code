package javapoker;

import java.util.Arrays;

public class pk5cardtype {
/*  2014/04/14 by Handel v1.0
 *  2014/04/14 by handel v1.1 add the flmask,stmask,pamask for show the card type leve
 */
	public String chkver = "pkchk v1.1 2014/04/14 handel";
	
	public int[] card_set = // total 52 card 0..51
	{ 0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08,0x09,0x0a,0x0b,0x0c,0x0d,
	  0x11,0x12,0x13,0x14,0x15,0x16,0x17,0x18,0x19,0x1a,0x1b,0x1c,0x1d,
	  0x21,0x22,0x23,0x24,0x25,0x26,0x27,0x28,0x29,0x2a,0x2b,0x2c,0x2d,
	  0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,0x3a,0x3b,0x3c,0x3d
	};
	
	public String[] logo ={
		"                  ", //0
		"JACKS OR BETTER   ", //1
		"2 PAIR            ", //2
		"3 OF A KIND       ", //3
		"STRAIGHT          ", //4小順
		"BROADWAY_STRAIGHT ", //5大順
		"FLUSH             ", //6 同花
		"FULL HOUSE        ", //7
		"4 OF A KIND       ", //8
		"STR FLUSH         ", //9
		"ROYAL FLUSH       ", //10
		"5 OF A KIND       "  //11
	};
	
	public String[] PokerType = {
	"NO","OP","TP","3K","ST","BT","FL","FH","4K","SF","RF","5K"
	//0    1   2    3    4    5    6    7    8    9    10   11
	};
	
	public String[] PokerNumberChar = {
	 "0","A","2","3","4","5","6","7","8","9","T","J","Q","K"		
	//0   1   2   3   4   5   6   7   8   9   10  11  12  13 
	};
	
	int[] StrToNo ={
		341, 	//0  no pair
		86, 	//1  1 pair
		26, 	//2  2 pair
		23, 	//3  3 of king
		11, 	//4  full_house
		8	    //5  4 of king
	};
	
	int[][] calcardnumber = new int[14][2];
	//[0]:black , [1]:A ,[2..9]:2..9
	//[10]:10,[11]:J,[12]:Q,[13]:K
	
	String cardSeq = "-ATJQK-A23456789TJQK"; //[0-19] char
					// 123456789012345     
		
	int[][][] cardpatter =  {  //[2][3][6]
			{	{ 0,1,2,3,7,8}, //[0]no Flush 	[0][0] NO
				{ 5,0,0,0,0,0}, //[0][1]  BT
				{ 4,0,0,0,0,0}  //[0][2]  ST
			},
			{	{ 6,6,6,6,7,8}, //[1]flush		[1][0] NO
				{10,0,0,0,0,0},	//[1][1] BT				
				{ 9,0,0,0,0,0}	//[1][2] ST			
			}
	};	

	public int[] cardbuff= new int[5];
	public int pktype;
	public int flmask,stmask,pamask;
	public int v2p,v3p,v4p;
	
	public pk5cardtype(){
		for(int i=0;i<=4;i++)cardbuff[i]=0x3f;
		pktype =0;
		v2p=0;v3p=0;v4p=0;
		flmask=0;stmask=0;pamask=0;		
	}
	
	public pk5cardtype(int[] cbuffer){
		v2p=0;v3p=0;v4p=0;
		flmask=0;stmask=0;pamask=0;		
		for(int i=0;i<=4;i++)cardbuff[i] = cbuffer[i];		
	}
	
	public int getpktype(){
		int chkfluidx,chkstridx,chkpairidx;
		v2p=0;v3p=0;v4p=0;
		flmask=0;stmask=0;pamask=0;		

		chkfluidx =  checkflush(cardbuff);
		chkstridx =  checkstraight(cardbuff);
		chkpairidx =   checkpair(cardbuff);		
		pktype = cardpatter[chkfluidx][chkstridx][chkpairidx];
		return pktype;
	}
	
	public void setcardbuff(int[] cbuffer){	
		for(int i=0;i<=4;i++)cardbuff[i] = cbuffer[i];		
	}
	
	public int getcard(int idx){
		return cardbuff[idx];
	}
	
	public void setcard(int card,int idx){
		cardbuff[idx] = card;
	}
	
	public int getmaxtype(){
		return  PokerType.length;
	}
		
	public String gettypelogo(){
		return logo[ getpktype()];		
	}
	
	public String gettypelogo(int idx){
		return logo[idx];
	}
	
	public String getpokertype(){
		return PokerType[ getpktype()];
	}
	
	public String getpokertype(int idx){
		return PokerType[idx];
	}
	
	//2014/04/14 @ handel  
	public int getflmask(){
		return flmask;
	}
	
	public int getstmask(){
		return stmask;
	}
	
	public int getpamask(){
		return pamask;
	}
	
	public int getv2p(){
		return v2p;
	}
	
	public int getv3p(){
		return v3p;
	}
	
	public int getv4p(){
		return v4p;
	}
	
	public String getpkver(){
		return chkver;
	}
	
	//同花
	public int checkflush(int[] cbuffer){
		//int isflush;
		boolean chk;	
		
		flmask=0x00;
		
		for(int i=0;i<=4;i++){
			if( cbuffer[i] == 0x3f )return 0;
		}
		
		chk =((cbuffer[0] & 0xf0) == (cbuffer[1] & 0xf0)) 
		&&((cbuffer[1] & 0xf0) == (cbuffer[2] & 0xf0))
		&&((cbuffer[2] & 0xf0) == (cbuffer[3] & 0xf0)) 
		&&((cbuffer[3] & 0xf0) == (cbuffer[4] & 0xf0));
		
		
		if(chk==false)return 0;
		Arrays.sort(cbuffer);
		flmask = cbuffer[4];		
		return 1;		
	}
	
	//順
	public int checkstraight(int[] cbuffer){
		String chkstr="";
		int[]  chkbuff = new int[5];		
		
		
		for(int i=0;i<=4;i++){
		    if(cbuffer[i]==0x3f)return 0;
			chkbuff[i]=cbuffer[i]&0x0f;
		}
		Arrays.sort(chkbuff);		
		for(int i : chkbuff)chkstr = chkstr + PokerNumberChar[(i & 0x0f)];
		int strindex = cardSeq.indexOf(chkstr);
		// strindex = 1 , 7..15
		//ss2=chkstr+" of "+cardSeq +" => " + strindex;
		Arrays.sort(cbuffer);
		stmask = cbuffer[4];	
		if ( strindex == 1)return 1;
		if ((strindex >=7 ) && ( strindex<=15 ))return 2;
		return 0;
		
	}
	
	//對
	public int checkpair(int[] cbuffer){
		int[] cary = new int[14];
		int costvalue,paleve;
		
		v2p=0;v3p=0;v4p=0;paleve=0;

		//clear buffer
		for(int i=0;i<cary.length;i++)cary[i]=0;		
		for(int i=0;i<=4;i++){
			if(cbuffer[i]==0x3f)return 0;
			cary[(cbuffer[i]&0x0f)]++;
			
			if((cary[(cbuffer[i]&0x0f)]==2) && (cbuffer[i]>v2p) ){
					v2p = cbuffer[i];
			}
			if((cary[(cbuffer[i]&0x0f)]==3) && (cbuffer[i]>v3p) ){
				v3p = cbuffer[i];
			}
			if((cary[(cbuffer[i]&0x0f)]==4) && (cbuffer[i]>v4p) ){
				v4p = cbuffer[i];
			}			
		}
		Arrays.sort(cary);
		
		costvalue = cary[13]    +
				    (cary[12] << 2) +
				    (cary[11] << 4) +
				    (cary[10] << 6) +
				    (cary[9]  << 8);
		
		
		
		//pamask= cary[13][1]; // the mask for pair
		for(int i=0;i<=5;i++){				
			if(costvalue == StrToNo[i]){
				paleve=i;
				break;
			}
		}			
		switch(paleve){
		case 0:
			break;
		case 1:
			pamask = v2p;
			break;
		case 2:
			pamask = v2p;
			break;
		case 3:
			pamask = v3p;
			break;
		case 4:
			pamask = v3p;
			break;
		case 5:
			pamask = v4p;
			break;
		default:
			break;			
		}
		
		return paleve;
	}
	
	
	
	
}
