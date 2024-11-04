/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package EJ4_A2UD1;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author Miguel del Rio
 */
public class Filtro implements FilenameFilter {
    String extension;
    
    Filtro (String extension) {
        this.extension = extension;
    }
    
    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extension);
    }
}
