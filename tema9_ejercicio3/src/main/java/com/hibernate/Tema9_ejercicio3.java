package com.hibernate;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hibernate.dao.SeriesDAO;

import com.hibernate.model.Series;


import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tema9_ejercicio3 {

	private JFrame frame;
	private JTextField textFieldId;
	private JTextField textFieldNombre;
	private JTextField textFieldTem;
	private JTextField textFieldCap;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tema9_ejercicio3 window = new Tema9_ejercicio3();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tema9_ejercicio3() {
		initialize();
	}

	private void initialize() {
		SeriesDAO seriesDAO = new SeriesDAO();

		frame = new JFrame();
		frame.setBounds(100, 100, 727, 708);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DefaultTableModel series = new DefaultTableModel();
		series.addColumn("idserie");
		series.addColumn("nombre");
		series.addColumn("temporadas");
		series.addColumn("capitulos");

		List<Series> seriesselect = seriesDAO.selectAllSeries();
		for (Series s : seriesselect) {
			Object[] fila = { s.getId(), s.getNombre(), s.getTemporadas(), s.getCapitulos() };
			series.addRow(fila);
		}

		JTable tabla = new JTable(series);
		System.out.println("tabla creada ");
		frame.getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(10, 0, 600, 331);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Id:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(37, 355, 46, 47);
		frame.getContentPane().add(lblNewLabel);
		
		textFieldId = new JTextField();
		textFieldId.setEditable(false);
		textFieldId.setEnabled(false);
		textFieldId.setBounds(126, 370, 112, 20);
		frame.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		JLabel lblSerie = new JLabel("Serie:");
		lblSerie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSerie.setBounds(37, 419, 46, 47);
		frame.getContentPane().add(lblSerie);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(126, 434, 296, 20);
		frame.getContentPane().add(textFieldNombre);
		
		JLabel lblTemporadas = new JLabel("Temporadas");
		lblTemporadas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTemporadas.setBounds(27, 471, 120, 47);
		frame.getContentPane().add(lblTemporadas);
		
		textFieldTem = new JTextField();
		textFieldTem.setColumns(10);
		textFieldTem.setBounds(126, 486, 112, 20);
		frame.getContentPane().add(textFieldTem);
		
		JLabel lblC = new JLabel("Capitulos");
		lblC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblC.setBounds(27, 531, 120, 47);
		frame.getContentPane().add(lblC);
		
		textFieldCap = new JTextField();
		textFieldCap.setColumns(10);
		textFieldCap.setBounds(126, 546, 112, 20);
		frame.getContentPane().add(textFieldCap);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int temporadas = Integer.parseInt(textFieldTem.getText());
				int capitulos = Integer.parseInt(textFieldCap.getText());
				Series  serie1 = new Series( textFieldNombre.getText(),temporadas,capitulos);
	            seriesDAO.insertSeries(serie1);List<Series> seriesselect = seriesDAO.selectAllSeries();
	            series.setRowCount(0);
	    		for (Series s : seriesselect) {
	    			Object[] fila = { s.getId(), s.getNombre(), s.getTemporadas(), s.getCapitulos() };
	    			series.addRow(fila);
	    		}
	            JOptionPane.showMessageDialog(null, "Serie añadida"); 
			}
		});
		btnGuardar.setBounds(37, 609, 120, 23);
		frame.getContentPane().add(btnGuardar);
		
		JButton btnActu = new JButton("Actualizar");
		btnActu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int selectedRow = tabla.getSelectedRow();
			        if (selectedRow == -1) {
			            JOptionPane.showMessageDialog(null, "Seleccione una serie para actualizar");
			        } else {
			            int id = (int) tabla.getValueAt(selectedRow, 0);
			            String nombre = textFieldNombre.getText();
			            int temporadas = Integer.parseInt(textFieldTem.getText());
			            int capitulos = Integer.parseInt(textFieldCap.getText());
			            Series  serie1 = new Series( textFieldNombre.getText(),temporadas,capitulos);
			            seriesDAO.updateSeries(serie1);
			            series.setValueAt(nombre, selectedRow, 1);
			            series.setValueAt(temporadas, selectedRow, 2);
			            series.setValueAt(capitulos, selectedRow, 3);
			    		series.fireTableDataChanged(); 
			            JOptionPane.showMessageDialog(null, "Serie actualizada"); 
			        }
			    }
			
		});
		btnActu.setBounds(314, 609, 120, 23);
		frame.getContentPane().add(btnActu);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tabla.getSelectedRow();
				if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "Seleccione una serie para actualizar");
		        } else {
		        	  int id = (int) tabla.getValueAt(selectedRow, 0);
		              seriesDAO.deleteSeries(id);

		             
		              series.removeRow(selectedRow);
		              JOptionPane.showMessageDialog(null, "Serie eliminada"); 
			}
			}
		});
		btnBorrar.setBounds(547, 609, 120, 23);
		frame.getContentPane().add(btnBorrar);
		
		JButton btnNewButton = new JButton("Mostrar");
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(333, 342, 89, 23);
		frame.getContentPane().add(btnNewButton);
		

		

		
		tabla.setVisible(true);
		scrollPane.setVisible(true);
		tabla.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int filaSeleccionada = tabla.getSelectedRow();
		        if (filaSeleccionada >= 0) {
		            String idSerie = tabla.getValueAt(filaSeleccionada, 0).toString();
		            String nombreSerie = tabla.getValueAt(filaSeleccionada, 1).toString();
		            String temporadasSerie = tabla.getValueAt(filaSeleccionada, 2).toString();
		            String capitulosSerie = tabla.getValueAt(filaSeleccionada, 3).toString();
		            textFieldId.setText(idSerie);
		            textFieldNombre.setText(nombreSerie);
		            textFieldTem.setText(temporadasSerie);
		            textFieldCap.setText(capitulosSerie);
		        }
		    }
		});
		int filaSeleccionada = tabla.getSelectedRow();
		if (filaSeleccionada >= 0) {
		    String idSerie = tabla.getValueAt(filaSeleccionada, 0).toString();
		    String nombreSerie = tabla.getValueAt(filaSeleccionada, 1).toString();
		    String temporadasSerie = tabla.getValueAt(filaSeleccionada, 2).toString();
		    String capitulosSerie = tabla.getValueAt(filaSeleccionada, 3).toString();
		    textFieldId.setText(idSerie);
		    textFieldNombre.setText(nombreSerie);
		    textFieldTem.setText(temporadasSerie);
		    textFieldCap.setText(capitulosSerie);
		}

		frame.setVisible(true);
	}
	
	
		
	}
	
