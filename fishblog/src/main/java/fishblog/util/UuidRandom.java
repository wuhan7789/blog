package fishblog.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

//随机数验证工具类
public class UuidRandom {
	private static UuidRandom uuidRandom;
	private UuidRandom(){
	}
	
	public static UuidRandom getUuidRandom(){
		if(uuidRandom == null) {
			uuidRandom = new UuidRandom();
		}
		return uuidRandom;
	}
	
	public synchronized BufferedImage getUuidImage(HttpServletRequest req) {
		String suijima = null;
		int width = 100;
		int height = 33;
		//去一个uuid随机码取前六位，放入session域
		suijima = UUID.randomUUID().toString().substring(0, 6);
		req.getSession().setAttribute("suijima", suijima);
		//拿到一个画布工具
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//拿到一只画笔
		Graphics graphics = bufferedImage.getGraphics();
		//设置初始背景色，填充一个样式的矩形设置画笔颜色和字体样式
		graphics.setColor(Color.white);
		graphics.fillRect(1, 1, width-2, height-2);//用白色画笔画矩形框、
		//写入随机码
		graphics.setColor(Color.black);
		graphics.setFont(new Font("方正姚体", Font.BOLD, 25));//用黑色画笔写随机码	
		graphics.drawString(suijima, width/6-3, height/2+8);
		//加一些随机的干扰线
		for(int i = 0 ; i < 10 ;i++ ) {
			graphics.drawLine(0, new Random().nextInt(height), width, new Random().nextInt(height));
			graphics.setColor(i/2==0 ? Color.green:Color.blue);
		}
		//销毁画笔
		graphics.dispose();
		return bufferedImage;
		//ImageIO.write(bufferedImage, "jpg", res.getOutputStream());
	}
	
	public synchronized boolean isEqualsUuidImage(String uuid,HttpServletRequest req) {
		 String suijima = (String)req.getSession().getAttribute("suijima");
		 if(suijima != null && !suijima.equals("")) {
				if(suijima.equals(uuid)) {
					 return true;
			     } else {
					 return false;
			     }
			 }else{
		         return false;
	         }
		}
	
	public synchronized void destroyUuidImage(HttpServletRequest req) {
		req.getSession().removeAttribute("suijima");
	}
}
