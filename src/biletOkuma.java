import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * Created by cenk.akdeniz on 10.12.2015.
 */
public class biletOkuma {


       private  boolean check =false;
    public List biletOku (String dosyaYolu1 ) throws IOException {
        String bilet , whitelist;
        StringBuffer sonuc =  new StringBuffer();
        List list = new ArrayList();
        try {

            FileInputStream file = new FileInputStream(new File(dosyaYolu1));

            //Get the workbook instance for XLS file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();
            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();

                //For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    switch(cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t\t");
                            list.add(cell.getBooleanCellValue());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t\t");
                            list.add(cell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + "\t\t");
                            list.add(cell.getStringCellValue());
                            break;
                    }

                }
                System.out.println("");
            }System.out.println(String.valueOf(list.size()) );
            file.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  list;
        }

        public List kiyasla (List l1,List l2){
            List list=new ArrayList();
            double ilkliste,ikinciliste;
            for(int i=0; i<l1.size();i++){
                setCheck(false);
            for (int j=0; j<l2.size();j++) {
                ilkliste = (Double) l1.get(i);
                ikinciliste = (Double) l2.get(j);
                //System.out.println(ilkliste);
                //System.out.println(ikinciliste);
                if (ilkliste == ikinciliste) {
                    System.out.println("hold 1 yapıldı");
                    setCheck(true);
                    break;
                }
            }
                if(isCheck()==false){
                    System.out.println("list'e kayıt eklendi");
                    list.add(l1.get(i));
                }
            }
            return list;
        }



    public void biletYaz(String yazilacakDosya, List list)throws  IOException{

       XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sample sheet");

        Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
        for (int i= 0; i<list.size();i++){
            data.put(i,new Object[] {list.get(i)});
        }
        //data.put("1", new Object[] {"Emp No.", "Name", "Salary"});
        //data.put("2", new Object[] {1d, "John", 1500000d});
        //data.put("3", new Object[] {2d, "Sam", 800000d});
        //data.put("4", new Object[] {3d, "Dean", 700000d});

        Set<Integer> keyset = data.keySet();
        int rownum = 0;
        for (Integer key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof Date)
                    cell.setCellValue((Date)obj);
                else if(obj instanceof Boolean)
                    cell.setCellValue((Boolean)obj);
                else if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Double)
                    cell.setCellValue((Double)obj);
            }
        }

        try {
            FileOutputStream out =
                    new FileOutputStream(new File(yazilacakDosya));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
