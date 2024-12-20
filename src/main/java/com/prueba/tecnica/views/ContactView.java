package com.prueba.tecnica.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.prueba.tecnica.models.Contact;
import com.prueba.tecnica.services.IContactService;

@Component
public class ContactView extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JButton addContactButton, editContactButton;
    private IContactService contactService;

    @Autowired
    public ContactView(IContactService contactService) {
        this.contactService = contactService;

        setTitle("Gestión de Contactos");
        setSize(new Dimension(600, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        customizeTitleBar();

        model = new DefaultTableModel();
        table = new JTable(model);

        // Configuración de las columnas de la tabla
        model.addColumn("ID");       
        model.addColumn("Nombre");        
        model.addColumn("Email");
        model.addColumn("Fono");

        // Se agrega la tabla al JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Creación de botones para agregar y editar contactos
        addContactButton = new JButton("Nuevo");
        editContactButton = new JButton("Editar"); 
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addContactButton);
        buttonPanel.add(editContactButton); 
        add(buttonPanel, BorderLayout.SOUTH);

        addContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddContactDialog();
            }
        });

        editContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editSelectedContact();
            }
        });

        loadContacts(); // Carga los contactos iniciales
    }

    private void customizeTitleBar() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        JLabel titleLabel = new JLabel(getTitle(), JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel);
        add(panel, BorderLayout.NORTH);
    }

    public void loadContacts() {
        try {
            List<Contact> contacts = contactService.getAllContacts();
            model.setRowCount(0); // Limpia las filas existentes
            for (Contact contact : contacts) {
                String fullName = contact.getFirstName() + " " + contact.getLastName(); // Concatena nombre y apellido
                model.addRow(new Object[]{
                    contact.getId(),         
                    fullName,              
                    contact.getEmail(),
                    contact.getPhone()
                });
            }
        } catch (Exception e) {
            showErrorMessage("Error al cargar contactos: " + e.getMessage());
        }
    }

    public void showAddContactDialog() {
        try {
            ContactDialog dialog = new ContactDialog(this, contactService);
            dialog.setVisible(true); 
        } catch (Exception e) {
            showErrorMessage("Error al mostrar el diálogo de agregar contacto: " + e.getMessage());
        }
    }

    public void editSelectedContact() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Long id = (Long) model.getValueAt(selectedRow, 0); // Obteniene el ID

                // Busca el contacto en la base de datos
                Contact selectedContact = contactService.findContactById(id);
                if (selectedContact != null) {
                    String firstName = selectedContact.getFirstName();
                    String lastName = selectedContact.getLastName();
                    String email = selectedContact.getEmail();
                    String phone = selectedContact.getPhone();

                    // Crea un nuevo objeto Contact con los datos actuales para la edición
                    Contact contactForEdit = new Contact(id, firstName, lastName, email, phone);

                    ContactEditDialog dialog = new ContactEditDialog(this, contactService, contactForEdit);
                    dialog.setVisible(true); 
                } else {
                    showErrorMessage("No se encontró el contacto para editar.");
                }
            } else {
                showErrorMessage("Selecciona un contacto para editar");
            }
        } catch (Exception e) {
            showErrorMessage("Error al editar el contacto: " + e.getMessage());
        }
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void refreshTable() {
        loadContacts();
    }

    public void showView() {
        setVisible(true);
    }
}
