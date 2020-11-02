package com.binghe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


// JFrame이며, 게임의 출력을 담당.
public class GameObject extends JFrame implements Runnable{

	static boolean Crash(int x1, int y1, int x2, int y2, int w1, int h1, int w2, int h2){
		// x,y : 위치값 , w,h : 이미지의 높이와 길이.
		boolean result = false;
		if(Math.abs( ( x1 + w1 / 2 )  - ( x2 + w2 / 2 ))  <  ( w2 / 2 + w1 / 2 )  
				&& Math.abs( ( y1 + h1 / 2 )  - ( y2 + h2 / 2 ))  <  ( h2 / 2 + h1/ 2 ))
			result = true;
		else result = false;
		return result;
	}
	
	int frameWidth = 800; // JFrame 폭.
	int frameHeight = 600; // JFrame 넓이.
	
	Player player; // 플레이어 정보 받아오기.
	User user; // 유저 정보 받아오기.
	int Stage; // 스테이지.
	int KilledEnemy = 0; // 죽인 적 숫자.
	int KilledBoss = 0; // 죽인 보스 숫자.
	Thread th; // KeyAdapter 쓰레드.
	boolean checkExit; // JFrame 종료 여부.
//	Music backGroundMusic; // 게임 배경음악.
	boolean checkSkill = true; // 스킬 쿨타임을 재는 변수.
	long leftTime = 0;
	long currenttime = 0;
	
	
	
	// 이미지를 불러오는 역할 , 더블 버퍼.
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image buffImg;
	Graphics buffG;

	Image[] enemyExplosionImg; // 폭발 사진 담아두는 배열.
	Image[] BenemyExplosionImg; // 보스 폭발 사진 담아두는 배열.
	
	Image background = tk.getImage("src/main/SOURCE/background.png"); // 배경화면;
	
	
	ArrayList<Bullet> Bullets = new ArrayList<Bullet>(); // 총알들을 담을 리스트.
	ArrayList<Enemy> Enemys = new ArrayList<Enemy>(); // 적들을 담을 리스트.
	ArrayList<Ebullet> Ebullets = new ArrayList<Ebullet>(); // 적들의 총알을 담을 리스트.
	ArrayList<Boss> Bosses = new ArrayList<Boss>(); // 적들의 객체를 담을 리스트.
	ArrayList<Effect> Effects = new ArrayList<Effect>(); // 적 비행기 폭발 이팩트.
	ArrayList<BEffect> BEffects = new ArrayList<BEffect>(); // 적 보스 비행기 폭발 이팩트. ( 이미지 사이즈가 달라서 따로 저장. )
	
	
	
