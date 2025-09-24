// NIM: 2111510141
// NAMA: Andri Septian Nugraha

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ZooFrame extends JFrame {

    private JComboBox<String> animalTypeComboBox;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField specificAttributeField;
    private JCheckBox canFlyCheckBox;
    private JLabel specificAttributeLabel;
    private JTextArea displayArea;
    private JButton addButton;

    private ArrayList<Animal> animals;

    public ZooFrame() {
        super("Digital Zoo Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLayout(new BorderLayout(10, 10));

        animals = new ArrayList<>();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 5, 5));

        nameField = new JTextField(15);
        ageField = new JTextField(15);
        specificAttributeField = new JTextField(15);
        canFlyCheckBox = new JCheckBox("Can Fly?");
        
        specificAttributeLabel = new JLabel("Fur Color:");
        
        String[] animalTypes = {"Generic Mammal", "Generic Bird"};
        animalTypeComboBox = new JComboBox<>(animalTypes);

        inputPanel.add(new JLabel("Animal Type:"));
        inputPanel.add(animalTypeComboBox);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(specificAttributeLabel);
        inputPanel.add(specificAttributeField);
        inputPanel.add(new JLabel("")); 
        inputPanel.add(canFlyCheckBox);
        
        canFlyCheckBox.setVisible(false);
        
        animalTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) animalTypeComboBox.getSelectedItem();
                if ("Generic Mammal".equals(selectedType)) {
                    specificAttributeLabel.setText("Fur Color:");
                    specificAttributeField.setVisible(true);
                    canFlyCheckBox.setVisible(false);
                } else if ("Generic Bird".equals(selectedType)) {
                    specificAttributeLabel.setText("");
                    specificAttributeField.setVisible(false);
                    canFlyCheckBox.setVisible(true);
                }
            }
        });

        addButton = new JButton("Add Animal");
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAnimal();
            }
        });

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.CENTER);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addAnimal() {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String selectedType = (String) animalTypeComboBox.getSelectedItem();

            Animal newAnimal = null;

            if ("Generic Mammal".equals(selectedType)) {
                String furColor = specificAttributeField.getText();
                newAnimal = new Mammal(name, age, furColor);
                displayArea.append("Added a new Mammal!\n");
            } else if ("Generic Bird".equals(selectedType)) {
                boolean canFly = canFlyCheckBox.isSelected();
                newAnimal = new Bird(name, age, canFly);
                displayArea.append("Added a new Bird!\n");
            }

            if (newAnimal != null) {
                animals.add(newAnimal);
                displayArea.append("Info: " + newAnimal.getInfo() + "\n");
                displayArea.append("Sound: " + newAnimal.makeSound() + "\n\n");
            }

            nameField.setText("");
            ageField.setText("");
            specificAttributeField.setText("");
            canFlyCheckBox.setSelected(false);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid age.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ZooFrame();
            }
        });
    }
}