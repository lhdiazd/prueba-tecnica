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

public class ContactDialog extends JDialog {
    private JTextField firstNameField, lastNameField, emailField, phoneField;
    private JButton saveButton, cancelButton;
    private IContactService contactService;

    public ContactDialog(ContactView parentView, IContactService contactService) {
        super(parentView, "Agregar Contacto", true);
        this.contactService = contactService;

        setLayout(new BorderLayout());
        customizeDialogTitle(); // Aplica el estilo al título

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BorderLayout());

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(5, 2, 10, 10)); // Ajusta el diseño de campos
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 20));

        // Campo Nombre
        fieldsPanel.add(new JLabel("Nombre:"));
        firstNameField = new JTextField(20);
        firstNameField.setToolTipText("Ingrese el nombre del contacto"); // Tooltip
        fieldsPanel.add(firstNameField);

        // Campo Apellido
        fieldsPanel.add(new JLabel("Apellido:"));
        lastNameField = new JTextField(20);
        lastNameField.setToolTipText("Ingrese el apellido del contacto"); // Tooltip
        fieldsPanel.add(lastNameField);

        // Campo Email
        fieldsPanel.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        emailField.setToolTipText("Ingrese el correo electrónico del contacto (formato: ejemplo@correo.com)"); // Tooltip
        fieldsPanel.add(emailField);

        // Campo Teléfono
        fieldsPanel.add(new JLabel("Fono:"));
        phoneField = new JTextField(20);
        phoneField.setToolTipText("Ingrese el número de teléfono del contacto (opcional)"); // Tooltip
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
        setSize(new Dimension(400, 250)); // Aumenta el tamaño del cuadro de diálogo
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

        if (!email.matches("^\\S+@\\S+\\.\\S+$")) { // Valida el formato de correo
            throw new Exception("Correo electrónico no válido");
        }

        if (!phone.isEmpty() && !phone.matches("^\\d+$")) { // Valida el formato de número telefónico si se ingresa
            throw new Exception("Número telefónico no válido");
        }

        Contact newContact = new Contact(firstName, lastName, email, phone.isEmpty() ? null : phone); // Permitir campo vacío para teléfono
        contactService.saveContact(newContact);
        ((ContactView) getParent()).refreshTable();
        setVisible(false);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
