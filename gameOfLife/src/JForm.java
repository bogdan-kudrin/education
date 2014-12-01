
public class JForm {
	public void openform () throws InterruptedException {
		JFrame1 frame1 = new JFrame1();
		frame1.openframe1();
		while  (frame1.Getheight() == 0){
			Thread.sleep(500);
		}
		JFrame2 frame2 = new JFrame2();
		frame2.openframe2(frame1.Getwidt (), frame1.Getheight ());
		
	}

}
