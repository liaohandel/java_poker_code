package javapoker;
//import java.net.*;
//import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;

//import pk5userset;


public class mainscreen  extends  Applet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Image showjpg;
    public Image blackcard;
	public Image takekeymg,openkeymg,betkeymg;
	public Image holdkeymg,unholdkeymg;
	public Image[] ccc = new Image[52];	
	
	public String message;
	public String message1;
	public String message2;

	public int sx,sy;
	public int sx2,sy2;
	public int mactpos;
	// 5 poker game pam
	public int userbank,membet,memwin;
	public int beackbet,bounes_no;
	public int gameloop,drawloop;
	
	Random ran = new Random();

	public int[] cardsetflag = new int[52];	
	
	public int[] card_set = // total 52 card 0..51
	{ 0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08,0x09,0x0a,0x0b,0x0c,0x0d,
	  0x11,0x12,0x13,0x14,0x15,0x16,0x17,0x18,0x19,0x1a,0x1b,0x1c,0x1d,
	  0x21,0x22,0x23,0x24,0x25,0x26,0x27,0x28,0x29,0x2a,0x2b,0x2c,0x2d,
	  0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,0x3a,0x3b,0x3c,0x3d
	};
	
	String[] logo ={
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
	
	int[] bonus_pam ={ 0,1,2,3,4,5,7, 8,50,100,200,300};
	
	String[] gkeyfilename ={
		"OVPK","JKPK",	//0,1                                  01:hold 03:erase  
		"button00","button01","button02","button03","button04",//2,3,4,5,6
		"button05","button06","button07","button08","button09",//7,8,9,10,11
		"button10","button11","button12"                       //12,13,14		
	};
	
	String[] GRHname ={"OVPK",					//0
			"rh01","RH02","RH03","RH04","RH05",	//1,2,3,4,5
			"RH06","RH07","RH08","RH09","RH10", //6,7,8,910
			"RH11","RH12","RH13"				//11,12,13
			};
	
	String[] GRDname ={"OVPK",
			"RD01","RD02","RD03","RD04","RD05",
			"RD06","RD07","RD08","RD09","RD10",
			"RD11","RD12","RD13"			
	};
	String[] GBFname ={"OVPK",
			"BF01","BF02","BF03","BF04","BF05",
			"BF06","BF07","BF08","BF09","BF10",
			"BF11","BF12","BF13"
			
	};
	String[] GBHname ={"ovpk",
			"BH01","BH02","BH03","BH04","BH05",
			"BH06","BH07","BH08","BH09","BH10",
			"BH11","BH12","BH13"			
	};
	
	
	int[][] logopos = {
			{ 10,10},	//0
			{ 10,30},	//1
			{ 10,50},	//2
			{ 10,70},	//3
			{ 10,90},	//4
			{ 10,110},	//5
			{ 10,130},	//6
			{ 10,150},	//7
			{ 10,170},	//8
			{ 10,190},	//9
			{ 10,210},	//10
			{  5,220},	//11 card1
			{145,220},	//12 card2
			{285,220},	//13 card3
			{425,220},	//14 card4
			{565,220},  //15 card5
			{ 40,405},	//16 hold1
			{180,405},	//17 hold2
			{320,405},	//18 hold3
			{460,405},	//19 hold4
			{600,405},	//20 hold5
			{500, 60},	//21 BET key
			{500,160},	//22 OPEN key
			{500, 10},	//23 TAKE key
			{300, 30},  //24 userbank 
			{300, 70},  //25 membet
			{300,170},	//26 memwin
			{300,125},	//27 win or loas message
			{300,150}   //28 bonus logo
	};
	
	int[][] mouseActpos = {
			{  0,  0,  1, 1},   //0
			{500, 60,100, 40},	//1 bet
			{500,160,100, 40},	//2 open
			{500, 10,100, 40},	//3 take
			{  5,220,120,160},	//4 card1
			{145,220,120,160},	//5 card2
			{285,220,120,160},	//6 card3
			{425,220,120,160},	//7 card4
			{565,220,120,160}   //8 card5			
	};
	
	
	int[][] squarepos ={
			{300, 10,150,25},
			{300, 50,150,25},
			{300,150,150,25}
	};

	int[] holdflag= new int[5];
	int[] cardbuff= new int[5];
	
	public pk5cardtype chkpk = new pk5cardtype();
	public pk5userset user1 = new pk5userset();
	public pkcardset set1 = new pkcardset();
	
	public void init(){
		sx=5;
		sy=5;
		sx2=10;
		sy2=10;
		mactpos=0;
		userbank = 168;
		membet = 0;
		memwin = 0;
		bounes_no = 0;
		beackbet = 0;

		gameloop=0;
		drawloop=0;
		
		
		set1.clrcardset(0);
		
		clrcardflag();		
		for(int i=0;i<=4;i++){
			holdflag[i]=0x00;
			cardbuff[i]=0x3f;
		}
		
		message1 ="              ";
		message2 ="              ";
		blackcard = getImage(getCodeBase(),GBFname[0]+".gif");
		loadccc();
	
	} //class startup init 	
	public void start(){}//after init run this 
	
	public void paint(Graphics g){
		g.setFont(new Font("Serif",Font.BOLD,24));	// 設定字型
		//g.drawString(message,10,10);
		//g.drawRect(sx, sy, sx2-sx, sy2-sy);

		//g.drawLine(10, 10,200, 200);
		//g.drawRect(5,15, 130, 200);	
		
		// mouse active key areg check flow
		
		for(int i=0;i<=2;i++){
			g.drawRect( squarepos[i][0], squarepos[i][1],
					    squarepos[i][2], squarepos[i][3]);			
		}

		//show1card(g,0x01,300,100);

		g.drawString(message1,logopos[28][0],logopos[28][1]);
		g.drawString(message2,logopos[27][0],logopos[27][1]);
		showmainscreen(g);
		//	showbonse(g);
			showmoney(g);		
			showfunkey(g);
			
		
		show5card(g);
		show5button(g);
		//drawloop=0;
	}//show screen 
	
	public void stop(){} // 解構式
	public void destory(){} // end applet
	
	public boolean mouseDown(Event evtObj,int x,int y){
			
		showStatus("mouseDown");
		
		int ppp;		
		ppp = mouseactcheck(x,y);		
		//message = "mouseDown x:"+x+" y:"+y+"mact: "+ ppp;
		//gameloop = 0 wait for bet 
		//gameloop = 1 open 1st
		//gameloop = 2 open 2st
		//gamellop = 3 win and wait take
		
		switch(gameloop){
		case 0: // gameloop mode 0
			switch(ppp){
			case 1: // bet
				if(userbank > 0){
					userbank--;
					membet++;
				}				
				if(membet >= 10){	
					ppp=2;
				}else{
					gameloop = 0;
					break;
				}				
			case 2: //open
				//clrcardflag();
				if(membet > 0){					
					runrand5card();					
					
					message1 =logo[user1.getmypktype()];
					gameloop = 1;
				}
				break;
			default:
				break;	
			}			
			break;
	
		case 1: //gameloop mode 1			
			switch(ppp){			
			case 2: //open
				//clrcardflag();
				//runrand5card();
				for(int i=0;i<=4;i++){
					if(holdflag[i]==0){
						 user1.mycardbuff[i]=0x3f;
					}
				}
				gameloop = 3;
				break;
			case 4:	//hold1	
				//if(gameloop==0)break;
				if(holdflag[0]==0){
					holdflag[0]=0xff;
				}else{
					holdflag[0]=0x00;				
				}
				break;
			case 5: //hold2
				//if(gameloop==0)break;
				if(holdflag[1]==0){
					holdflag[1]=0xff;
				}else{
					holdflag[1]=0x00;				
				}
				break;
			case 6: //hold3	
					//if(gameloop==0)break;
				if(holdflag[2]==0){
					holdflag[2]=0xff;
				}else{
					holdflag[2]=0x00;				
				}
				break;
			case 7: //hold4
					//if(gameloop==0)break;
				if(holdflag[3]==0){
					holdflag[3]=0xff;
				}else{
					holdflag[3]=0x00;				
				}
				break;
			case 8: //hold5
					//if(gameloop==0)break;
				if(holdflag[4]==0){
					holdflag[4]=0xff;
				}else{
					holdflag[4]=0x00;				
				}
				break;		
			default:
				break;	
			}			
			break;
		case 3: // gameloop show unhold card
				switch(ppp){
				case 2: //open
					//clrcardflag();
					runrand5card();					
					message1 = logo[user1.getmypktype()];
					memwin = membet * bonus_pam[user1.getmypktype()];
					gameloop = 2;
					break;
				default:
					break;
				}
				break;
		case 2: //gameloop mode 2
			switch(ppp){
			case 3: // take
				if(memwin>0){
					userbank+=memwin;
					memwin=0;
				}
				membet=0;
				message2 ="              ";
				//clrcardflag();
				set1.clrcardset(0);
				for(int i=0;i<=4;i++){
					holdflag[i]=0x00;
					user1.set1usercard(i,0x3f);
				}	
				//chkpk.setcardbuff(cardbuff);
				message1 = logo[user1.getmypktype()];
				gameloop=0;
				break;
			default:
				if (memwin >0){
					message2 ="[you win ...] ";
				}else{
					message2 ="[you lose...] ";
				}
				break;	
			}						
			break;
		default:
			break;
		}
		
		
		message = "mouseDown x:"+x+" y:"+y+"mact:"+ ppp +"loop:"+gameloop;
		//ppp=0;
		//if(ppp!=0)
		repaint();
		return(true);
	}
	
	public boolean mouseup(Event evtObj,int x,int y){
		repaint();
		return(true);
	}
	public boolean mouseExit(Event evtObj,int x,int y){
		repaint();
		return(true);
	}
	public boolean mouseMove(Event evtObj,int x,int y){
		//repaint();
		return(true);
	}
	public boolean mouseDrag(Event evtObj,int x,int y){
		repaint();
		return(true);
	}
	
	
	public void showmainscreen(Graphics g){
		for(int i=1;i<11;i++){
			g.drawString(logo[i], logopos[i][0],logopos[i][1]);
		}
	}
	
	public void showbonse(Graphics g){
		int bone;
		
		bone = 100;
		for(int i=1;i<10;i++){
			g.drawString( String.valueOf(bone) , logopos[i][0]+130,logopos[i][1]);
		}
	}
	
	public void show1card(Graphics g,int card,int xx,int yy){
		//int cardcolor,cardnumber;
		
		Image cardimg;
		
		int ii;
		
		if(card == 0x3f){			
			g.drawImage(blackcard,xx,yy, null);
			return;
		}
		
		for(ii=0;ii<=52;ii++){
			if(card_set[ii]==card)break;
		}		
		
		cardimg = ccc[ii];		
		g.drawImage(cardimg,xx,yy, null);
	}
	
	public void show5card(Graphics g){		
		for(int i=0;i<=4;i++){		
			show1card(g,user1.mycardbuff[i],logopos[i+11][0],logopos[i+11][1]);
		}		
	}
	
	public void show5button(Graphics g){
				
		for(int j=0;j<=4;j++){
			if(holdflag[j]==0){
				//showjpg =getImage(getCodeBase(),"pkimage/"+gkeyfilename[5]+".jpg");
				g.drawImage(unholdkeymg,logopos[j+16][0],logopos[j+16][1], null);	
				
			}else{
				//showjpg =getImage(getCodeBase(),"pkimage/"+gkeyfilename[3]+".jpg");
				g.drawImage(holdkeymg,logopos[j+16][0],logopos[j+16][1], null);
			}
		}		
	}
	
	public void showfunkey(Graphics g){
  		g.drawImage(betkeymg,logopos[21][0],logopos[21][1], null);	
		g.drawImage(openkeymg,logopos[22][0],logopos[22][1], null);
		g.drawImage(takekeymg,logopos[23][0],logopos[23][1], null);		
	}
	
	public void showmoney(Graphics g){
		
		g.drawString("BANK: "+ userbank, logopos[24][0],logopos[24][1]);
		g.drawString("BET : "+ membet  , logopos[25][0],logopos[25][1]);
		g.drawString("WIN : "+ memwin  , logopos[26][0],logopos[26][1]);		
	}

	
	public void runrand1card(int cardindex){
		user1.mycardbuff[cardindex] = set1.get1card();
	}
	
	
	public void runrand5card(){		
		for(int i=0;i<=4;i++){
			if(holdflag[i]==0)user1.mycardbuff[i]=set1.get1card();
		}
	}
	
	public void clrcardflag(){
		for(int i=0;i<=51;i++){
			cardsetflag[i] = 1;
		}
	}
	
	public void loadccc(){
		int cardcolor,cardnumber;
		Image cardimg;
		
		for(int i=0;i<=51;i++){
			cardcolor = card_set[i] & 0x00f0;
			cardnumber = card_set[i] & 0x000f;
			
			switch(cardcolor){
			case 0x00: //RH
				cardimg = getImage(getCodeBase(),GRHname[cardnumber]+".gif");
				break;
			case 0x10: //RD
				cardimg = getImage(getCodeBase(),GRDname[cardnumber]+".gif");
				break;
			case 0x20: //BH
				cardimg = getImage(getCodeBase(),GBHname[cardnumber]+".gif");
				break;
			case 0x30: //BF
				cardimg = getImage(getCodeBase(),GBFname[cardnumber]+".gif");
				break;
			default:
				cardimg = getImage(getCodeBase(),GBFname[0]+".gif");
				break;
			}
			
			ccc[i]=cardimg;			
		}
		
		betkeymg = getImage(getCodeBase(),gkeyfilename[7]+".gif");
		openkeymg = getImage(getCodeBase(),gkeyfilename[8]+".gif");
		takekeymg = getImage(getCodeBase(),gkeyfilename[9]+".gif");
		
		unholdkeymg = getImage(getCodeBase(),gkeyfilename[5]+".gif");
		holdkeymg = getImage(getCodeBase(),gkeyfilename[3]+".gif");
	}
	
	public int mouseactcheck(int fx,int fy){
		//int actfun=0;
		int agx1,agy1,agx2,agy2;
		
		for(int i=1;i<=8;i++){
			agx1=mouseActpos[i][0];
			agy1=mouseActpos[i][1];
			agx2=mouseActpos[i][0]+mouseActpos[i][2];
			agy2=mouseActpos[i][1]+mouseActpos[i][3];
			
			if((fx >= agx1)&&(fx <= agx2)){
				if((fy>=agy1)&&(fy<=agy2)){
					return i;
				}
			}			
		}
		return 0;
	}
	
	
}
