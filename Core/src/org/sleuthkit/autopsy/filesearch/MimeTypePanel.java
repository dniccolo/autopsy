/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sleuthkit.autopsy.filesearch;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.logging.Level;
import org.sleuthkit.autopsy.modules.filetypeid.FileTypeDetector;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeTypes;
import org.sleuthkit.autopsy.coreutils.Logger;

/**
 *
 * @author oliver
 */
public class MimeTypePanel extends javax.swing.JPanel {

    private static final SortedSet<MediaType> mediaTypes = MimeTypes.getDefaultMimeTypes().getMediaTypeRegistry().getTypes();
    private static final Logger logger = Logger.getLogger(MimeTypePanel.class.getName());
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form MimeTypePanel
     */
    public MimeTypePanel() {
        initComponents();
    }

    private String[] getMimeTypeArray() {
        Set<String> fileTypesCollated = new HashSet<>();
        for (MediaType mediaType : mediaTypes) {
            fileTypesCollated.add(mediaType.toString());
        }

        FileTypeDetector fileTypeDetector;
        try {
            fileTypeDetector = new FileTypeDetector();
            List<String> userDefinedFileTypes = fileTypeDetector.getUserDefinedTypes();
            fileTypesCollated.addAll(userDefinedFileTypes);

        } catch (FileTypeDetector.FileTypeDetectorInitException ex) {
            logger.log(Level.SEVERE, "Unable to get user defined file types", ex);
        }

        List<String> toSort = new ArrayList<>(fileTypesCollated);
        toSort.sort((String string1, String string2) -> {
            int result = String.CASE_INSENSITIVE_ORDER.compare(string1, string2);
            if (result == 0) {
                result = string1.compareTo(string2);
            }
            return result;
        });
        String[] mimeTypeArray = new String[toSort.size()];
        return toSort.toArray(mimeTypeArray);
    }

    List<String> getMimeTypesSelected() {
        return this.jList1.getSelectedValuesList();
    }

    boolean isSelected() {
        return this.jCheckBox1.isSelected();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<String>();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(150, 150));
        setPreferredSize(new java.awt.Dimension(100, 100));

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = getMimeTypeArray();
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setMinimumSize(new java.awt.Dimension(0, 200));
        jScrollPane1.setViewportView(jList1);

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox1, org.openide.util.NbBundle.getMessage(MimeTypePanel.class, "MimeTypePanel.jCheckBox1.text")); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(MimeTypePanel.class, "MimeTypePanel.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jCheckBox1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
