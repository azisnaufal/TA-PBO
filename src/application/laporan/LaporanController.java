/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package application.laporan;

import application.anggota.Anggota;
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
    private static final String FILE_IURAN_ANGGOTA = "Rekap Iuran Anggota - ";
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
        view.alertLoading();
        List<Anggota> anggotas = repos.getDaftarAnggota();
        view.stopLoading();
        Anggota anggota = view.getSelectedAnggota(anggotas);
        int year = view.frmTahun();
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
           
            createCellMergeAndCenter(sheet, row, 1, 1, 1, 2, "ID Anggota", 1);
            createCell(row, anggota.getId_anggota() + " " + anggota.getNama_lengkap(), 3);
            
            row = sheet.createRow(2);
            createCellMergeAndCenter(sheet, row, 2, 2, 1, 2, "Tahun", 1);
            createCell(row, year, 3);
            
            row = sheet.createRow(4);
            createCellMergeAndCenter(sheet, row, 4, 6, 1, 1, "No.", 1);
            createCellMergeAndCenter(sheet, row, 4, 6, 2, 2, "TGL.", 2);
            createCellMergeAndCenter(sheet, row, 4, 4, 3, 6, "MUTASI SIMPANAN", 3);
            
            row = sheet.createRow(5);
            createCellMergeAndCenter(sheet, row, 5, 6, 3, 3, "SP + SW", 3);
            createCellMergeAndCenter(sheet, row, 5, 5, 4, 5, "Simpanan Sukarela", 4);
            
            row = sheet.createRow(6);
            createCellAndCenter(row, "Masuk", 4);
            createCellAndCenter(row, "Keluar", 5);
            
            row = sheet.getRow(5);
            createCellMergeAndCenter(sheet, row, 5, 6, 6, 6, "Total", 6);
            
            row = sheet.getRow(4);
            createCellMergeAndCenter(sheet, row, 4, 4, 7, 9, "SALDO SIMPANAN", 7);
            
            row = sheet.getRow(5);
            createCellMergeAndCenter(sheet, row, 5, 6, 7, 7, "SP + SW", 7);
            createCellMergeAndCenter(sheet, row, 5, 6, 8, 8, "SS", 8);
            createCellMergeAndCenter(sheet, row, 5, 6, 9, 9, "Total", 9);
     
            //get data
            int i = 1;
            row = sheet.createRow(7);
            createCell(row, i, 1);
            if (anggota.getCreated_at() == null){
                createCell(row, "", 2);
            }
            else {
                createCell(row, anggota.getCreated_at().toString(), 2);
            }
            createCellCurrency(workbook, row, 100000, 3);
            createCellCurrency(workbook, row, 100000, 6);
            createCellCurrency(workbook, row, 100000, 7);
            createCellCurrency(workbook, row, 100000, 9);
            i++;
                        
            List<HistorySimpanan> history = repos.getSimpananByIdAnggota(anggota.getId_anggota());
            int saldoSimpananPokokDanWajib = 100000;
            int saldoSimpananSukarela = 0;
            int saldoTotal = 0;
            for (HistorySimpanan historySimpanan : history) {
                row = sheet.createRow(6 + i);
                createCell(row, i, 1);
                createCell(row, historySimpanan.getCreated_at().toString(), 2);
                
                int simpananWajib = historySimpanan.getSimpanan_wajib()*25000;
                saldoSimpananPokokDanWajib += simpananWajib;
                createCellCurrency(workbook, row, simpananWajib, 3);
                
                int simpananSukarela = historySimpanan.getSimpanan_sukarela()*25000;
                saldoSimpananSukarela += simpananSukarela;
                if(simpananSukarela > 0){
                     createCellCurrency(workbook, row, simpananSukarela, 4);
                }
                else {
                     createCellCurrency(workbook, row, simpananSukarela, 5);
                }
                
                createCellCurrency(workbook, row, simpananSukarela + simpananWajib, 6);
                createCellCurrency(workbook, row, saldoSimpananPokokDanWajib, 7);
                createCellCurrency(workbook, row, saldoSimpananSukarela, 8);
                
                saldoTotal = saldoTotal + saldoSimpananPokokDanWajib + saldoSimpananSukarela;
                createCell(row, saldoTotal, 9);
                i++;
            }
            
            
            //auto resize
            for (i = 0; i <= 10; i++){
                sheet.autoSizeColumn(i, true);
            }
            // create new excel file
            File desktopDir = new File(System.getProperty("user.home"), "Desktop");
            FileOutputStream fileOut = new FileOutputStream(new File(desktopDir, FILE_KARTU_ANGGOTA + year + " - " + anggota.getNama_lengkap() + FILE_EXTENSION));
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
        int year = view.frmTahun();
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
            List<RekapPoinTahunan> rekap = repos.getRekapPoinTahunan(year);
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
                i++;
            }
            
            //auto resize
            for (i = 0; i < 10; i++){
                sheet.autoSizeColumn(i, true);
            }
            // create new excel file
            File desktopDir = new File(System.getProperty("user.home"), "Desktop");
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
        int year = view.frmTahun();
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
            
            createCellMergeAndCenter(sheet, row, 1, 3, 1, 1, "No.", 1);
            createCellMergeAndCenter(sheet, row, 1, 3, 2, 2, "ID Anggota", 2);
            createCellMergeAndCenter(sheet, row, 1, 3, 3, 3, "Nama", 3);
            createCellMergeAndCenter(sheet, row, 1, 1, 4, 15, "IURAN SW / BULAN", 4);
            
            row = sheet.createRow(2);
            createCellMergeAndCenter(sheet, row, 2, 3, 4, 4, "JAN", 4);
            createCellMergeAndCenter(sheet, row, 2, 3, 5, 5, "FEB", 5);
            createCellMergeAndCenter(sheet, row, 2, 3, 6, 6, "MAR", 6);
            createCellMergeAndCenter(sheet, row, 2, 3, 7, 7, "APR", 7);
            createCellMergeAndCenter(sheet, row, 2, 3, 8, 8, "MEI", 8);
            createCellMergeAndCenter(sheet, row, 2, 3, 9, 9, "JUN", 9);
            createCellMergeAndCenter(sheet, row, 2, 3, 10, 10, "JUL", 10);
            createCellMergeAndCenter(sheet, row, 2, 3, 11, 11, "AGU", 11);
            createCellMergeAndCenter(sheet, row, 2, 3, 12, 12, "SEP", 12);
            createCellMergeAndCenter(sheet, row, 2, 3, 13, 13, "OKT", 13);
            createCellMergeAndCenter(sheet, row, 2, 3, 14, 14, "NOV", 14);
            createCellMergeAndCenter(sheet, row, 2, 3, 15, 15, "DES", 15);
            
            //get data
            List<RekapIuranAnggota> rekap = repos.getRekapIuranAnggota(year);
            int i = 1;
            for (RekapIuranAnggota rekapIuranAnggota : rekap) {
                row = sheet.createRow(3 + i);
                createCell(row, i, 1);
                createCell(row, rekapIuranAnggota.getId_anggota(), 2);
                createCell(row, rekapIuranAnggota.getNama(), 3);
                int poin = rekapIuranAnggota.getSimpanan_wajib();
                for (int j = 4; j <= 15; j++){
                    if (poin > 0){
                        createCell(row, "V", j);
                        poin--;
                    }
                    else {
                        break;
                    }
                }
                i++;
            }
            
            //auto resize
            for (i = 0; i <= 19; i++){
                sheet.autoSizeColumn(i, true);
            }
            // create new excel file
            File desktopDir = new File(System.getProperty("user.home"), "Desktop");
            FileOutputStream fileOut = new FileOutputStream(new File(desktopDir, FILE_IURAN_ANGGOTA + year + FILE_EXTENSION));
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
}
