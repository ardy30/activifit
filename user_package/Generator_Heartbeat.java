package user_package;

import java.util.concurrent.ThreadLocalRandom;

public class Generator_Heartbeat extends Generator {

	//Heart-beat States 
	// "Too Fast" 
	private final int TACHYCARDIA = 100; //over 100 at rest
	// "Too Slow"
	private final int BRADYCARDIA = 60; //less than
	// "Normal" and Healthy heart beat 60-90
	private final int REST_MIN = 60;
	private final int REST_MAX = 90; 
	
	//User Variables
	private int maxHR; // During exercise
	private int dailyTargetHR; // Training or Target HR
	private int dailyRestBPM; // Current bpm at rest

	// Constructor
	public Generator_Heartbeat(User user) {
		super(user);
		this.maxHR = getMaxHR(user.getAge());
		this.dailyTargetHR = (int) Math.round(getTargetHR(maxHR));
		this.dailyRestBPM = getRestHR();
	}

	/* Haskell and Fox Method.
	 * Gets the max heart beat according to the age */
	private int getMaxHR(int age) {
		// Right now it does not differentiate between male and female
		// So it will grab the median. 226 woman 220 men.
		return 223 - age;
	}// end method

	/* Get random rest rate */
	private int getRestHR(){
		return ThreadLocalRandom.current().nextInt(REST_MIN, REST_MAX);
	}
	
	/* Get the 50% - 85% of MaxHR */
	private double getTargetHR(int maxHeartRate) {
		double random = ThreadLocalRandom.current().nextDouble(.50, .85);
		return random * maxHeartRate;
	}
	
	/* Get current BPM depending on the active id */
	private int getRandomCurrentHR(){
		//HECHALE MAS MATES
		double act = getRestHR() * (user.getActId() *0.25); 
		int h = (int) Math.round(act);
		return h;
	}
	
	/* Returns the condition of the current heartbeat
	 * @Param in bpm at rest */
	private String getRestHeartStatus(int bpm){
		//Note that this gives the status at rest
		if(bpm<= BRADYCARDIA) 
			return "BRADYCARDIA"; //<60 bpm
		else if(bpm > REST_MIN && bpm <= REST_MAX)
			return "NORMAL";  //61 to 90
		else if(bpm > REST_MAX && bpm < TACHYCARDIA)
			return "ABOVE AVERAGE"; //91 to 99
		else
			return "TACHYCARDIA"; // <100
	}
	
	/* Print detail information about today's temperature */
	public void printDetailHeartbeat(){
		System.out.println("Today's Detail Activity Log ");
		System.out.println("Time|Heartrate|Status");
		System.out.println("---------------------");
		for (int i = 0; i <= 24-0; i++) {
			int t = 0+i;
			int hr = getRandomCurrentHR();
			String s = getRestHeartStatus(hr);
			if (t < 10)					
				System.out.printf("0%dam|   %d bpm|%s\n", (t), hr, s);
			else if (t < 12)
				System.out.printf("%dam|    %d bpm|%s\n", (t), hr, s);
			else
				System.out.printf("%dpm|   %d bpm|%s\n", (t), hr, s);
		}
		System.out.println("-----------------------");
		System.out.println("End of Heartbeat report");
	}
	
	
	/* See how 'random' random really is */
	public void testRandomness(){
		int i = 0;
		while(i<20){
			int h = (int) Math.round(getTargetHR(maxHR));
			int r = getRestHR();
			System.out.println("AGE:"+user.getAge()+" HR Target:"+h+" HR Rest: "+r);
			i++;
		}
	}
	/**
	 * Extra information 
	 * 
	 * The following can and will be related to the activity index that will
	 * modulate and change according to the user.
	 * 
	 * Levels of Exercises = Benefits -> Max HR (%)
	 * Light Exercise = Maintenance for healthy heart 50% - 60%
	 * Weight Loss = Burn fat and calories  60% - 70%
	 * Base - Aerobic = Increase endurance and stamina 70% - 80%
	 * Conditioning = Fitness conditioning, athletic training and muscle building  80% - 90%
	 * Athletic - Elite = Athletic training and endurance 90% - 100%
	 */
}