	public GameObject(Player player, int Stage, User user) {
		this.player = player; // 플레이어 정보 받아오기.
		this.user = user; // 유저 정보 받아오기
		this.Stage = Stage; // 현재 스테이지
		this.checkExit = false;
//		backGroundMusic = new Music("SOURCE/gamebackground.wav"); // 배경음악 객체 생성.
//		Thread backMusicTh = new Thread(backGroundMusic); // 게임 배경음악 스레드 선언.
//		backMusicTh.start(); // 게임 배경음악 실행.
		
		
		// 플레이어 키 입력 스레드.
		KeyControl key = new KeyControl(player,this);
		th = new Thread(key);
		th.start();
		
		// 폭발용 이미지 저장.
		enemyExplosionImg = new Image[5];
		enemyExplosionImg[0] = tk.getImage("src/main/SOURCE/explosion1.png");
		enemyExplosionImg[1] = tk.getImage("src/main/SOURCE/explosion2.png");
		enemyExplosionImg[2] = tk.getImage("src/main/SOURCE/explosion3.png");
		enemyExplosionImg[3] = tk.getImage("src/main/SOURCE/explosion4.png");
		enemyExplosionImg[4] = tk.getImage("src/main/SOURCE/explosion5.png");
		
		// 보스 폭발용 이미지 저장.
		BenemyExplosionImg = new Image[5];
		BenemyExplosionImg[0] = tk.getImage("src/main/SOURCE/explosion1.png");
		BenemyExplosionImg[1] = tk.getImage("src/main/SOURCE/explosion2.png");
		BenemyExplosionImg[2] = tk.getImage("src/main/SOURCE/explosion3.png");
		BenemyExplosionImg[3] = tk.getImage("src/main/SOURCE/explosion4.png");
		BenemyExplosionImg[4] = tk.getImage("src/main/SOURCE/explosion5.png");

		// 적 몬스터 생성.
		if(Stage == 1) { // 첫번째 스테이지 몬스터.
			Enemy enemy1 = new MovingEnemy(730,250);
			Enemy enemy2 = new MovingEnemy(730,100);
			Enemy enemy3 = new MovingEnemy(730,400);
			Boss Boss1_1 = new Boss1(700,150);
			Boss Boss1_2 = new Boss1(700,350);
			Enemys.add(enemy1);
			Enemys.add(enemy2);
			Enemys.add(enemy3);
			Bosses.add(Boss1_1);
			Bosses.add(Boss1_2);
		}
		else if( Stage == 2) { // 두번째 스테이지 몬스터.
			Enemy enemy1 = new MovingEnemy(730,200);
			Enemy enemy2 = new MovingEnemy(730,100);
			Enemy enemy3 = new MovingEnemy(730,300);
			Enemy enemy4 = new MovingEnemy(730,400);
			Boss Boss2_1 = new Boss2(700,150);
			Boss Boss2_2 = new Boss2(700,350);
			Enemys.add(enemy1);
			Enemys.add(enemy2);
			Enemys.add(enemy3);
			Bosses.add(Boss2_1);
			Bosses.add(Boss2_2);
		}
		else if( Stage == 3) {
			Enemy enemy1 = new MovingEnemy(730, 250);
			Enemy enemy2 = new MovingEnemy(730, 100);
			Enemy enemy3 = new MovingEnemy(730, 400);
			Boss Boss2_1 = new Boss2(700,100);
			Boss Boss2_2 = new Boss2(700,300);
			Boss Boss2_3 = new Boss2(700,500);
			Enemys.add(enemy1);
			Enemys.add(enemy2);
			Enemys.add(enemy3);
			Bosses.add(Boss2_1);
			Bosses.add(Boss2_2);
			Bosses.add(Boss2_3);
		}
		
		
		// Frame 설정.
		addKeyListener(key);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("2019 World War");
		setResizable(false);
		setSize(frameWidth,frameHeight);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void paint(Graphics g) {
		repaint();
		buffImg = createImage(getWidth(),getHeight());
		buffG = buffImg.getGraphics();
		update(g);
		if(this.player.HP <=0 || (this.Enemys.size() <= 0 && this.Bosses.size() <= 0))
			return;
	}
	
	public void update(Graphics g) {
		drawBackGround(g);
		drawPlayer(g);
		drawStatus(g);
		drawBullet(g);
		drawEnemy(g);
		drawBoss(g);
		drawExplosion(g);
		currenttime = System.currentTimeMillis()/1000;
		if(currenttime - KeyControl.skillTime >= 4) {
			this.checkSkill = true;
		}	
//		System.out.println("현재시간 : "+currenttime);
//		System.out.println("스킬시간 : "+KeyControl.skillTime);
		g.drawImage(buffImg,0,0,this);	
	}
	
	
	int count = 0;
	// 배경 그리기.
	public void drawBackGround(Graphics g) {
		buffG.clearRect(0, 0, frameWidth, frameHeight);
		if(count > -780) {
			buffG.drawImage(background,count, 0, this);
			count--;
		}
		else
			count = 0;
	}
	
	// 현재 게임 상태 창.
	public void drawStatus(Graphics g) {
		buffG.drawString("Player HP : "+this.player.HP, 700, 50);
		if(this.player.user.장착중인아이템 != null)
			buffG.drawString("Item : "+this.player.user.장착중인아이템.name, 700, 70);
		else if(this.player.user.장착중인아이템 == null)
			buffG.drawString("Item : "+ "null", 700, 70);
		for(int i = 0; i < Bosses.size(); i++) {
			buffG.drawString("Boss HP : "+Bosses.get(i).HP, 700, i*20+90);
		}
		if(this.checkSkill == true) {
			buffG.drawString("SKILL ACTIVE", 360, 560);
		}
	}
	
	
	// 플레이어 비행기에 대한 출력.
	public void drawPlayer(Graphics g) {
		buffG.drawImage(this.player.img,this.player.posX,this.player.posY, this);
		
		// 플레이어의 피가0이 되면 모두 종료.
		if(this.player.HP <= 0) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {e.printStackTrace();}
//			this.backGroundMusic.stop();
			
			for (int k = 0; k < 60; k++) System.out.println();
			System.out.println(" ------------------------------------------------------------------------------- ");
			System.out.println("   _____ _______       _____ ______   ______      _____ _      ______ _____");  
			System.out.println("  / ____|__   __|/\\   / ____|  ____| |  ____/\\   |_   _| |    |  ____|  __ \\"); 
			System.out.println(" | (___    | |  /  \\ | |  __| |__    | |__ /  \\    | | | |    | |__  | |  | |");
			System.out.println("  \\___ \\   | | / /\\ \\| | |_ |  __|   |  __/ /\\ \\   | | | |    |  __| | |  | |");
			System.out.println("  ____) |  | |/ ____ \\ |__| | |____  | | / ____ \\ _| |_| |____| |____| |__| |");
			System.out.println(" |_____/   |_/_/    \\_\\_____|______| |_|/_/    \\_\\_____|______|______|_____/\n"); 
			System.out.println(" ------------------------------------------------------------------------------- ");                                                                  
			ETC.printOneByOne("                                  ◆ 제거한 적 : "+this.KilledEnemy+"\n\n"
					+"                                  ◆ 제거한 보스 : "+this.KilledBoss+"\n\n"
					+"                                  ◆ 남은 HP  : "+this.player.HP+"\n\n"
					+"                                  ◆ 최종 점수 : "+(this.KilledEnemy*10 + this.KilledBoss*30)*this.player.HP+"\n\n"
					+"                                  ◆ 획득한 돈 : "+(this.KilledEnemy*10 + this.KilledBoss*30)*this.player.HP+"\n\n",30);
			System.out.println("                                  [ 엔터키를 눌러주세요. ]");
			ETC.sc.nextLine();                                            
			
			this.player.user.userMoney += (this.KilledEnemy*10 + this.KilledBoss*30)*this.player.HP;
			
			// 적 리스트 초기화.
			Enemys = new ArrayList<Enemy>();
			Bullets = new ArrayList<Bullet>();
			Ebullets = new ArrayList<Ebullet>();
			Bosses = new ArrayList<Boss>();
			
			// Frame 없애기.
			this.checkExit = true;
			this.dispose();
			return;
		}
		
		// 적이 모두 죽으면 게임 종료.
		if(Enemys.size() <= 0 && Bosses.size() <= 0) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {e.printStackTrace();}
//			this.backGroundMusic.stop();
			
			for (int k = 0; k < 60; k++) System.out.println();
			System.out.println(" ------------------------------------------------------------------------------- ");
			System.out.println("     _____ _______       _____ ______    _____ _      ______          _____  ");
			System.out.println("    / ____|__   __|/\\   / ____|  ____|  / ____| |    |  ____|   /\\   |  __ \\ ");
			System.out.println("   | (___    | |  /  \\ | |  __| |__    | |    | |    | |__     /  \\  | |__) |");
			System.out.println("    \\___ \\   | | / /\\ \\| | |_ |  __|   | |    | |    |  __|   / /\\ \\ |  _  / ");
			System.out.println("    ____) |  | |/ ____ \\ |__| | |____  | |____| |____| |____ / ____ \\| | \\ \\ ");
			System.out.println("   |_____/   |_/_/    \\_\\_____|______|  \\_____|______|______/_/    \\_\\_|  \\_\\\n");
			System.out.println(" ------------------------------------------------------------------------------- ");
			if(this.Stage == 1)
				System.out.println("                               첫번째 공격을 막아냈습니다!");
			else if(this.Stage == 2)
				System.out.println("                               두번째 공격을 막아냈습니다!");
			
			
			ETC.printOneByOne("                                  ◆ 제거한 적 : "+this.KilledEnemy+"\n\n"
					+"                                  ◆ 제거한 보스 : "+this.KilledBoss+"\n\n"
					+"                                  ◆ 남은 HP  : "+this.player.HP+"\n\n"
					+"                                  ◆ 최종 점수 : "+(this.KilledEnemy*10 + this.KilledBoss*30)*this.player.HP+"\n\n"
					+"                                  ◆ 획득한 돈 : "+(this.KilledEnemy*10 + this.KilledBoss*30)*this.player.HP+"\n\n",30);
			System.out.println("                                  [ 엔터키를 눌러주세요. ]");
			ETC.sc.nextLine();
			this.player.user.userMoney += (this.KilledEnemy*10 + this.KilledBoss*30)*this.player.HP;
			
			if(this.Stage == 3) {
				
				System.out.println(" ------------------------------------------------------------------------------- ");
				System.out.println("            _______   _    _   ______    ______   _   _   _____");  
				System.out.println("           |__   __| | |  | | |  ____|  |  ____| | \\ | | |  __ \\"); 
				System.out.println("              | |    | |__| | | |__     | |__    |  \\| | | |  | |");
				System.out.println("              | |    |  __  | |  __|    |  __|   | . ` | | |  | |");
				System.out.println("              | |    | |  | | | |____   | |____  | |\\  | | |__| |");
				System.out.println("              |_|    |_|  |_| |______|  |______| |_| \\_| |_____/"); 
				System.out.println(" ------------------------------------------------------------------------------- ");
				ETC.printOneByOne("            모든 적들로 부터 대한민국의 영토를 지켰습니다. 대한민국은 이제 안전한 나라입니다.\n"
						        + "                         Made by KIM.", 80);
			}
			
			if(this.Stage == 1) { // 스테이지 1 클리어 시.
				this.user.stage1 = true;
			}
			else if(this.Stage == 2) { // 스테이지 2 클리어 시.
				this.user.stage2 = true;
			}
			
			// 적 리스트 초기화.
			Enemys = new ArrayList<Enemy>();
			Bullets = new ArrayList<Bullet>();
			Ebullets = new ArrayList<Ebullet>();
			Bosses = new ArrayList<Boss>();
			
			// Frame 없애기.
			this.checkExit = true;
			this.dispose();
			
		}
	} // drawPlayer 끝.
	
	// 플레이어와 적에 대한 총알 출력.
	public void drawBullet(Graphics g) {
		// 플레이어 총알 출력.
		for(int i = 0 ; i < Bullets.size(); i++) {
			Bullet bullet = Bullets.get(i);
			buffG.drawImage(bullet.img,bullet.posX+50,bullet.posY,this);
			bullet.move();
			if(bullet.posX > 800) // 만약 총알이 화면에서 나가면 삭제.
				Bullets.remove(i);
		}
		
		// 적 총알 출력.
		for(int j = 0; j < Ebullets.size(); j++) {
			buffG.drawImage(Ebullets.get(j).img, Ebullets.get(j).posX,Ebullets.get(j).posY, this);
			Ebullets.get(j).move();
			if(Ebullets.get(j).posX <= 0) // 만약 총알이 화면에서 나가면 삭제.
				Ebullets.remove(j);
		}
	} // drawBullet 끝.
	
	// 적 비행기 출력.
	public void drawEnemy(Graphics g) {
		for(int i = 0; i < Enemys.size(); i++) {
			buffG.drawImage(Enemys.get(i).img,Enemys.get(i).posX,Enemys.get(i).posY, this);
			Enemys.get(i).move();
		}
	} //drawEnemy 끝.
	
	// 적 보스 출력.
	public void drawBoss(Graphics g) {
		for(int i = 0; i < Bosses.size(); i++) {
			buffG.drawImage(Bosses.get(i).img, Bosses.get(i).posX, Bosses.get(i).posY,this);
			Bosses.get(i).move();
		}
	}

	// 폭발 이팩트 발동.
	public void drawExplosion(Graphics g) {
		
		// 적 일반 비행기 폭발시 발동하는 임팩트 효과.
		for(int i = 0; i < Effects.size(); i++) {
			if(this.Effects.get(i).cnt <= 8 )
				buffG.drawImage(this.enemyExplosionImg[0], this.Effects.get(i).posX,this.Effects.get(i).posY , this);
			else if(this.Effects.get(i).cnt <= 16 )
				buffG.drawImage(this.enemyExplosionImg[1], this.Effects.get(i).posX,this.Effects.get(i).posY , this);
			else if(this.Effects.get(i).cnt <= 24 )
				buffG.drawImage(this.enemyExplosionImg[2], this.Effects.get(i).posX,this.Effects.get(i).posY , this);
			else if(this.Effects.get(i).cnt <= 32 )
				buffG.drawImage(this.enemyExplosionImg[3], this.Effects.get(i).posX,this.Effects.get(i).posY , this);
			else if(this.Effects.get(i).cnt <= 40 )
				buffG.drawImage(this.enemyExplosionImg[4], this.Effects.get(i).posX,this.Effects.get(i).posY , this);
			this.Effects.get(i).show();
			
			if(this.Effects.get(i).cnt == 40)
				Effects.remove(i);
		}
		
		// 보스 비행기 폭발시 발동하는 임팩트 효과.
		for(int i = 0; i < BEffects.size(); i++) {
			if(this.BEffects.get(i).cnt <= 8)
				buffG.drawImage(this.BenemyExplosionImg[0], this.BEffects.get(i).posX,this.BEffects.get(i).posY , this);
			else if(this.BEffects.get(i).cnt <= 16)
				buffG.drawImage(this.BenemyExplosionImg[1], this.BEffects.get(i).posX,this.BEffects.get(i).posY , this);
			else if(this.BEffects.get(i).cnt <= 24)
				buffG.drawImage(this.BenemyExplosionImg[2], this.BEffects.get(i).posX,this.BEffects.get(i).posY , this);
			else if(this.BEffects.get(i).cnt <= 32)
				buffG.drawImage(this.BenemyExplosionImg[3], this.BEffects.get(i).posX,this.BEffects.get(i).posY , this);
			else if(this.BEffects.get(i).cnt <= 40)
				buffG.drawImage(this.BenemyExplosionImg[4], this.BEffects.get(i).posX,this.BEffects.get(i).posY , this);
			this.BEffects.get(i).show();
			
			if(this.BEffects.get(i).cnt == 40)
				BEffects.remove(i);
		}
	}
	
	@Override
	public void run() {
		// 게임 진행시 main스레드를 join으로 묶어 두고 있기 위함.
		while(true) {
//			if(this.player.HP <=0 || Enemys.size() <= 0 && Bosses.size() <= 0)
//				break;
//			else {
//				System.out.print("");
//				continue;
//			}
			if(this.checkExit == true)
				break;
			else {
				System.out.print("");
				continue;
			}
		}
	}
	
}
