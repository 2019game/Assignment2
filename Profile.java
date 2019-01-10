import java.io.*;

public class Save{
	
}


public class Profile{
	String fileSeparator = System.getProperty("file.separator");
	public String nameOfProfile;
	public int numOfSaves;
	public String[] Saves;
	public numberOfProfiles;
//==========================variables==============================
	public int achivements;
	int width;//variable to store width of window to adjust the window size.
	int height;//variable to store height of window to adjust the window size.
	boolean audio;//variable that defines whether or not sound will play.
//=================================================================
	Profile(){
		System.out.printf("constructor\n");
	}
	public static void main(String args[]) {
		System.out.printf("Hello World!\n");
		Profile p = new Profile();
		p.createProfile("tester");
		//p.load("test");
		System.out.printf(""+p.achivements);
		p.achivements = 50;
		p.save();
		//Profile t = new Profile();
		//t.load("test");
		//System.out.printf(""+t.achivements);
		System.out.printf("Hello World!\n");
	}
	public boolean createProfile(String Name){
		this.save();
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
				numberOfProfiles+=1;
//=================================defualt values==========================================
				achivements=0;
				int width=500;//variable to store width of window to adjust the window size.
				int height=500;//variable to store height of window to adjust the window size.
				boolean audio=true;//variable that defines whether or not sound will play.
//=========================================================================================
				save();
				File f = new File( System.getProperty("user.dir")+fileSeparator+"saves"+fileSeparator+nameOfProfile+fileSeparator+nameOfProfile+".prf");
				FileOutputStream outStream = null;
				BufferedOutputStream bos = null;
				try{
					outStream = new FileOutputStream(f);
					bos = new BufferedOutputStream(outStream);
					bos.write(numberOfProfiles);
					for(int i=0;i<numberOfProfiles;i++){
						bos.write(this.listOfProfiles()[i].getBytes());
						bos.write(0);
					}
					bos.close();
				}
				catch(Exception e) {
					// if any I/O error occurs
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
		File f = new File( System.getProperty("user.dir")+fileSeparator+"saves"+fileSeparator+"p.p");
		InputStream inStream = null;
		BufferedInputStream bis = null;
		try {
			// open input stream test.txt for reading purpose.
			inStream = new FileInputStream(f);
			// input stream is converted to buffered input stream
			bis = new BufferedInputStream(inStream);			
			// read until a single byte is available
			numberOfProfiles=bis.read();
			String[numberOfProfiles] parray = new String[numberOfProfiles];
			for(int i=0;i<numberOfProfiles;i++){
				int j=0;
				char c=bis.read();
				while(c!=0){
					parray[i][j]=c;
					c=bis.read();
					j++;
				}
			}
			return parray;
		}
		catch(Exception e) {
			// if any I/O error occurs
			e.printStackTrace();
		}
		finally {try{
			// releases any system resources associated with the stream
			if(inStream!=null) inStream.close();
			if(bis!=null) bis.close();
			}catch(Exception e){}
		}
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
			hight = (int)bis.read();
			audio = (boolean)bis.read();
//==================================================================			
		}
		catch(Exception e) {
			// if any I/O error occurs
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
			bos.write(hight);
			bos.write(audio);
//=====================================================================
			bos.close();
		}
		catch(Exception e) {
			// if any I/O error occurs
			e.printStackTrace();
		}
		finally{try{
			if(outStream!=null) outStream.close();
			if(bos!=null) bos.close();
			}catch(Exception e){}
		}
	}
}