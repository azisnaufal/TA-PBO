/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.anggota;

import application.mahasiswa.MahasiswaController;

/**
 *
 * @author Farhan Rafly Fahrezi
 */
public class AnggotaController {
    private static AnggotaView view = null;
    private static AnggotaController instance = null;
    private static AnggotaRepository repos = null;
    
    private AnggotaController() {
        this.view = new AnggotaView();
        this.repos = AnggotaRepository.getInstance();
    }
}
