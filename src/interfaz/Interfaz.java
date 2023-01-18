package interfaz;

import java.awt.*;
import javax.swing.*;
import Ahorcado.Ahorcado;
import Ahorcado.Conjunto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Interfaz {

	private JFrame frame;
	private JTextField campoTexto;
	private Ahorcado juego;
	private JLabel lblCantidadIntentos;
	private JTextArea letrasUsadas;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		juego = new Ahorcado();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 250, 450, 300);
		frame.setTitle("Ahorcado - Animales");
		frame.setIconImage(new ImageIcon("icono.png").getImage());
		frame.getContentPane().setBackground(Color.ORANGE);;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		campoTexto = new JTextField();
		campoTexto.setAlignmentX(JTextField.CENTER_ALIGNMENT);
		campoTexto.setBounds(180, 215, 30, 19);
		frame.getContentPane().add(campoTexto);
		campoTexto.setColumns(10);

		JLabel lblPalabra = new JLabel(palabraEspaciada(juego.mostrarPalabraCifrada()));
		lblPalabra.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalabra.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPalabra.setBounds(59, 149, 297, 32);
		frame.getContentPane().add(lblPalabra);
		
		JLabel lblMensaje = new JLabel("");
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensaje.setBounds(59, 111, 297, 14);
		frame.getContentPane().add(lblMensaje);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoTexto.setEditable(true);
				btnAceptar.setText("Aceptar");
				String letraIngresada = obtieneLetra();
				juego.verificarLetraIngresada(letraIngresada);
				lblMensaje.setText(juego.mensajeActual(letraIngresada));
				campoTexto.setText("");
				lblPalabra.setText(palabraEspaciada(juego.mostrarPalabraCifrada()));
				lblCantidadIntentos.setText(juego.getIntentos() + "");
				letrasUsadas.setText(mostrarLetrasUsadas(juego.getLetrasUsadas()));
				reiniciar(btnAceptar);
			}
		});
		btnAceptar.setBounds(215, 214, 131, 21);
		frame.getContentPane().add(btnAceptar);

		JLabel lblIngreseLetra = new JLabel("Ingrese una letra");
		lblIngreseLetra.setBounds(76, 218, 114, 13);
		frame.getContentPane().add(lblIngreseLetra);
		
		JLabel lblIntentosRestantes = new JLabel("Intentos restantes:");
		lblIntentosRestantes.setBounds(10, 10, 141, 13);
		frame.getContentPane().add(lblIntentosRestantes);
		
		lblCantidadIntentos = new JLabel(juego.getIntentos()+"");
		lblCantidadIntentos.setBounds(122, 10, 46, 13);
		frame.getContentPane().add(lblCantidadIntentos);

		JLabel lblLetrasUsadas = new JLabel("Letras usadas");
		lblLetrasUsadas.setVerticalAlignment(SwingConstants.TOP);
		lblLetrasUsadas.setBounds(332, 10, 114, 19);
		frame.getContentPane().add(lblLetrasUsadas);

		letrasUsadas = new JTextArea();
		letrasUsadas.setEditable(false);
		letrasUsadas.setOpaque(false);
		letrasUsadas.setBounds(353, 26, 114, 246);
		frame.getContentPane().add(letrasUsadas);
	}

	private String obtieneLetra() {
		String letraIngresada = campoTexto.getText();
		return letraIngresada;
	}

	private String palabraEspaciada(String palabra) {
		String s = "";
		for (int i = 0; i < palabra.length(); i++) {
			s += palabra.charAt(i) + " ";
		}
		return s.substring(0, s.length() - 1);
	}

	private String mostrarLetrasUsadas(Conjunto<String> letrasUsadas) {
		StringBuilder s = new StringBuilder();
		int i = 0;
		while (i != letrasUsadas.cantElementos()) {
			s.append(letrasUsadas.dameElemento().toString()).append(" \n");
			i++;
		}
		return s.toString();
	}
	
	private void reiniciar(JButton btn) {
		if(juego.juegoTerminado()) {
			campoTexto.setEditable(false);
			btn.setText(juego.mensajeReiniciar());
			juego.reiniciarJuego();
		}
	}
}