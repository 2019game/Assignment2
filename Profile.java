import java.io.*;

public class Save{
	
}


public class Profile{
	String fileSeparator = System.getProperty("file.separator");
	String nameOfProfile;
	public int numOfSaves;
	public String[] Saves;
//==========================variables==============================
	public int achivements;
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
//=========================================================================================
				save();
				return true;
			}catch(Exception e){}
		}
		return false;
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