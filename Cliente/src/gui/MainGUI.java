package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Timoteo
 */
public class MainGUI extends JFrame {

    private JButton cmdNuevoJuego;
    private int width;
    private int height;

    public MainGUI() {
        super();

        cmdNuevoJuego = new JButton("Nuevo Juego");

        width = 500;
        height = 530;

        config();
    }

    public void config() {
        setTitle("Gato");
        setSize(width, height);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        cmdNuevoJuego.setFont(new Font("Arial", Font.PLAIN, 30));
        cmdNuevoJuego.setBackground(Color.green);
        cmdNuevoJuego.addActionListener(e -> onClickCmdNuevoJuego());
        cmdNuevoJuego.setBounds(100, 200, 300, 50);
        cmdNuevoJuego.setFocusPainted(false);
        add(cmdNuevoJuego, BorderLayout.CENTER);

    }

    private void onClickCmdNuevoJuego() {
        setVisible(false);
        GatoGUI gatoGUI = new GatoGUI(this);
        gatoGUI.setVisible(true);
    }
}
