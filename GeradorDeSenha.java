package com.example.myjavafxapp;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class GeradorDeSenha {
    public static void main(String[] args) {
        // Pergunta ao usuário quantos caracteres a senha deve ter
        String lengthInput = JOptionPane.showInputDialog("Quantos caracteres a senha deve ter?");
        int length = Integer.parseInt(lengthInput);

        // Pergunta ao usuário se deve incluir caracteres especiais
        int includeSpecialChars = JOptionPane.showConfirmDialog(null, "A senha deve conter caracteres especiais?", "Caracteres Especiais", JOptionPane.YES_NO_OPTION);

        // Gera a senha
        String password = generatePassword(length, includeSpecialChars == JOptionPane.YES_OPTION);

        // Exibe a senha gerada
        JOptionPane.showMessageDialog(null, "A senha gerada é:  " + password);

        // Salva a senha em um arquivo .txt
        savePasswordToFile(password);
    }

    // Método para gerar a senha
    public static String generatePassword(int length, boolean includeSpecialChars) {
        String upperCaseLetters = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = " abcdefghijklmnopqrstuvwxyz";
        String numbers = " 0123456789";
        String specialChars = " !@#$%^&*()-_=+[]{}|;:,.<>?/";

        String allowedChars = upperCaseLetters + lowerCaseLetters + numbers;
        if (includeSpecialChars) {
            allowedChars += specialChars;
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(index));
        }

        return password.toString();
    }

    // Método para salvar a senha em um arquivo .txt
    public static void savePasswordToFile(String password) {
        try (FileWriter writer = new FileWriter("senha.txt")) {
            writer.write(password);
            JOptionPane.showMessageDialog(null, "Senha salva em senha.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar a senha no arquivo: " + e.getMessage());
        }
    }
}
