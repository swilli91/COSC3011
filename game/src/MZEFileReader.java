import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class MZEFileReader 
{
    public MZEFileReader() throws FileNotFoundException, IOException
    {
        File file = new File("input\\default.mze");
        fileReader(file);
    }
    
   /* public byte[] fileReader(File file) throws FileNotFoundException, IOException
    {
        FileInputStream inFile = new FileInputStream(file);
        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4];
        try {
            for (int readData; (readData = inFile.read(buffer)) != -1;)
            {
                byteOutStream.write(buffer, 0, readData);
                System.out.println("read: " + readData);
            }
        } catch (IOException ex) 
        {
           JOptionPane.showMessageDialog(null, "File Not Found", "ERROR", 
                   JOptionPane.WARNING_MESSAGE); 
           System.exit(0);
        }
        byte[] byteArray = byteOutStream.toByteArray();
        return byteArray;
    }*/
    
    public byte[] fileReader(File file) 
    {
        FileInputStream inFile = null;
        try {
            inFile = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File Not Found", "ERROR", 
                    JOptionPane.WARNING_MESSAGE); 
            System.exit(0);
        }
        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4];
        try {
            for (int readData; (readData = inFile.read(buffer)) != -1;)
            {
                byteOutStream.write(buffer, 0, readData);
                //System.out.println("read: " + readData);
            }
        } catch (IOException ex) 
        {
           JOptionPane.showMessageDialog(null, file + "File Not Found", "ERROR", 
                   JOptionPane.WARNING_MESSAGE); 
           System.exit(0);
        }
      //TODO how to test if this is the correct information in byteArray
        byte[] byteArray = byteOutStream.toByteArray();
        
        
       /* File testFile = new File("input\\testFile.mze");
        FileOutputStream outFile = new FileOutputStream(testFile);
        outFile.write(byteArray);
        outFile.flush();
        outFile.close();*/
        return byteArray;
    }
}
