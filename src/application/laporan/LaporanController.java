/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package application.laporan;

import application.tariksimpanan.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LaporanController {
    private static LaporanView view = null;
    private static LaporanController instance = null;
    private static LaporanRepository repos = null;
    private XSSFDataFormat dataFormat;
    
    private static final String FILE_EXTENSION = ".xlsx";
    private static final String FILE_REKAP_POIN_TAHUNAN = "Rekap Poin Tahunan - ";
    private static final String FILE_KARTU_ANGGOTA = "Kartu Anggota - ";
    private static final String FILE_IURAN_ANGGOTA = "Rekap Poin Tahunan.xlsx" + FILE_EXTENSION;
    private static final String CURRENCY_FORMAT = "_-Rp* #.##0_-;-Rp* #.##0_-;_-Rp* \"-\"_-;_-@_-";
    
    
    private LaporanController(){    
        this.view = new LaporanView();
        this.repos = LaporanRepository.getInstance();
    }
    
    public static LaporanController getInstance(){
        if (instance == null){
            instance = new LaporanController();
        }
        
        return instance;
    }
    
    public void index(){
        boolean loop = true;
        while (loop){
            int menu = view.menu();
            switch (menu){
                case 1:
                    this.kartuAnggota();
                    break;
                case 2:
                    this.rekapPoinTahunan();
                    break;
                case 3:
                    this.rekapIuranAnggota();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:{
                    System.out.println("Pilihan tidak tersedia");
                    break;
                }
            }
        }
    }
    
    public void kartuAnggota(){
        
    }
    
    private void createCellMergeAndCenter(Sheet sheet, Row row, int firstRow, int lastRow, int firstCol, int lastCol, String cellValue, int cellNumber){
        //merge
        CellRangeAddress cellRangeAddress = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        sheet.addMergedRegion(cellRangeAddress);
        
        // create cell
        Cell cell = row.createCell(cellNumber);
        
        cell.setCellValue(cellValue);
        
        //center
        CellUtil.setAlignment(cell, HorizontalAlignment.CENTER);
        CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
    }
    
    private void createCellAndCenter(Row row, String cellValue, int cellNumber){
        // create cell
        Cell cell = row.createCell(cellNumber);
        
        cell.setCellValue(cellValue);
        
        //center
        CellUtil.setAlignment(cell, HorizontalAlignment.CENTER);
        CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
    }
    
    private void createCell(Row row, String cellValue, int cellNumber){
        // create cell
        Cell cell = row.createCell(cellNumber);
        
        cell.setCellValue(cellValue);
    }
    
    private void createCellCurrency(XSSFWorkbook workbook, Row row, int cellValue, int cellNumber){
        // create cell
        Cell cell = row.createCell(cellNumber);
        CellStyle style = workbook.createCellStyle();
        style.setDataFormat(dataFormat.getFormat(CURRENCY_FORMAT));
        cell.setCellStyle(style);
        cell.setCellValue(cellValue);
    }
    
    private void createCell(Row row, int cellValue, int cellNumber){
        // create cell
        Cell cell = row.createCell(cellNumber);
        
        cell.setCellValue(cellValue);
    }
    
    public void rekapPoinTahunan(){
        view.alertLoading();
        
        try {
            // create new book
            XSSFWorkbook workbook = new XSSFWorkbook();
            
            //create formatter
            dataFormat = workbook.createDataFormat();
            
            // create new sheet
            Sheet sheet = workbook.createSheet();
            // create row,
            Row row = sheet.createRow(1);
            
            //B2:B3
            createCellMergeAndCenter(sheet, row, 1, 2, 1, 1, "No.", 1);
            //C2:C3
            createCellMergeAndCenter(sheet, row, 1, 2, 2, 2, "ID Anggota", 2);
            //D2:D3
            createCellMergeAndCenter(sheet, row, 1, 2, 3, 3, "Nama", 3);
            //E2:G2
            createCellMergeAndCenter(sheet, row, 1, 1, 4, 6, "Saldo Simpanan", 4);
            
            row = sheet.createRow(2);
            //E3
            createCellAndCenter(row, "SP + SW", 4);
            //F3
            createCellAndCenter(row, "SS", 5);
            //G3
            createCellAndCenter(row, "Total", 6);
            
            row = sheet.getRow(1);
            //I2:I3
            createCellMergeAndCenter(sheet, row, 1, 2, 8, 8, "Jumlah Poin", 8);
            
            //get data
            List<RekapPoinTahunan> rekap = repos.getRekapPoinTahunan();
            int i = 1;
            for (RekapPoinTahunan rekapPoinTahunan : rekap) {
                row = sheet.createRow(2 + i);
                createCell(row, i, 1);
                createCell(row, rekapPoinTahunan.getId_anggota(), 2);
                createCell(row, rekapPoinTahunan.getNama(), 3);
                createCellCurrency(workbook, row, rekapPoinTahunan.getSimpanan_wajib(), 4);
                createCellCurrency(workbook, row, rekapPoinTahunan.getSimpanan_sukarela(), 5);
                createCellCurrency(workbook, row, rekapPoinTahunan.getTotal(), 6);
                createCell(row, rekapPoinTahunan.getTotalPoin(), 8);
            }
            
            //auto resize
            for (i = 0; i < 10; i++){
                sheet.autoSizeColumn(i, true);
            }
            // create new excel file
            File desktopDir = new File(System.getProperty("user.home"), "Desktop");
            int year = Calendar.getInstance().get(Calendar.YEAR);
            FileOutputStream fileOut = new FileOutputStream(new File(desktopDir, FILE_REKAP_POIN_TAHUNAN + year + FILE_EXTENSION));
            // write  book ke file
            workbook.write(fileOut);
            // close file
            fileOut.close();
            view.alertDataSaved();
        } catch (Exception e) {
            view.alertDataNotSaved();
            
            e.printStackTrace();
        }
    }
    
    public void rekapIuranAnggota(){
        
    }
}
