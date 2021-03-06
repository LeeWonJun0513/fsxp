// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwaySignData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.dialog.TaxiwaySignWizardDialog;
import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.model.TaxiwaySignModel;
import com.zbluesoftware.fsxp.ui.WindowBorder;
import com.zbluesoftware.fsxp.ui.component.TaxiwaySignDisplay;
import com.zbluesoftware.fsxp.util.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Referenced classes of package com.zbluesoftware.fsxp.ui.data:
//            ObjectData

public class TaxiwaySignData extends ObjectData
    implements ActionListener, ChangeListener, FocusListener, PropertyChangeListener
{

    public TaxiwaySignData()
    {
        listeners = new Vector();
        setLayout(new BorderLayout(2, 2));
        lockingButton = new JButton(IconFactory.getInstance().getIcon("padlockUnlocked"));
        lockingButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        lockingButton.setToolTipText("Lock/Unlock Taxiway Signs");
        lockingButton.addActionListener(this);
        if(LockingEngine.getInstance().getTaxiwaySignsLocked())
            lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
        deleteButton = new JButton(IconFactory.getInstance().getIcon("delete"));
        deleteButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        deleteButton.setToolTipText("Delete");
        deleteButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(2));
        buttonPanel.add(lockingButton);
        buttonPanel.add(deleteButton);
        latLabel = new JLabel("Latitude");
        latLabel.setFont(Utilities.LABEL_FONT);
        latLabel.setForeground(Color.black);
        latTF = new PopupTextField(20);
        latTF.setFont(Utilities.TEXT_FIELD_FONT);
        latTF.setForeground(Color.black);
        latTF.addActionListener(this);
        latTF.addFocusListener(this);
        if(LockingEngine.getInstance().getTaxiwaySignsLocked())
            latTF.setBackground(Color.red);
        lonLabel = new JLabel("Longitude");
        lonLabel.setFont(Utilities.LABEL_FONT);
        lonLabel.setForeground(Color.black);
        lonTF = new PopupTextField(20);
        lonTF.setFont(Utilities.TEXT_FIELD_FONT);
        lonTF.setForeground(Color.black);
        lonTF.addActionListener(this);
        lonTF.addFocusListener(this);
        if(LockingEngine.getInstance().getTaxiwaySignsLocked())
            lonTF.setBackground(Color.red);
        headingLabel = new JLabel("Heading");
        headingLabel.setFont(Utilities.LABEL_FONT);
        headingLabel.setForeground(Color.black);
        headingTF = new PopupTextField(5);
        headingTF.setFont(Utilities.TEXT_FIELD_FONT);
        headingTF.setForeground(Color.black);
        headingTF.addActionListener(this);
        headingTF.addFocusListener(this);
        headingSlider = new JSlider(0);
        headingSlider.setModel(new DefaultBoundedRangeModel(0, 1, 0, 360));
        headingSlider.setPreferredSize(new Dimension(100, headingSlider.getPreferredSize().height));
        headingSlider.addChangeListener(this);
        JPanel headingPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(headingPanel, headingTF, 0, 0, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(headingPanel, headingSlider, 1, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        labelLabel = new JLabel("Label");
        labelLabel.setFont(Utilities.LABEL_FONT);
        labelLabel.setForeground(Color.black);
        labelTF = new PopupTextField(5);
        labelTF.setFont(Utilities.TEXT_FIELD_FONT);
        labelTF.setForeground(Color.black);
        labelTF.addFocusListener(this);
        labelTF.addActionListener(this);
        wizardButton = new JButton("wizard");
        wizardButton.setFont(Utilities.BUTTON_FONT);
        wizardButton.setForeground(Color.black);
        wizardButton.addActionListener(this);
        JPanel labelPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(labelPanel, labelTF, 0, 0, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(labelPanel, wizardButton, 1, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        justificationLabel = new JLabel("Justification");
        justificationLabel.setFont(Utilities.LABEL_FONT);
        justificationLabel.setForeground(Color.black);
        DefaultComboBoxModel justificationModel = new DefaultComboBoxModel();
        justificationModel.addElement("LEFT");
        justificationModel.addElement("RIGHT");
        justificationComboBox = new JComboBox(justificationModel);
        justificationComboBox.setFont(Utilities.COMBO_BOX_FONT);
        justificationComboBox.setForeground(Color.black);
        justificationComboBox.addActionListener(this);
        sizeLabel = new JLabel("Size");
        sizeLabel.setFont(Utilities.LABEL_FONT);
        sizeLabel.setForeground(Color.black);
        DefaultComboBoxModel sizeModel = new DefaultComboBoxModel();
        sizeModel.addElement("SIZE1");
        sizeModel.addElement("SIZE2");
        sizeModel.addElement("SIZE3");
        sizeModel.addElement("SIZE4");
        sizeModel.addElement("SIZE5");
        sizeComboBox = new JComboBox(sizeModel);
        sizeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        sizeComboBox.setForeground(Color.black);
        sizeComboBox.addActionListener(this);
        taxiwaySignDisplay = new TaxiwaySignDisplay();
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, buttonPanel, 0, 0, 2, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latTF, 1, 1, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, lonLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lonTF, 1, 2, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, headingLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, headingPanel, 1, 3, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, labelLabel, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, labelPanel, 1, 4, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, justificationLabel, 0, 5, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, justificationComboBox, 1, 5, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, sizeLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, sizeComboBox, 1, 6, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, taxiwaySignDisplay, 0, 7, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, Box.createGlue(), 0, 8, 2, 1, 3, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, "Center");
        windowBorder = new WindowBorder("Taxiway Sign Data");
        setBorder(windowBorder);
        LockingEngine.getInstance().addPropertyChangeListener(this);
    }

    public void updateDisplay(BaseModel baseModel)
    {
        TaxiwaySignModel model = null;
        if(baseModel instanceof TaxiwaySignModel)
            model = (TaxiwaySignModel)baseModel;
        if(this.model != null)
            this.model.removePropertyChangeListener(this);
        updateModel();
        this.model = model;
        boolean status = true;
        headingSlider.removeChangeListener(this);
        justificationComboBox.removeActionListener(this);
        sizeComboBox.removeActionListener(this);
        labelTF.removeFocusListener(this);
        labelTF.removeActionListener(this);
        if(model != null)
        {
            latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getLatLon().getLat()));
            lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getLatLon().getLon()));
            headingSlider.setValue((int)model.getHeading());
            headingTF.setText((new StringBuilder()).append("").append(model.getHeading()).toString());
            labelTF.setText(model.getLabel());
            justificationComboBox.setSelectedItem(model.getJustification());
            sizeComboBox.setSelectedItem(model.getSize());
            taxiwaySignDisplay.setLabelText(model.getLabel());
            model.addPropertyChangeListener(this);
        } else
        {
            latTF.setText("");
            lonTF.setText("");
            headingSlider.setValue(0);
            headingTF.setText("");
            labelTF.setText("");
            justificationComboBox.setSelectedIndex(0);
            sizeComboBox.setSelectedIndex(0);
            taxiwaySignDisplay.setLabelText("");
            status = false;
        }
        deleteButton.setEnabled(status);
        lockingButton.setEnabled(status);
        latLabel.setEnabled(status);
        latTF.setEnabled(status);
        lonLabel.setEnabled(status);
        lonTF.setEnabled(status);
        headingLabel.setEnabled(status);
        headingTF.setEnabled(status);
        headingSlider.setEnabled(status);
        labelLabel.setEnabled(status);
        labelTF.setEnabled(status);
        wizardButton.setEnabled(status);
        justificationLabel.setEnabled(status);
        justificationComboBox.setEnabled(status);
        sizeLabel.setEnabled(status);
        sizeComboBox.setEnabled(status);
        headingSlider.addChangeListener(this);
        justificationComboBox.addActionListener(this);
        sizeComboBox.addActionListener(this);
        labelTF.addFocusListener(this);
        labelTF.addActionListener(this);
    }

    public void updateModel()
    {
        if(model != null)
        {
            model.setLabel(labelTF.getText());
            model.setJustification((String)justificationComboBox.getSelectedItem());
            model.setSize((String)sizeComboBox.getSelectedItem());
        }
    }

    private void updateLatitude()
    {
        if(latTF.isPopupDisplayed())
            return;
        double lat = ParseEngine.getInstance().parseLatitude(latTF.getText());
        if(lat == (1.0D / 0.0D))
            lat = model.getLatLon().getLat();
        latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(lat));
        firePropertyChange("update-lat", new Double(model.getLatLon().getLat()), new Double(lat));
        model.setLatLon(new LatLonPoint(lat, model.getLatLon().getLon()));
    }

    private void updateLongitude()
    {
        if(lonTF.isPopupDisplayed())
            return;
        double lon = ParseEngine.getInstance().parseLongitude(lonTF.getText());
        if(lon == (1.0D / 0.0D))
            lon = model.getLatLon().getLon();
        lonTF.setText(DisplayEngine.getInstance().formatObjectLatitude(lon));
        firePropertyChange("update-lon", new Double(model.getLatLon().getLon()), new Double(lon));
        model.setLatLon(new LatLonPoint(model.getLatLon().getLat(), lon));
    }

    private void updateHeading()
    {
        if(headingTF.isPopupDisplayed())
            return;
        float heading = model.getHeading();
        try
        {
            heading = Float.parseFloat(headingTF.getText());
        }
        catch(NumberFormatException nfe)
        {
            heading = model.getHeading();
        }
        if(heading < 0.0F)
            heading = 0.0F;
        else
        if(heading > 359F)
            heading = 359F;
        headingSlider.setValue((int)heading);
        headingTF.setText((new StringBuilder()).append("").append(heading).toString());
        firePropertyChange("update-heading", new Float(model.getHeading()), new Float(headingSlider.getValue()));
        model.setHeading(heading);
    }

    private void delete()
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Taxiway Sign?", "Confirm Delete...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("delete", model, model);
            updateDisplay(null);
            return;
        }
    }

    private void displayTaxiwaySignWizard()
    {
        String label = TaxiwaySignWizardDialog.showDialog(Utilities.MAIN_FRAME, labelTF.getText());
        if(label != null)
        {
            labelTF.setText(label);
            model.setLabel(label);
            taxiwaySignDisplay.setLabelText(label);
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        if(!listeners.contains(listener))
            listeners.add(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        listeners.remove(listener);
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue)
    {
        super.firePropertyChange(propertyName, oldValue, newValue);
        if(listeners == null)
            return;
        Vector list;
        synchronized(this)
        {
            list = (Vector)listeners.clone();
        }
        if(list.size() == 0)
            return;
        PropertyChangeEvent event = new PropertyChangeEvent(this, propertyName, oldValue, newValue);
        for(int i = list.size() - 1; i >= 0; i--)
            ((PropertyChangeListener)list.elementAt(i)).propertyChange(event);

    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == latTF)
            updateLatitude();
        else
        if(event.getSource() == lonTF)
            updateLongitude();
        else
        if(event.getSource() == headingTF)
            updateHeading();
        else
        if(event.getSource() == deleteButton)
            delete();
        else
        if(event.getSource() == lockingButton)
            LockingEngine.getInstance().setTaxiwaySignsLocked(!LockingEngine.getInstance().getTaxiwaySignsLocked());
        else
        if(event.getSource() == justificationComboBox)
            model.setJustification((String)justificationComboBox.getSelectedItem());
        else
        if(event.getSource() == sizeComboBox)
            model.setSize((String)sizeComboBox.getSelectedItem());
        else
        if(event.getSource() == labelTF)
        {
            model.setLabel(labelTF.getText());
            taxiwaySignDisplay.setLabelText(model.getLabel());
        } else
        if(event.getSource() == wizardButton)
            displayTaxiwaySignWizard();
    }

    public void stateChanged(ChangeEvent event)
    {
        if(event.getSource() == headingSlider && model != null)
        {
            headingTF.setText((new StringBuilder()).append("").append(headingSlider.getValue()).toString());
            firePropertyChange("update-heading", new Float(model.getHeading()), new Float(headingSlider.getValue()));
            model.setHeading(headingSlider.getValue());
        }
    }

    public void focusGained(FocusEvent focusevent)
    {
    }

    public void focusLost(FocusEvent event)
    {
        if(event.getSource() == latTF)
            updateLatitude();
        else
        if(event.getSource() == lonTF)
            updateLongitude();
        else
        if(event.getSource() == headingTF)
            updateHeading();
        else
        if(event.getSource() == labelTF && !labelTF.isPopupDisplayed())
        {
            model.setLabel(labelTF.getText());
            taxiwaySignDisplay.setLabelText(model.getLabel());
        }
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() == model)
        {
            if(event.getPropertyName().equals("latLon"))
            {
                latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(((LatLonPoint)event.getNewValue()).getLat()));
                lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(((LatLonPoint)event.getNewValue()).getLon()));
            } else
            if(event.getPropertyName().equals("label"))
            {
                labelTF.setText((String)event.getNewValue());
                taxiwaySignDisplay.setLabelText((String)event.getNewValue());
            } else
            if(event.getPropertyName().equals("justification"))
            {
                justificationComboBox.removeActionListener(this);
                justificationComboBox.setSelectedItem(event.getNewValue());
                justificationComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("size"))
            {
                sizeComboBox.removeActionListener(this);
                sizeComboBox.setSelectedItem(event.getNewValue());
                sizeComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("heading"))
            {
                headingTF.setText((new StringBuilder()).append("").append(((Float)event.getNewValue()).floatValue()).toString());
                headingSlider.removeChangeListener(this);
                headingSlider.setValue(((Float)event.getNewValue()).intValue());
                headingSlider.addChangeListener(this);
            }
        } else
        if((event.getSource() instanceof LockingEngine) && event.getPropertyName().equals("taxiwaySignsLocked"))
        {
            latTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            lonTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            if(((Boolean)event.getNewValue()).booleanValue())
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
            else
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockUnlocked"));
        }
    }

    private TaxiwaySignModel model;
    private Vector listeners;
    private JButton lockingButton;
    private JButton deleteButton;
    private PopupTextField latTF;
    private PopupTextField lonTF;
    private PopupTextField headingTF;
    private JSlider headingSlider;
    private PopupTextField labelTF;
    private JComboBox justificationComboBox;
    private JComboBox sizeComboBox;
    private TaxiwaySignDisplay taxiwaySignDisplay;
    private JButton wizardButton;
    private JLabel latLabel;
    private JLabel lonLabel;
    private JLabel headingLabel;
    private JLabel labelLabel;
    private JLabel justificationLabel;
    private JLabel sizeLabel;
}
