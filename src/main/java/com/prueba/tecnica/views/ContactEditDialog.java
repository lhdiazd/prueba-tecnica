package com.prueba.tecnica.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.prueba.tecnica.models.Contact;
import com.prueba.tecnica.services.IContactService;

public class ContactEditDialog extends JDialog {
    private JTextField firstNameField, lastNameField, emailField, phoneField;
    private JButton saveButton, cancelButton;
    private IContactService contactService;
    private Contact contactForEdit;

    public ContactEditDialog(ContactView parentView, IContactService contactService, Contact contactForEdit) {
        super(parentView, "Editar Contacto", true); // modal dialog
        this.contactService = contactService;
        this.contactForEdit = contactForEdit;

        setLayout(new BorderLayout());
        customizeDialogTitle(); // Aplicar el estilo al título

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BorderLayout());

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(5, 2, 10, 10)); // Ajustar diseño de campos
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 20));
        fieldsPanel.add(new JLabel("Nombre:"));
        firstNameField = new JTextField(contactForEdit.getFirstName(), 20);
        fieldsPanel.add(firstNameField);

        fieldsPanel.add(new JLabel("Apellido:"));
        lastNameField = new JTextField(contactForEdit.getLastName(), 20);
        fieldsPanel.add(lastNameField);

        fieldsPanel.add(new JLabel("Email:"));
        emailField = new JTextField(contactForEdit.getEmail(), 20);
        fieldsPanel.add(emailField);

        fieldsPanel.add(new JLabel("Fono:"));
        phoneField = new JTextField(contactForEdit.getPhone(), 20);
        fieldsPanel.add(phoneField);

        formPanel.add(fieldsPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        saveButton = new JButton("Guardar");
        cancelButton = new JButton("Cancelar");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveContact();
                } catch (Exception ex) {
                    showErrorMessage(ex.getMessage());
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);

        formPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(formPanel, BorderLayout.CENTER);
        setSize(new Dimension(400, 250)); // Aumentar tamaño del cuadro de diálogo
        setLocationRelativeTo(parentView);
    }

    private void customizeDialogTitle() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        JLabel titleLabel = new JLabel(getTitle(), JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel);
        add(panel, BorderLayout.NORTH);
    }

    private void saveContact() throws Exception {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            throw new Exception("Nombre, Apellido y Email son obligatorios");
        }

        if (!email.matches("^\\S+@\\S+\\.\\S+$")) { // Validar formato de correo
            throw new Exception("Correo electrónico no válido");
        }

        if (!phone.isEmpty() && !phone.matches("^\\d+$")) { // Validar formato de número telefónico si se ingresa
            throw new Exception("Número telefónico no válido");
        }

        contactForEdit.setFirstName(firstName);
        contactForEdit.setLastName(lastName);
        contactForEdit.setEmail(email);
        contactForEdit.setPhone(phone.isEmpty() ? null : phone); // Permitir campo vacío para teléfono

        contactService.saveContact(contactForEdit); // Actualizar el contacto existente
        ((ContactView) getParent()).refreshTable(); // Actualizar la vista de la tabla
        setVisible(false);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
