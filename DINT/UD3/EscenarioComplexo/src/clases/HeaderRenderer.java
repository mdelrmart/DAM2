/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author usuario
 */
public class HeaderRenderer implements TableCellRenderer {
    private final JPanel panel = new JPanel();
    private final JLabel label = new JLabel();

    public HeaderRenderer() {
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(label);     
        panel.setBackground(Color.WHITE);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        label.setText(value.toString());

        ImageIcon icon = null;
        switch (column) {
            case 0:
                icon = new ImageIcon("./src/resources/nome.png");
                break;
            case 1:
                icon = new ImageIcon("./src/resources/dni.png");
                break;
            case 2:
                icon = new ImageIcon("./src/resources/phone.png");
                break;
            case 3:
                icon = new ImageIcon("./src/resources/mail.png");
                break;
            default:
                break;
        }

        label.setIcon(icon);
        
        // Posición del texto respecto al icono
        label.setHorizontalTextPosition(SwingConstants.RIGHT);
        
        return panel;
    }
}
