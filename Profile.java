import java.io.*;
import java.nio.ByteBuffer;
import java.nio.*;

public class Profile{
	String fileSeparator = System.getProperty("file.separator");
	public String nameOfProfile;
	public int numOfSaves;
	public String[] Saves;
	public int numberOfProfiles;
//==========================variables==============================
	public int achivements;
	public int width;//variable to store width of window to adjust the window size.
	public int height;//variable to store height of window to adjust the window size.
	public int score;
	public int playerHP;
	public float volume;
	public boolean audio;//variable that defines whether or not sound will play.
//=================================================================
	public static void main(String args[]) {
		System.out.printf("Hello World!\n");
		Profile p = new Profile();
		//p.createProfile("drumer");
		p.load("drumer");
		//System.out.printf("\n:"+p.listOfProfiles()+":\n");
		
		System.out.printf(""+p.listOfProfiles()[0]);
		if(p.audio){
			System.out.printf("sound");
		}
		else{
			System.out.printf("quite");
		}
		p.volume=0.8f;
		p.audio = false;
		p.save();
		//Profile t = new Profile();
		//t.load("test");
		//System.out.printf(""+t.achivements);
		System.out.printf("Hello World!\n");
	}
	public boolean createProfile(String Name){
		File f = new File( System.getProperty("user.dir")+fileSeparator+"saves"+fileSeparator+Name+fileSeparator);
		if(f.exists()){
			System.out.printf("Profile already exists");
			return false;
		}
		else{
			try{
				f.mkdirs();
				f = new File( System.getProperty("user.dir")+fileSeparator+"saves"+fileSeparator+Name+fileSeparator+Name+".prf");
				f.createNewFile();
				nameOfProfile = Name;
				numOfSaves=0;
//=================================defualt values==========================================
				achivements=0;
				width=500;//variable to store width of window to adjust the window size.
				height=500;//variable to store height of window to adjust the window size.
				score=0;
				volume=1;
				audio=true;//variable that defines whether or not sound will play.
//=========================================================================================
				save();
				String[] parray = this.listOfProfiles();
				f = new File( System.getProperty("user.dir")+fileSeparator+"saves"+fileSeparator+"main.prf");
				FileOutputStream outStream = null;
				BufferedOutputStream bos = null;
				try{
					outStream = new FileOutputStream(f);
					bos = new BufferedOutputStream(outStream);
					bos.write(numberOfProfiles+1);
					for(int i=0;i<numberOfProfiles;i++){
						bos.write(parray[i].getBytes());
						bos.write(0);
					}
					bos.write(nameOfProfile.getBytes());
					bos.write(0);
					bos.close();
				}
				catch(Exception e) {
					// if any I/O error occurs
					System.out.printf("\ncreate exception\n");
					e.printStackTrace();
				}
				finally{try{
					if(outStream!=null) outStream.close();
					if(bos!=null) bos.close();
					}catch(Exception e){}
				}
				return true;
			}catch(Exception e){}
		}
		return false;
	}
	public String[] listOfProfiles(){
		File f = new File( System.getProperty("user.dir")+fileSeparator+"saves"+fileSeparator+"main.prf");
		InputStream inStream = null;
		BufferedInputStream bis = null;
		try {
			// open input stream test.txt for reading purpose.
			inStream = new FileInputStream(f);
			// input stream is converted to buffered input stream
			bis = new BufferedInputStream(inStream);			
			// read until a single byte is available
			numberOfProfiles=bis.read();
			String[] parray = new String[numberOfProfiles];
			for(int i=0;i<numberOfProfiles;i++){
				parray[i]="";
				int c= (int)bis.read();
				while(c!=0){
					parray[i]+=(char)c;
					c=(int)bis.read();
				}
			}
			return parray;
		}
		catch(Exception e) {
			// if any I/O error occurs
			System.out.printf("\nlistOfProfiles exception\n");
			e.printStackTrace();
		}
		finally {try{
			// releases any system resources associated with the stream
			if(inStream!=null) inStream.close();
			if(bis!=null) bis.close();
			}catch(Exception e){}
		}
		return new String[0];
	}
	public void load(String Name){
		nameOfProfile = Name;
		File f = new File( System.getProperty("user.dir")+fileSeparator+"saves"+fileSeparator+Name+fileSeparator+Name+".prf");
		InputStream inStream = null;
		BufferedInputStream bis = null;
		try {
			// open input stream test.txt for reading purpose.
			inStream = new FileInputStream(f);
			// input stream is converted to buffered input stream
			bis = new BufferedInputStream(inStream);			
			// read until a single byte is available
			numOfSaves = (int)bis.read();
			Saves = new String[numOfSaves];
			for(int i=0;i<numOfSaves;i++){
				char c = (char)bis.read();
				while(c!=0){
					Saves[i] = Saves[i] + c;
					c = (char)bis.read();
				}
			}
//===========================load values=======================================
			achivements = (int)bis.read();
			width = (int)bis.read();
			height = (int)bis.read();
			score = (int)bis.read();
			playerHP = (int)bis.read();
			byte[] b = new byte[4];
			bis.read(b,0,4);
			volume = ByteBuffer.wrap(b).getFloat();
			//volume = (float)bis.readFloat();
			if((int)bis.read()==1){audio=true;}else{ audio=false;}
//==================================================================			
		}
		catch(Exception e) {
			// if any I/O error occurs
			System.out.printf("\nload exception\n");
			e.printStackTrace();
		}
		finally {try{
			// releases any system resources associated with the stream
			if(inStream!=null) inStream.close();
			if(bis!=null) bis.close();
			}catch(Exception e){}
		}
	}
	public void save(){
		File f = new File( System.getProperty("user.dir")+fileSeparator+"saves"+fileSeparator+nameOfProfile+fileSeparator+nameOfProfile+".prf");
		FileOutputStream outStream = null;
		BufferedOutputStream bos = null;
		try{
			outStream = new FileOutputStream(f);
			bos = new BufferedOutputStream(outStream);
			bos.write(numOfSaves);
			for(int i=0;i<numOfSaves;i++){
				bos.write(Saves[i].getBytes());
			}
//=================================save values====================================
			bos.write(achivements);
			bos.write(width);
			bos.write(height);
			bos.write(score);
			bos.write(playerHP);
			bos.write( ByteBuffer.allocate(4).putFloat(volume).array() ,0,4);
			if (audio) bos.write(1); else bos.write(0); 
//=====================================================================
			bos.close();
		}
		catch(Exception e) {
			// if any I/O error occurs
			System.out.printf("\nsave exception\n");
			e.printStackTrace();
		}
		finally{try{
			if(outStream!=null) outStream.close();
			if(bos!=null) bos.close();
			}catch(Exception e){}
		}
	}
}