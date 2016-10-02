package recieving;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Remote_Reciever {
public static void main(String[] args) throws AWTException{
		try{
			Robot robot = new Robot();
			ServerSocket serversocket = new ServerSocket(4444);
			Socket client = serversocket.accept();
			System.out.println(client.getInetAddress());
			while(true)
			{
				DataInputStream dis = new DataInputStream(client.getInputStream());
				String message = dis.readUTF();
				String[] message_parts = message.split(";");
				String part1 = message_parts[0];
				String part2 = message_parts[1];
				if(part1.equals("up"))
				{
					robot.keyPress(KeyEvent.VK_UP);
					robot.keyRelease(KeyEvent.VK_UP);
				}
				else if(part1.equals("down"))
				{
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
				}
				else if(part1.equals("open_pp"))
				{
					File file = new File("C:\\Users\\Nitish\\Desktop" +"\\"+ part2+".pptx");
					if(file.exists())
					{
						Desktop.getDesktop().open(new File("C:\\Users\\Nitish\\Desktop" +"\\"+ part2+".pptx"));
					}
					else 
					{
						Desktop.getDesktop().open(new File("C:\\Users\\Nitish\\Desktop" +"\\"+ part2+".ppt"));
					}
					
				}
				else if(part1.equals("slideshow")){
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_F5);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					robot.keyRelease(KeyEvent.VK_F5);
				}
				else if(part1.equals("exitslide"))
				{
					robot.keyPress(KeyEvent.VK_ESCAPE);
				}
				else if(part1.equals("openfile"))
				{
					Desktop.getDesktop().open(new File(part2));
				}
				else if(part1.equals("exit"))
				{
					System.exit(0);
				}
				}
		}
		catch(IOException ioe)
		{
			System.out.println(ioe.getMessage());
		}
	}
}
